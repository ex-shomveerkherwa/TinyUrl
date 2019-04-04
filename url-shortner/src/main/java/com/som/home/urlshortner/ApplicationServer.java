package com.som.home.urlshortner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.som.home.urlshortner.rest.UrlResource;

public class ApplicationServer {

	private Server server;
	
	public static void main(String[] args) throws Exception {
		ApplicationServer applicationServer = new ApplicationServer();
		
		try {
			applicationServer.start();
			applicationServer.join();
		} finally {
			applicationServer.stop();
		}
	}

	private void join() throws InterruptedException {
		server.join();
	}

	private void start() throws Exception {
		server = new Server(8080);
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		
		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", MyResource.class.getCanonicalName());
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", UrlResource.class.getCanonicalName());
		context.addEventListener(new ContextLoaderListener());
        context.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        context.setInitParameter("contextConfigLocation", ApplicationConfiguration.class.getName());

        server.setHandler(context);
		server.start();
	}

	private void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			server.destroy();
			e.printStackTrace();
		}
		
	}

}
