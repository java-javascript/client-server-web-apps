package com.oreilly.jersey.restservices;

import org.apache.commons.lang.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO: Review Curl, discuss fiddler, browser plugins
 * curl -X GET http://localhost:9090/jetty-server/books/list
 */


/**
 * NOTE:  Discuss use of @Path directive
 */
@Path("/books")

/**
 * TODO:  Naming -discussion of Services/Resources
 */
public class BookService {

    public static Map<String, String> books = new TreeMap<String, String>();

    @Path("/{book}")
    @PUT
    @Produces("application/json")
    public String create(@PathParam("book") String book, @QueryParam("title") String title) {
        books.put(book, title);
        return "{\"Added book #" + book + "\"}";
    }

    @Path("/{book}")
    @GET
    @Produces("application/json")
    public String find(@PathParam("book") String book) {
        if (books.containsKey(book))
            return "{\"" + book + "\",\"" + books.get(book) + "\"}";

        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Path("/{book}")
    @DELETE
    @Produces("application/json")
    public String delete(@PathParam("book") String book) {
        books.remove(book);
        if (books.containsKey(book))
            return "{\"" + book + "\"}";

        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Path("/")
    @GET
    @Produces("application/json")
    public String list() {
        String json = "{";
        for (Map.Entry<String, String> book : books.entrySet())
            json += "\"" + book.getKey() + "\":\"" + book.getValue() + "\",";

        if (books.size() > 0)
            json = StringUtils.chop(json);

        return json + "}";
    }
}