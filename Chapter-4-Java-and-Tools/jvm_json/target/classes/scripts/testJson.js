println('*** JSON Javascript ***')
// Hack to read in file (using Java) and convert the Java String to a JavaScript String
var str = String(com.saternos.app.Jvms.readFile("data/test.json"));  

// Don't do this in production.  Example demonstrating JSON is a subset of JavaScript
var o = eval(str);
for (var i=0; i < o.length; i++){println(o[i].title);}