require 'rubygems'
require 'sinatra'
require 'json'

configure {
  Sinatra::Application.reset!; 
  use Rack::Reloader
}

set :public_folder, File.dirname(__FILE__) + '/webapp'

get "/testapp/api/:entity" do
  content_type 'application/json'
  File.open("#{File.dirname(__FILE__)}/api/#{params[:entity]}.json").readlines
end

get '/' do
  redirect 'index.html'
end

