package java_server;

public class NotImplementedResponder implements Responder {

    @Override
    public byte[] getMessageBody() {
        return "501 Not Implemented".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(501);
    }
}
