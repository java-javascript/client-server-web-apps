%w{rubygems sinatra java json}.each{|r|require r}
java_import 'java.lang.System'

# Allow changes without restarting server - sometimes :)
configure {Sinatra::Application.reset!; use Rack::Reloader}

$stocks = [
#  { "id"=> "22144" ,"t" => "AAPL" ,"e" => "NASDAQ" ,"l" => "527.00" ,"l_cur" => "527.00" ,"s"=> "0" ,"ltt"=>"4=>00PM EST" ,"lt" => "Jan 4, 4=>00PM EST" ,"c" => "-15.10" ,"cp" => "-2.78" ,"ccol" => "chr" ,"eo" => "" ,"delay"=> "" ,"op" => "536.97" ,"hi" => "538.63" ,"lo" => "525.83" ,"vo" => "21.23M" ,"avvo" => "20.15M" ,"hi52" => "705.07" ,"lo52" => "418.66" ,"mc" => "495.74B" ,"pe" => "11.93" ,"fwpe" => "" ,"beta" => "1.21" ,"eps" => "44.16" ,"shares" => "940.69M" ,"inst_own" => "67%" ,"name" => "Apple Inc." ,"type" => "Company" }, 
#  { "id"=> "694653","t" => "GOOG" ,"e" => "NASDAQ" ,"l" => "737.97" ,"l_cur" => "737.97" ,"s"=> "0" ,"ltt"=>"4=>00PM EST" ,"lt" => "Jan 4, 4=>00PM EST" ,"c" => "+14.30" ,"cp" => "1.98" ,"ccol" => "chg" ,"eo" => "" ,"delay"=> "" ,"op" => "729.34" ,"hi" => "741.47" ,"lo" => "727.68" ,"vo" => "2.76M" ,"avvo" => "2.18M" ,"hi52" => "774.38" ,"lo52" => "556.52" ,"mc" => "242.49B" ,"pe" => "23.12" ,"fwpe" => "" ,"beta" => "1.08" ,"eps" => "31.92" ,"shares" => "328.59M" ,"inst_own" => "69%" ,"name" => "Google Inc" ,"type" => "Company" }, 
#  { "id"=> "10261" ,"t" => "DD" ,"e" => "NYSE" ,"l" => "45.73" ,"l_cur" => "45.73" ,"s"=> "0" ,"ltt"=>"4=>00PM EST" ,"lt" => "Jan 4, 4=>00PM EST" ,"c" => "+0.44" ,"cp" => "0.97" ,"ccol" => "chg" ,"eo" => "" ,"delay"=> "" ,"op" => "45.39" ,"hi" => "45.82" ,"lo" => "45.34" ,"vo" => "5.24M" ,"avvo" => "5.89M" ,"hi52" => "57.50" ,"lo52" => "41.67" ,"mc" => "42.64B" ,"pe" => "15.34" ,"fwpe" => "" ,"beta" => "1.46" ,"eps" => "2.98" ,"shares" => "932.47M" ,"inst_own" => "65%" ,"name" => "E I Du Pont De Nemours And Co" ,"type" => "Company" }
  ]

get '/' do
  Dir.entries('public').entries.map{|f|"<a href='#{f}'>#{f}</a><br/>" if f=~/.*\.html/}.join
end

get '/version' do
  "(Ruby Platform: #{RUBY_PLATFORM} Ruby Version: #{RUBY_VERSION}) Call From Java: #{System.currentTimeMillis()}"
end

get '/stocks' do
  $stocks.to_json
end

get '/stock/:t' do
  stock = $stocks.find{|e|e['t']==params['t']}
  if stock.nil?  
    status 404
  else
    status 200
    body(stock.to_json)
  end
end

delete '/stock/:t' do

  stock = $stocks.find{|e|e['t']==params['t']}
  if stock.nil?  
    status 404
  else
    $stocks.delete(stock)
    status 202
    body(stock.to_json)
  end
end

put  '/stock/:t' do
  o = JSON.parse(request.body.read)['data']
  puts "---\n  #{o['name']} \n---\n"
  $stocks << o
  status 200
end

# post