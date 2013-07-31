# Java
See http://docs.seleniumhq.org/docs/03_webdriver.jsp#chapter03-reference
mvn clean install exec:java

# Ruby:
gem install selenium-webdriver
cd src/main/scripts
ruby seleniumtest1.rb

# Notes:
- Oriented towards Java and Firefox, but support in many other languages and browsers.
- HtmlUnitDriver is a headless (no GUI) java based browser implementation.   
-- Selenium Server is required to use this driver with any other language binding.
-- HtmlUnitDriver(true) did not work, which calls into question JavaScript support for this driver (Rhino).

- Firefox, Safari, and HtmlUnitDriver worked on OSX out of the box
- Additional work is required to use Chrome.  Did not test Android, IE is Windows only, and iPhone is deprecated

- Elements can be located/manipulated by id, css classname, tag name, name attribute, link, link text, partial link text, 
  css selector, xpath, or executing arbitrary JavaScript

- Lots of special functionality (cookies, browser history, etc)- even drag and drop


