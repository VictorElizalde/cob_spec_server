package java_server;

public class HeadResponder implements Responder {
    @Override
    public byte[] getMessageBody() {
        return "".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(200);
    }
}
