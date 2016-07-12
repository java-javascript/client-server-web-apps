# Jasmine itself has no dependencies
# To run Jasmine tests, open /Animals/test/SpecRunner.html in a browser

#
# Initial setup install node and the node package manager (npm)
#

# Info on Karma/Testacular
# http://karma-runner.github.com
# https://github.com/vojtajina/testacular/

npm install karma -g

# To see help
karma --help

# Project configuration file was initially created using
karma init

# To run tests every time a file has changed
karma start


# Info on docco http://jashkenas.github.io/docco/
npm install docco

# Note also - https://github.com/jashkenas/docco/issues/119
sudo easy_install pygments

docco app/*.js


