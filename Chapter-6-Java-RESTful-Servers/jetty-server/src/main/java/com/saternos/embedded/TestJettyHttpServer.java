/*

Cs-MacBook-Air:jetty-server cs$ java -version
java version "1.6.0_45"
Java(TM) SE Runtime Environment (build 1.6.0_45-b06-451-11M4406)
Java HotSpot(TM) 64-Bit Server VM (build 20.45-b01-451, mixed mode)
Cs-MacBook-Air:jetty-server cs$ gradle --version

------------------------------------------------------------
Gradle 1.6
------------------------------------------------------------

Gradle build time: Tuesday, May 7, 2013 9:12:14 AM UTC
Groovy: 1.8.6
Ant: Apache Ant(TM) version 1.8.4 compiled on May 22 2012
Ivy: 2.2.0
JVM: 1.6.0_45 (Apple Inc. 20.45-b01-451)
OS: Mac OS X 10.7.5 x86_64

Cs-MacBook-Air:jetty-server cs$

*/
// gradle build startServer
package com.saternos.embedded;

import org.eclipse.jetty.server.Server;

public class TestJettyHttpServer {
    public static void main(String[] args) throws Exception {

        Server server = new Server(Integer.parseInt(args[0]));
        server.setHandler(new JsonHandler());
        System.out.println("Starting server on port: " + args[0]);
        server.start();
        server.join();
    }
}