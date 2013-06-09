import java.io.*;
import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;

public class HttpJsonServer {

    public static void main(String[] args) throws Exception {
    	
        HttpServer server = HttpServer.create(
           new InetSocketAddress(8000), 0
        );
        server.createContext("/", new MyHandler());
        server.setExecutor(null); 
		System.out.println("Starting server on port: 8000");
        server.start();
    }

    static class MyHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            String response = "{\"testResponse\":\"Hello World\"}";
			t.getResponseHeaders().set(
                "Content-Type", 
                "application/json"
            );
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
