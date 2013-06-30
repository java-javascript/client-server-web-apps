package com.saternos.embedded;

import org.restlet.*;
import org.restlet.data.*;
import org.restlet.resource.*;
import org.restlet.ext.json.JsonConverter;

public class TestRestletHttpServer extends 	 {

	public static void main(String[] args) throws Exception {  
	    Component component = new Component();  
	    component.getServers().add(Protocol.HTTP, Integer.parseInt(args[0]));  
	    component.getDefaultHost().attach("/", TestRestletHttpServer.class);  
	    component.start();  
	}  

	@Get ("json")
	public String getGreeting() {  
	    return "{\"testResponse\":\"Hello World\"}";  
	}
	
}