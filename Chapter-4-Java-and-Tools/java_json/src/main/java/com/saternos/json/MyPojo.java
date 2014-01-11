package com.saternos.json;

/**
 *
 */
public class MyPojo {
    private String thing1;
    private String thing2;

    public MyPojo() {
        System.out.println("*** Constructor MyPojo() called");
    }

    public String getThing1() {
        return thing1;
    }

    public void setThing1(String thing1) {
        this.thing1 = thing1;
    }

    public String getThing2() {
        return thing2;
    }

    public void setThing2(String thing2) {
        this.thing2 = thing2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPojo myPojo = (MyPojo) o;

        if (thing1 != null ? !thing1.equals(myPojo.thing1) : myPojo.thing1 != null) return false;
        if (thing2 != null ? !thing2.equals(myPojo.thing2) : myPojo.thing2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = thing1 != null ? thing1.hashCode() : 0;
        result = 31 * result + (thing2 != null ? thing2.hashCode() : 0);
        return result;
    }
}
