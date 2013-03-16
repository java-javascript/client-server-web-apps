require 'rubygems'
require 'sinatra'
require 'json'
#
# Initial Configuration
# Download todomvc-gh-pages.zip from https://github.com/addyosmani/todomvc
PUBLIC = File.dirname(__FILE__) + '/todomvc-gh-pages'

set :public_folder, PUBLIC

configure do
  Sinatra::Application.reset!
  use Rack::Reloader
end

#
# Architecture Examples (Client Side)
#
ARCH_EXAMPLES = '/architecture-examples'

def linkify(s)
  l="#{ARCH_EXAMPLES}/#{s}/index.html";"<a href='#{l}'>#{s}</a><br/>"
end

get '/' do
  s='';Dir.open(PUBLIC + ARCH_EXAMPLES).entries().each{|e|s+=linkify("#{e}") unless %w{. ..}.include?(e)};s
end

#
# RESTful Responses
# 
@@todos=[]

get '/todos' do
  puts 'in get'
  @@todos.to_json
end  



post '/todo' do
    puts "in post #{@@todos.length}}"
    todo=JSON.parse(request.body.readlines.join.to_s)
    # This should really be a put - it updates
    @@todos.delete_if{|x|x['order']==todo['order']}
    
    @@todos << todo
  
   puts JSON.pretty_generate(JSON.parse(@@todos.to_json))
  
  @@todos.to_json
end  
 
# Diagnostics
get '/json' do
  s="<pre>"
  s+=JSON.pretty_generate(JSON.parse(@@todos.to_json))
  s+="</pre>"
end