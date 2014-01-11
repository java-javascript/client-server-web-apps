package controllers;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

// pretty print

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
        result.put("NestedObj", child);

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
