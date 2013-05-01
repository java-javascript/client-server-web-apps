To build the project:

	> mvn clean install

To start the server

	> mvn test -Pserver
	
About page should be available at

	http://localhost:4579/about
	
This demonstrates
	- ruby is available
	- rubygems (sinatra) available
	- java (System) available
	
Tested on Mac OSX with:
	Apache Maven 2.2.1 (r801777; 2009-08-06 15:16:01-0400)
	Java version: 1.6.0_43
	
Examples
# GET
curl http://localhost:4579/this/is/a/test?the=thing, 
curl -i http://localhost:4579/?httpErrorCode=400

# HEAD (same as GET with no body):  
curl -iI http://localhost:4579/?httpErrorCode=501

# POST
curl -i -H "Accept: application/json" -X POST -d "['test',1,2]" http://localhost:4579 

# PUT
curl -i -H "Accept: application/json" -X PUT -d "{phone: 1-800-999-9999}" http://localhost:4579

# DELETE
curl -i -H "Accept: application/json" -X DELETE http://localhost:4579

# http://beerpla.net/2010/06/10/how-to-display-just-the-http-response-code-in-cli-curl/	