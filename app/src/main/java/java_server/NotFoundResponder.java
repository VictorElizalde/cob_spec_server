package java_server;

public class NotFoundResponder implements Responder {
    @Override
    public byte[] getMessageBody() {
        return "404 File Not Found".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(404);
    }
}
