package com.v2gclarity.risev2g.secc.transportLayer;

import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.CommunicationSessionApi;

/**
 * A simple Jetty server which starts the HSRM Wallbox API Endpoint.
 * 
 * @author Fabian Birk
 *
 */
@SuppressWarnings("unused")
public class RestAPIServer extends Observable implements Runnable {

	private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	private static final RestAPIServer uniqueRestServerInstance = new RestAPIServer();
	private ServletContextHandler context;
	private Server jettyServer;

	private RestAPIServer() {
	}

	public static RestAPIServer getInstance() {
		return uniqueRestServerInstance;
	}

	@Override
	public void run() {
		context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.setResourceBase(System.getProperty("user.dir"));
		context.setWelcomeFiles(new String[] { "index.html", "index.htm", "index.jsp" });

		jettyServer = new Server(8080);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitParameter("dirAllowed", "true");
		jerseyServlet.setInitOrder(0);
		
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", CommunicationSessionApi.class.getCanonicalName());
		
		try {
			jettyServer.start();
			
			getLogger().info("Rest server started at port 8080.");
			
			jettyServer.join();
		} catch(Exception e) {
			getLogger().error("Jetty Server Exception", e);
		}
		finally {
			jettyServer.destroy();
		}
	}

	private Logger getLogger() {
		return logger;
	}

	private void setLogger(Logger logger) {
		this.logger = logger;
	}

}
