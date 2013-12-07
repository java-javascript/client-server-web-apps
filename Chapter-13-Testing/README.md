Chapter 13 Testing

Server Side - JAX-RS (Jetty) Testing using JUnit.
Client Side - Jasmine, Browser automation

curl --user guest:guest http://127.0.0.1:8080/tonotdo/xsadf
curl --user guest2:guest2  http://127.0.0.1:8080/tonotdo/xsadf

curl --user guest:guest -H "Accept: application/xml" http://127.0.0.1:8080/tonotdo/x


curl \
--user guest:guest \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-X PUT -d '{"name" :"echoback"}' http://127.0.0.1:8080/tonotdo

curl \
--user guest:guest \
-H "Accept: application/xml" \
-H "Content-Type: application/xml" \
-X PUT -d '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><deleteMeValueObject><name>boo</name></deleteMeValueObject>' http://127.0.0.1:8080/tonotdo

curl --user guest:guest http://127.0.0.1:8080/application.wadl



