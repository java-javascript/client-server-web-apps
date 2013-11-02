# TODO - make generic.  Count LOC by type (server side or client side)


a = `find jython-json-REST/rest-json -name '*.*' | xargs wc -l`.split
h=Hash[a.each_slice(2).entries.map{|a|a.reverse}]

h.each_pair{|k,v|
  puts "#{(v.to_i*100/h['total'].to_i)}% #{v} #{k}" unless k=='total'
}

