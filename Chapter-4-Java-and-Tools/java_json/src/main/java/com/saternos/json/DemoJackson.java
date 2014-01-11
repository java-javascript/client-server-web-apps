package com.saternos.json;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 */
public class DemoJackson {

    public static void main(String args[]) throws Exception {

        MyPojo pojo = new MyPojo();
        pojo.setThing1("Hello");
        pojo.setThing2("Jackson");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pojo);
        System.out.println(json);

        System.out.println("\nNow create object and populate it with the JSON string.");
        MyPojo pojo2 = mapper.readValue(json, MyPojo.class);
        System.out.println("Serialized and deserialized object are equal: " + pojo.equals(pojo2));

    }

}
