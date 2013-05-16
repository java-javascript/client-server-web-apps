# Specify batch mode and it runs without prompting (default is interactive mode)
mvn --batch-mode archetype:generate -DgroupId=com.saternos.app -DartifactId=main-webapp -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-webapp
mvn --batch-mode archetype:generate -DgroupId=com.saternos.app -DartifactId=overlay-webapp -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-webapp


http://localhost:8080/main-webapp/index.jsp
http://localhost:8080/main-webapp/index-overlay.jsp

#
# Full Server Development
#
Added a pom.xml above to combine the two wars in a multi module pom.
From top level

mvn clean install; 

#
# Server development
#
# From the main-webapp directory

mvn jetty:run; cd ..

# http://localhost:8080/

#
# Client Development (no need to rebuild, just stop/start server)
#
# From the overlays directory

mvn test -Pserver

# http://localhost:4579/index.html
