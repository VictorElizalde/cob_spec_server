/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package java_server;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ArgsParser argsParser = new ArgsParser(args);
        int port = argsParser.getPort();
        String directory = argsParser.getDirectory();
        Server server = new Server(port, directory);
        server.runServer();
    }
}
