# mvn clean install jetty:run

# Load some data

curl -X PUT http://127.0.0.1:9090/jetty-server/api/books/1?title=JavaScript+for+Kids 
curl -X PUT http://127.0.0.1:9090/jetty-server/api/books/2?title=Groovy+for+Girls
curl -X PUT http://127.0.0.1:9090/jetty-server/api/books/3?title=Ruby+for+Robots 
curl -X PUT http://127.0.0.1:9090/jetty-server/api/books/4?title=C+for+Cats  

curl http://localhost:9090/jetty-server/api/books

# JBoss runs on port 8080 by default
curl http://localhost:8080/jetty-server/api/books