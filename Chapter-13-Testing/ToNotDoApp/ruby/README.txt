Start the server in another window
mvn clean install jetty:run


(if you don't need to run tests:  -D skipTests )


# Start with the Gemfile

rvm list

Choose Ruby 2.0.0

rvm list <-- local
rvm list known <-- what's available

gem install bundler
bundle

cucumber

No such file or directory - features. Please create a features directory to get started. (Errno::ENOENT)

mkdir features

cucumber
 0 scenarios
 0 steps
 0m0.000s


Add login.feature

this will fail but give you ruby code to add these
mkdir features/step_definitions
touch features/step_definitions/webapp_steps.rb

Add code to webapp_steps.rb
