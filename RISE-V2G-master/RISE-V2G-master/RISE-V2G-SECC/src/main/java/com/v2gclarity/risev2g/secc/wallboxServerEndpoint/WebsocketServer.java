package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class WebsocketServer implements Runnable {

	private static final WebsocketServer uniqueWebsocketServerInstance = new WebsocketServer();
	private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	private WebsocketServer() {
	}

	public static WebsocketServer getInstance() {
		return uniqueWebsocketServerInstance;
	}

	@Override
	public void run() {
		Server server = new Server(8080);

		// Setup the basic application "context" for this application at "/"
		// This is also known as the handler tree (in jetty speak)
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		try {
			// Initialize javax.websocket layer
			WebSocketServerContainerInitializer.configure(context, (servletContext, wsContainer) -> {
				// This lambda will be called at the appropriate place in the
				// ServletContext initialization phase where you can initialize
				// and configure your websocket container.
				
				wsContainer.getPolicy().setIdleTimeout(1500);	// TODO: periodically send pings to prevent timeout 

				// Configure defaults for container
				wsContainer.setDefaultMaxTextMessageBufferSize(65535);

				// Add WebSocket endpoint to javax.websocket layer
				wsContainer.addEndpoint(WallboxServerEndpoint.class);
			});

			server.start();
			
			getLogger().info(server.getState() + ", Waiting for new connection...");
			
			server.join();
			
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}
	}

	private Logger getLogger() {
		return logger;
	}
}
