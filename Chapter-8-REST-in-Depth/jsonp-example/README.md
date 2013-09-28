
JSONP Example

To set the callback
curl -HAccept:application/x-javascript http://localhost:8080/api/greeting?callback=asdf

Default Callback
curl -HAccept:application/x-javascript http://localhost:8080/api/greeting

XML
curl -HAccept:application/xml http://localhost:8080/api/greeting

JSON
curl -HAccept:application/json http://localhost:8080/api/greeting

WADL
http://localhost:8080/api/application.wadl


mvn clean compile exec:java

https://grizzly.java.net/

TODO - add an HTML test page?  Either that or just resort to curls...