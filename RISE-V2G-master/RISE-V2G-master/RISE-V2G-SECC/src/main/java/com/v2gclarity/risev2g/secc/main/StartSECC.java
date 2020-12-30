/*******************************************************************************
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2015 - 2019  Dr. Marc Mültin (V2G Clarity)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *******************************************************************************/
package com.v2gclarity.risev2g.secc.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.v2gclarity.risev2g.secc.session.V2GCommunicationSessionHandlerSECC;
import com.v2gclarity.risev2g.secc.transportLayer.TCPServer;
import com.v2gclarity.risev2g.secc.transportLayer.TLSServer;
import com.v2gclarity.risev2g.secc.transportLayer.UDPServer;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.WallboxServerEndpoint;
import com.v2gclarity.risev2g.shared.enumerations.GlobalValues;
import com.v2gclarity.risev2g.shared.utils.MiscUtils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class StartSECC {
	
	public static void main(String[] args) {
		final Logger logger = LogManager.getLogger(StartSECC.class.getSimpleName());
		MiscUtils.loadProperties(GlobalValues.SECC_CONFIG_PROPERTIES_PATH.toString());
		
		UDPServer udpServer = UDPServer.getInstance();
		TCPServer tcpServer = TCPServer.getInstance();
		TLSServer tlsServer = TLSServer.getInstance();
		
		if (!udpServer.initialize() || !tlsServer.initialize() || !tcpServer.initialize()) {
			logger.fatal("Unable to start SECC because UDP, TCP or TLS server could not be initialized");
		} else {
			Thread udpServerThread = new Thread(udpServer);
			udpServerThread.setName("UDPServerThread");
			
			Thread tcpServerThread = new Thread(tcpServer);
			tcpServerThread.setName("TCPServerThread");
			
			Thread tlsServerThread = new Thread(tlsServer);
			tlsServerThread.setName("TLSServerThread");
			
			// Initialize the server end-point to the HSRM wallbox  
			WallboxServerEndpoint wallboxServerEndpoint = new WallboxServerEndpoint();
			
			// startWebsocketServer();
			
			// All transport layer threads need to be initialized before initializing the SECC session handler.
			new V2GCommunicationSessionHandlerSECC(wallboxServerEndpoint);
			
			/*
			 * To avoid possible race conditions, the transport layer threads need to be started AFTER the SECC
			 * session handler has been initialized. Otherwise the situation might occur that the UDPServer is 
			 * receiving a UDP client packet and tries to access the MessageHandler object before this object has
			 * been created by the SECC session handler.
			 */
			udpServerThread.start();
			tcpServerThread.start();
			tlsServerThread.start();
		} 
	}
	
	private static void startWebsocketServer() {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        try
        {
            // Initialize javax.websocket layer
            WebSocketServerContainerInitializer.configure(context,
                (servletContext, wsContainer) ->
                {
                    // This lambda will be called at the appropriate place in the
                    // ServletContext initialization phase where you can initialize
                    // and configure  your websocket container.

                    // Configure defaults for container
                    wsContainer.setDefaultMaxTextMessageBufferSize(65535);

                    // Add WebSocket endpoint to javax.websocket layer
                    wsContainer.addEndpoint(WallboxServerEndpoint.class);
                });

            server.start();
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
	}
}
