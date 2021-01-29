package java_server.responders;

public class MethodNotAllowedResponder implements Responder {
    public byte[] body;

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.METHOD_NOT_ALLOWED;
    }

    @Override
    public void processResponse() {
        body = statusMessageCode.METHOD_NOT_ALLOWED.getBytes();
    }
}
