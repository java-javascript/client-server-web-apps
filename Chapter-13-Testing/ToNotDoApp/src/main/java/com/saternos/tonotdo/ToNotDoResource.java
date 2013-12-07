package com.saternos.tonotdo;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import org.joda.time.DateTime;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Path ("/tonotdo")
public class ToNotDoResource {

  @GET
  @Path ("/{param}")
  @Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response helloJson(@Context SecurityContext sc, @PathParam ("param") String who) {
    Item d = new Item();
	String str = "";
	
	if (sc.getUserPrincipal() != null)
		str=" ("+sc.getUserPrincipal().getName()+")";

    d.setName(who + str);
    return Response.status(200).entity(d).build();
  }

  @PUT
  @Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response helloJson(@Context SecurityContext sc, Item d) {
    storeData(sc.getUserPrincipal().getName(), d);
    return Response.status(200).entity(d).build();
  }

// Persistence

  private static HashMap<String, List<Item>> dataStore;

  {
    	dataStore = new HashMap();
  }

private storeData(String name, Item d)
   if (!dataStore.containsKey(sc.getUserPrincipal().getName())){
	 dataStore.put(name, new ArrayList<Item>()); 
   }

   if (dataStore.get(name).contains())	  

}