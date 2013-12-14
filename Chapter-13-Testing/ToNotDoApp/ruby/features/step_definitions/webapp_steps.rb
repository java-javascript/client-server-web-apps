# Capybara/Selenium Specific
require "capybara"
require "capybara/dsl"

Capybara.default_driver = :selenium
Capybara.run_server = false
Capybara.default_wait_time = 3

VALID_LOGIN_URL='http://guest:guest@localhost:8080/'


# API Test
require 'rest-client'
require 'json'
API_URL='http://guest:guest@localhost:8080/api/tonotdo'


Given(/^correct credentials$/) do
end

# Capybara/Selenium Specific
When(/^I load the page$/) do
  Capybara.visit(VALID_LOGIN_URL)
end

Then(/^I should be able to log in$/) do
  Capybara.page.has_content?('To NOT Do')
end


# API Test
When(/^I add and list To Not Do items$/) do
  content = {'lastUpdated'=>'12/14/2013 10:11:12', 'description'=>'Test Item', 'priority'=>1, 'externalKey'=>'testExternalKey' }.to_json;
  RestClient.put(API_URL, content, :content_type => :json, :accept => :json)
end

Then(/^the returned list should contain the item I added$/) do
  JSON.parse(RestClient.get(API_URL, :content_type => :json, :accept => :json)).first['description']=='Test Item'
end


