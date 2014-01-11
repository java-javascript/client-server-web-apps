package com.saternos.jsonp;

import org.glassfish.jersey.server.JSONP;

import javax.ws.rs.*;

@Path("greeting")

public class GreetingResource {

    // Because we cannot rely upon the headers being set correctly, we differentiate between
    // the remote and local calls by path.
    @GET
    @Produces({"application/xml", "application/json"})
    public GreetingBean getGreeting() {
        return new GreetingBean("Hello World Local");
    }

    @Path("remote")
    @GET
    @Produces({"application/x-javascript"})
    @JSONP(queryParam = JSONP.DEFAULT_QUERY)
    public GreetingBean

    {
        return new GreetingBean("Hello World Remote");
    }
}
