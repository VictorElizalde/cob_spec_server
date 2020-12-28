package java_server;

public class Response {
    private Routes routes;
    private statusCode statusCode;
    private int port;

    public Response(statusCode statusCode, Routes routes, int port) {
        this.routes = routes;
        this.statusCode = statusCode;
        this.port = port;
    }

    public String getStatusMessage(Request request) {
        return routes.getHandler(request).getStatusCode(statusCode);
    }

    public byte[] getMessageBody(Request request) {
        return routes.getHandler(request).getMessageBody();
    }
}
