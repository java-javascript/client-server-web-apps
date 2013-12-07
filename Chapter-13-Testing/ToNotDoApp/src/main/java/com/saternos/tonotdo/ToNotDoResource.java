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

	String str = "";

	if (sc.getUserPrincipal() != null)
		str=" ("+sc.getUserPrincipal().getName()+")";

    d.setName(d.getName() + str);
    return Response.status(200).entity(d).build();
  }

}