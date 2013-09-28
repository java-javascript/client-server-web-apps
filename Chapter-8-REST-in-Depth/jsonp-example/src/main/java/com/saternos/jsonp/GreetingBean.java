package com.saternos.jsonp;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "greeting")
public class GreetingBean {

    @XmlAttribute
    public String text;

    public GreetingBean() {}
    public GreetingBean(String text) {
        this.text = text;
    }
}
