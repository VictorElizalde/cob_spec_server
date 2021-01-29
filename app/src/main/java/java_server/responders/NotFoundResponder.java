package java_server.responders;

public class NotFoundResponder implements Responder {
    public byte[] body;

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.NOT_FOUND;
    }

    @Override
    public void processResponse() {
        body = statusMessageCode.NOT_FOUND.getBytes();
    }
}
