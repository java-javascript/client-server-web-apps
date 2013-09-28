#
# This is a ruby-based sketch of the JSONP example used to think through 
# the implementation details.
#
require 'sinatra'
require 'json'

get '/' do
<<-HERE
<html><head></head>
    <body>
      <h3>Response</h3>
      <textarea id='response' rows='10' cols='40'></textarea>
      <br/><button id='noheader'>No Header</button>
      <br/><button id='json'>JSON</button>
      <br/><button id='jsonp'>JSONP</button>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script>
      $(function() {
  $('#noheader').on('click', function(){$.get(    '/jsontest',function(data){$('#response').val(data);});});
  $('#json').on('click', function(){    $.getJSON('/jsontest',function(data){$('#response').val(JSON.stringify(data));});});

         $('#jsonp').on('click', function(){
           
                   var url = 'http://localhost:4567/jsontest?callback=?';
                   
                   // Guarantee a cross origin request
                   if (location.hostname==='localhost'){
                     url = url.replace("localhost", "127.0.0.1");  
                   }
                   
                   $.ajax({url:     url,
                    success: function(data){ $('#response').val("JSONP (to " + url +"):"+ JSON.stringify(data));}, 
                    dataType: 'jsonp'
                   });
         });
      });
    </script>  
  </body>
</html>
HERE
end

get '/jsontest' do
 o={'Hello'=>'World'}

 puts request.accept
 puts request.content_type

 if request.accept.include?('application/json')
   content_type 'application/json'
   JSON.pretty_generate(o)   
 elsif not params['callback'].nil? # differentiated here using the parameter since header not reliably set
   content_type 'application/javascript'
   "#{params['callback']}(#{o.to_json})"
 else
   'Hello World'
 end 

end
