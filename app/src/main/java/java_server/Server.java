package java_server;

import java.io.*;
import java.net.*;

public class Server {
     public String runServer() {
         String response = "HTTP/1.1 200 OK\r\n\r\n";
         try (
             ServerSocket serverSocket = new ServerSocket(5000);
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
