import groovy.json.JsonSlurper

println "*** JSON Groovy ***"

def json = new File("data/test.json").text

new JsonSlurper().parseText(json).each { println it.title }