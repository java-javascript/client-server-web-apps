#
# Import the packages required for Ruby and Java
#
%w{rubygems sinatra java json}.each{|r|require r}
java_import 'java.lang.System'

# Configuration option that allows changes without restarting server - sometimes :)
configure {Sinatra::Application.reset!; use Rack::Reloader}

#
#  This block is executed prior to each HTTP call (like a servlet filter)
#
before do
   
   unless request.path=='/testrest'
      content_type 'application/json'
      request.body.rewind  # in case someone already read the body

      @@response=JSON.pretty_generate({
         'request.url'           => request.url,
         'request.request_method'=> request.request_method, 
         'request.query_string'  => request.query_string,
         'request.media_type'    => request.media_type,
         'request.body'          => request.body.read
      })   
      
   status(params['httpErrorCode'].nil? ? 200 : params['httpErrorCode'])
   end
     
end

#
# HTTP Verb followed by the path to match (* is a wild-card)
#
get '/about' do
   JSON.pretty_generate({
      'ruby.platform' => RUBY_PLATFORM, 
      'ruby.version'  => RUBY_VERSION, 
      'java.millis' => System.currentTimeMillis() 
      })
end

get '/testrest' do
   redirect 'testrest.html'
end

get '/*' do 
   @@response 
end

post '/*' do 
   @@response 
end

put '/*' do 
   @@response 
end

delete '/*' do 
   @@response 
end