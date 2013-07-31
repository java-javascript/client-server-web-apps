# Notes:  http://newtriks.com/2013/06/11/automating-angularjs-with-yeoman-grunt-and-bower/

sudo npm install -g yo grunt-cli bower karma generator-angular generator-karma

mkdir angular-yeoman 
cd angular-yeoman 
yo angular

npm install

# Run Tests
grunt karma

# To eliminate the one warning
In karma.conf.js comment out 'test/mock/**/*.js'.

# Run Server
grunt server

The browser displays - trace through app.js, index.html, main.js, main.html

See http://jsfiddle.net/8zVxH/1/

Get the stock price client side, then use the webapp.rb from the jruby-sinatra-json-REST