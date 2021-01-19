package java_server;

import java.net.URLConnection;

public class Response {
    private Routes routes;
    private StatusCode statusCode;
    private int port;
    private Responder responder;
    private byte[] responseBody;

    public Response(StatusCode statusCode, Routes routes, int port) {
        this.routes = routes;
        this.statusCode = statusCode;
        this.port = port;
    }

    public void setResponder(Request request) {
        this.responder = routes.getHandler(request);
    }

    public String getStatusMessage(Request request) {
        return responder.getStatusCode();
    }

    public byte[] getMessageBody(Request request) {
        responseBody = responder.getMessageBody();
        return responseBody;
    }

    public String getLocation() {
        String portNumber = Integer.toString(port);
        return "Location: http://localhost:" + portNumber + "/";
    }

    public String getContentType(Request request) {
        String type = URLConnection.guessContentTypeFromName(request.getURI());
        if (type == null) {
            type = "text/html";
        }

        return "Content-Type: " + type;
    }

    public String getContentLength(Request request) {
        String httpMessageBodySize = Integer.toString(responseBody.length);
        return "Content-Length: " + httpMessageBodySize;
    }

    public String getAllowHeader(Request request) {
        return "Allow: " + routes.getOptions(request);
    }
}
