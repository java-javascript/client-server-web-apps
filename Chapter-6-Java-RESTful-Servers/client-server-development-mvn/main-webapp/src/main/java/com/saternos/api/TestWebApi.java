package com.saternos.api;

import java.io.File;
import java.net.URISyntaxException;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/testapp")
public class TestWebApi {
	
 @GET
 @Produces("text/html")
 public Response  index() throws URISyntaxException { 
  File f = new File(System.getProperty("user.dir")+"/index.html");
  String mt = new MimetypesFileTypeMap().getContentType(f);
  return Response.ok(f, mt).build();   
 }

 @GET
 @Path("/hello")
 public Response  helloGet() {     
  return Response.status(200).entity("HTTP GET method called").build();
 }

 @GET
 @Path("/glossary")
 @Produces("application/json")
 public Response  getGlossary() {     
  return Response.status(200).entity("{\"serverSide\": \"glossary\"}").build();
 }

 @GET
 @Path("/menu")
 @Produces("application/json")
 public Response getMenu() {     

	String json=
		"{\"menu\": {"+
		"  \"serverSide\": \"menu\","+
		"  \"id\": \"file\","+
		"  \"value\": \"File\","+
		"  \"popup\": {"+
		"    \"menuitem\": ["+
		"      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},"+
		"      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},"+
		"      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}"+
		"    ]"+
		"  }"+
		"}}";
	
  	return Response.status(200).entity(json).build();
 }

 @GET
 @Path("/widget")
 @Produces("application/json")
 public Response  getWidget() {     
  	return Response.status(200).entity("{\"serverSide\": \"widget\"}").build();
 }

}