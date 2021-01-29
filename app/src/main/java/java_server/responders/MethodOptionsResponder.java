package java_server.responders;

public class MethodOptionsResponder implements Responder {
    private String options;
    public byte[] body;

    public MethodOptionsResponder(String options) {
        this.options = options;
    }

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }

    @Override
    public void processResponse() {
        body = options.getBytes();
    }
}
