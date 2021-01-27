package java_server.main;

import java_server.handlers.ConnectionHandler;
import java_server.handlers.RequestHandler;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private String directory;

    public Server(int port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    public String runServer() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server Is Listening on port: " + port);
        System.out.println("Server Is Using directory: " + directory);

        while (true) {
            try {
                RequestHandler requestHandler = new RequestHandler(serverSocket.accept());
                pool.execute(new ConnectionHandler(requestHandler, port, directory));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
