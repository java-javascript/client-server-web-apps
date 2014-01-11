package com.saternos.json;

import com.google.gson.Gson;

/**
 *
 */
public class DemoGSON {

    public static void main(String args[]) {

        MyPojo pojo = new MyPojo();
        pojo.setThing1("Hello");
        pojo.setThing2("GSON");

        Gson gson = new Gson();
        String json = gson.toJson(pojo);
        System.out.println(json);

        System.out.println("\nNow create object and populate it with the JSON string.");
        MyPojo pojo2 = gson.fromJson(json, MyPojo.class);
        System.out.println("Serialized and deserialized object are equal: " + pojo.equals(pojo2));

    }

}
