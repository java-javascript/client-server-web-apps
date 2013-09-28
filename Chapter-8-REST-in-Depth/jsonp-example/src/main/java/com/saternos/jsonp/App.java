package com.saternos.jsonp;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.grizzly.http.server.*;

public class App {

    public static void main(String[] args) throws java.io.IOException{

			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
						java.net.URI.create("http://localhost:8080/api"), 
						new ResourceConfig(GreetingResource.class)
			);
			
			StaticHttpHandler staticHttpHandler = new StaticHttpHandler("src/main/webapp");
			server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");
			
            System.in.read();
            server.stop();
    }

}
