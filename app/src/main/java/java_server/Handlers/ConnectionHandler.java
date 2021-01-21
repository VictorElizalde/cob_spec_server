package java_server.Handlers;

import java_server.Parsers.RequestParser;
import java_server.Main.Request;
import java_server.Main.Response;
import java_server.Parsers.ResponseParser;
import java_server.Main.Routes;
import java_server.Helpers.StatusCode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConnectionHandler implements Runnable {
    private RequestHandler requestHandler;
    private String directory;
    private int port;

    public ConnectionHandler(RequestHandler requestHandler, int port, String directory) {
        this.requestHandler = requestHandler;
        this.port = port;
        this.directory = directory;
    }

    @Override
    public void run() {
        InputStream inputStream = requestHandler.getInputStream();
        RequestParser requestParser = new RequestParser(inputStream, directory);
        StatusCode statusCode = new StatusCode();
        Routes routes = new Routes(directory);
        Response response = new Response(statusCode, routes, port, directory);
        ResponseParser responseParser = new ResponseParser(response);

        try {
            Request request = requestParser.parse();
            System.out.println(request.getFullRequest());
            DataOutputStream dataOutputStream = new DataOutputStream(requestHandler.getOutputStream());
            dataOutputStream.write(responseParser.buildResponse(request));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestHandler.closeSocket();
    }
}