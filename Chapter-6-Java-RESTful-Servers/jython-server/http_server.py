import SimpleHTTPServer
import SocketServer
import os

os.chdir('root')
print "serving at port 8000"
SocketServer.TCPServer(("", 8000), SimpleHTTPServer.SimpleHTTPRequestHandler).serve_forever()