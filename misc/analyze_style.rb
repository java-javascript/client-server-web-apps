#
#  When writing, it is helpful to review word usage to see if the author has gotten into a rut using the same words
#  This program analyzes an HTML doc (exported from Open Office) and displays the word counts/locations of each word
#

if ARGV[0].nil?
  puts "\n  Usage : #{__FILE__} <name of html file exported from Open Office>\n\n"
  exit
end

%w{rubygems hpricot}.each{|r|require r}                                  # Required libraries
doc = Hpricot(open(ARGV[0]))                                   # Export from Open Office Write to html
(doc/'style').remove()                                                   # Remove the style element
text = (doc/"//*/text()").join("\n")                                     # Create an array of text values
%w{'. ◦. ) ( " ' $ , . : * - ↔ ▪  “ ” – ~ =}.each{|l|text.gsub!(l,'')} # List of characters to remove
text.gsub!(/[0-9]/,'')                                                   # Strip out numbers
text.gsub!('          ',' ')                                             # etc.

a=text.downcase.split                                                    # Downcase the letters
h={}                                                                     # Create an empty hash
a.each_with_index{|w,i| h[w].nil? ? h[w]=[i] :h[w]<<i }                  # For each word, save the word as key, and put its index into the value array
h.keys.sort.each{|k|puts "#{h[k].size.to_s.ljust(10)} #{k.ljust(25)} #{h[k].join(', ')}"}  # Print out the number of occurences, key, and a list of indices
# Pipe to sort -n if needed