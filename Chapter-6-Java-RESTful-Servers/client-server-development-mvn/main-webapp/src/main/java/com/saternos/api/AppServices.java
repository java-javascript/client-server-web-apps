package com.saternos.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;


public class AppServices extends Application  {
	
 private static Set services = new HashSet(); 
 public  AppServices() {     
   services.add(new TestWebApi());  
 }
 @Override
 public  Set getSingletons() {
  return services;
 }  
 public  static Set getServices() {  
  return services;
 } 
}