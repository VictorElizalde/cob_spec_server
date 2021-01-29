package java_server.responders;

public class NotImplementedResponder implements Responder {
    public byte[] body;

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.NOT_IMPLEMENTED;
    }

    @Override
    public void processResponse() {
        body = statusMessageCode.NOT_IMPLEMENTED.getBytes();
    }
}
