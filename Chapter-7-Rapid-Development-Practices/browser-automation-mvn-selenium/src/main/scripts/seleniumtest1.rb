require 'rubygems'
require 'selenium-webdriver'

#driver = Selenium::WebDriver.for :firefox
driver = Selenium::WebDriver.for :safari
driver.get "http://google.com"

element = driver.find_element :name => "q"
element.send_keys "Cheese!"

#element.clear
#element.send_keys "JavaScript"

# http://docs.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
#driver.find_element(:partial_link_text, 'Mozilla').click
#driver.find_element(:id, "x")

element.submit

puts "Page title is #{driver.title}"

wait = Selenium::WebDriver::Wait.new(:timeout => 10)
wait.until { driver.title.downcase.start_with? "cheese!" }

puts "Page title is #{driver.title}"

driver.quit