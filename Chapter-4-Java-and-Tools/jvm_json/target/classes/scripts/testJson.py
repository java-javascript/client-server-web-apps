import json

print('*** JSON Jython ***')

for item in json.loads(open("data/test.json").read()):
	print item['title']
