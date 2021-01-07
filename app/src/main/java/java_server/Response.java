package java_server;

import java.net.URLConnection;

public class Response {
    private Routes routes;
    private statusCode statusCode;
    private int port;
    private Responder responder;

    public Response(statusCode statusCode, Routes routes, int port) {
        this.routes = routes;
        this.statusCode = statusCode;
        this.port = port;
    }

    public void setResponder(Request request) {
        this.responder = routes.getHandler(request);
    }

    public String getStatusMessage(Request request) {
        return responder.getStatusCode(statusCode);
    }

    public byte[] getMessageBody(Request request) {
        return responder.getMessageBody();
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
        String httpMessageBodySize = Integer.toString(getMessageBody(request).length);
        return "Content-Length: " + httpMessageBodySize;
    }

    public String getAllowHeader(Request request) {
        return "Allow: " + routes.getOptions(request);
    }
}
