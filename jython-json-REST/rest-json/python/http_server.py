import SimpleHTTPServer
import SocketServer
import os

os.chdir('src/main/resources')
httpd = SocketServer.TCPServer(("", 8000), SimpleHTTPServer.SimpleHTTPRequestHandler)
print "serving at port 8000"
httpd.serve_forever()