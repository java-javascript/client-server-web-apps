package com.saternos.tonotdo;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.sun.jersey.spi.resource.Singleton;
import org.apache.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Singleton is required to support of our little persistence implementation below - otherwise
 * new classes are created and data is not persisted across calls.
 * See https://jersey.java.net/documentation/latest/jaxrs-resources.html#d0e1851
 */
@Singleton
@Path("/tonotdo")
public class ToNotDoResource {

    // Toy Persistence Data Store
    private static HashMap<String, List<Item>> dataStore;

    //static initializer
    {
        dataStore = new HashMap<String, List<Item>>();
    }

    /**
     * Not using plural in the path - rather the convention that no id indicates get list of items for user
     *
     * @param sc
     * @return
     */
    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public Response list(@Context SecurityContext sc) {
        List<Item> list = dataStore.get(sc.getUserPrincipal().getName());
        if (list == null) {
            list = new ArrayList<Item>();
        }
        // bit-o magic to handle collections
        GenericEntity entity = new GenericEntity<List<Item>>(list) {
        };
        return Response.status(200).entity(entity).build();
    }

    /**
     * Return the item specified, if not found return a 404
     *
     * @param sc
     * @param id
     * @return
     */
    @GET
    @Path("/{param}")
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public Response find(@Context SecurityContext sc, @PathParam("param") String id) {

        try {
            Item item = findItem(sc.getUserPrincipal().getName(), id);
            return Response.status(HttpStatus.SC_OK).entity(item).build();
        } catch (NoSuchElementException e) {
            return Response.status(HttpStatus.SC_NOT_FOUND).entity(id).build();
        }
    }

    /**
     * Remove item from the list if it exists.
     *
     * @param sc
     * @param id
     * @return
     */
    @DELETE
    @Path("/{param}")
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public Response remove(@Context SecurityContext sc, @PathParam("param") final String id) {
        removeItem(sc.getUserPrincipal().getName(), id);
        return Response.status(HttpStatus.SC_NO_CONTENT).build();
    }

    /**
     * Insert or update an item.
     *
     * @param sc
     * @param item
     * @return
     */
    @PUT
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    @Consumes({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public Response upsert(@Context SecurityContext sc, Item item) {
        String name = sc.getUserPrincipal().getName();

        if (!dataStore.containsKey(name)) {
            dataStore.put(name, new ArrayList<Item>());
        } else {
            // It might exist, remove it first it does
            removeItem(name, item);
        }

        dataStore.get(name).add(item);

        return Response.status(HttpStatus.SC_CREATED).entity(item).build();
    }

    /**
     * Utility method to find a user's item by id.
     *
     * @param name
     * @param id
     * @return
     */
    private Item findItem(String name, final String id) {

        return Iterables.find(dataStore.get(name), new Predicate<Item>() {
            public boolean apply(Item input) {
                return input.getExternalKey().equals(id);
            }
        });
    }

    /**
     * Utility method to remove a user's item by id.
     *
     * @param name
     * @param id
     */
    private void removeItem(String name, final String id) {

        Iterables.removeIf(dataStore.get(name), new Predicate<Item>() {
            @Override
            public boolean apply(Item input) {
                if (input.getExternalKey().equals(id)) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Utility method to remove a user's item.
     *
     * @param name
     * @param item
     */
    private void removeItem(String name, final Item item) {
        removeItem(name, item.getExternalKey());
    }
}