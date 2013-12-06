To build

$ docker build -t cas/restwar .

To run 

$ docker run -p 49005:49005 -name restwarcontainer cas/restwar \
java -jar jetty-runner-8.1.9.v20130131.jar \
--port 49005 rest-jersey-server.war

To access from base machine running Vagrant VirtualBox with Docker, make sure the port is forwarded in the Vagrantfile.

Vagrant::Config.run do |config|
  ...
  config.vm.forward_port 49005, 49005
  ...