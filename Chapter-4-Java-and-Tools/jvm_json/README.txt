
mvn clean install
mvn -q exec:java -Dexec.args="testJson.clj testJson.js testJson.groovy testJson.py"

or outside of maven
jvms.sh testJson.clj testJson.js testJson.py testJson.groovy


java library examples
---------------------
http://stackoverflow.com/questions/338586/a-better-java-json-library
https://code.google.com/p/google-gson/
http://json-lib.sourceforge.net/
http://flexjson.sourceforge.net/
https://code.google.com/p/json-simple/