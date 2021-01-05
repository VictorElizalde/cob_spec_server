package java_server;

public class MethodNotAllowedResponder implements Responder {

    @Override
    public byte[] getMessageBody() {
        return "405 Method Not Allowed".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(405);
    }
}
