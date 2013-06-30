Java Play JSON Server
---------------------
http://www.playframework.com/documentation/2.1.1/ScalaJsonRequests
http://www.playframework.com/documentation/2.1.1/ScalaJson


Scala Play JSON Server
---------------------
http://www.playframework.com/documentation/2.1.1/ScalaJsonRequests
http://www.playframework.com/documentation/2.1.1/ScalaJson

play new <appName>
cd <appName>
play



-----

$ play new play-server
       _            _
 _ __ | | __ _ _  _| |
| '_ \| |/ _' | || |_|
|  __/|_|\____|\__ (_)
|_|            |__/

play! 2.1.1 (using Java 1.7.0_21 and Scala 2.10.0), http://www.playframework.org

The new application will be created in /Users/cs/Desktop/tmp/play-server

What is the application name? [play-server]
> 

Which template do you want to use for this new application? 

  1             - Create a simple Scala application
  2             - Create a simple Java application

> 2
OK, application play-server is created.

Have fun!

----

cd play-server

----

modify conf/routes - add the following line:

GET     /json                       controllers.Application.json()



----

modify project/plugins.sbt

addSbtPlugin("com.jamesward" %% "play-auto-refresh" % "0.0.3")

----
Modify app/controllers/Application.java

package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ArrayNode;

// pretty print
import org.codehaus.jackson.map.ObjectMapper;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
  
	// How to return JSON:  Add a mapping to routes 
	//   GET     /json                       controllers.Application.json()
	// and a corresponding method
    public static Result json() {

	    ObjectNode result = Json.newObject();
	
		// How to nest a JSON object
		ObjectNode child = Json.newObject();
		child.put("color", "blue");
		result.put("NestedObj",child);

		//  Adding Strings
		result.put("status", "OK");
		result.put("something", "else");	
		
		// Add Integers
		result.put("int", 1);

		// Add a JSON array
	    ArrayNode collection = result.putArray("coll");
		collection.add(1);
		collection.add(2);
		collection.add(3);				
		collection.add(4);
		
		// http://www.playframework.com/documentation/api/1.2/play/mvc/results/Result.html
		return ok(result);
/*		
// To return json pretty printed, need to render it as a formatted
// String and then explicitly set the response
// http://www.playframework.com/documentation/2.1.0/JavaResponse
//
//
		ObjectMapper mapper = new ObjectMapper();
		String s="";
		try{
		 	s = mapper.defaultPrettyPrintingWriter().writeValueAsString(result);
		}catch(Exception e){}					
        return ok(s).as("application/json");
*/
    }

}
