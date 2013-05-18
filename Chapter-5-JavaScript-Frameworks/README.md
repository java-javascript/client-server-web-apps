Chapter 5 JavaScript Frameworks
===============================

Project: Simple (no server integration) implemented in each framework.

We do embed a web server (Jetty).  This can be run using:

1) Gradle:

	gradle jettyRun

2) Maven:

	mvn jetty:run

The pom.xml is a bit more verbose (by nature of xml and because a minimal WEB-INF/web.xml seems to be required)
	
Or if you have python installed, you can just run your own:	

	python -m SimpleHTTPServer
	
