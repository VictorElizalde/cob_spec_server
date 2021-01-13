package java_server;

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
        RequestParser requestParser = new RequestParser(inputStream);
        statusCode statusCode = new statusCode();
        Routes routes = new Routes(directory);
        Response response = new Response(statusCode, routes, port);
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
