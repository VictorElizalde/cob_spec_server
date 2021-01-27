package java_server.Responders;

public class NotFoundResponder implements Responder {
    @Override
    public byte[] getMessageBody() {
        return statusMessageCode.NOT_FOUND.getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.NOT_FOUND;
    }
}
