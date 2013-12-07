package com.saternos.tonotdo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {

  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}