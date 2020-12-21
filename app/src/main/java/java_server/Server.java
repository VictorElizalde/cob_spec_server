package java_server;

import java.io.*;
import java.net.*;

public class Server {
    private int port;
    private String directory;

    public Server(int port, String directory) {
        this.port = port;
        this.directory = directory;
    }

    public String runServer() {
        String response = "HTTP/1.1 200 OK\r\n\r\n";
        try (
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            out.print(response);
        } catch(IOException exception){
            System.out.println();
        }
        return response;
    }
}
