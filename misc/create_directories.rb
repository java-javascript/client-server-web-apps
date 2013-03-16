#
# Structure code project by chapter to match the book
#
{
  'Chapter-1-Introduction'          => 'None',
  'Chapter-2-JavaScript-And-Tools'  => 'JavaScript Only - uses node.js, Testacular, Jasmine with JavaScript for testing, docco for documentation.  The specific projects will be the front end for later projects in the book.',
  'Chapter-3-REST-and-JSON'         => 'Use various REST client tools with either public or project with sample REST calls',
  'Chapter-4-Java-and-Tools'        => 'Simple single module Java main project that converts Java objects to JSON and back.',
  'Chapter-5-JavaScript-Frameworks' => 'Simple (no server integration) implemented in each framework.',
  'Chapter-6-Java-RESTful-Servers'  => 'Hello world on each server using curl or simple web page to view results',
  'Chapter-7-Rapid-Development-Practices'=>'No distinct project - examples from inline text will be extracted',
  'Chapter-8-REST-in-Depth'         => 'REST Server implementation with curl calls to demonstrate',
  'Chapter-9-jQuery-and-Jython'     => 'Python HTTP Server - JSON on File System.  App will also use JSONP to do look ups on external data.',
  'Chapter-10-JRuby-and-Angular'    => 'Sinatra (stock portfolio).',
  'Chapter-11-Jersey-and-Jetty'     => 'Jersey/Jetty/jQuery/maven (personal library of books)',
  'Chapter-12-RESTeasy-and-Tomcat-in-a-war' => 'Book library rewritten using Backbone JS.',
  'Chapter-13-RESTeasy-and-JBoss-in-an-ear' => 'Restructure and enhance (modify to use angular/angularui).  Seperate wars for REST and web app.',
  'Chapter-14-Conclusion'           => '' 

}.each_pair do |k,v|
  
  puts "#{k} Project: #{v}"
  system "mkdir #{k}"
  File.open("#{k}/README.md",'w') do |f|
      f.puts(k.gsub('-',' '))
      f.puts '=' * k.length
      f.puts
      f.puts "Project: " + v
  end
   
end

puts "Completed."
