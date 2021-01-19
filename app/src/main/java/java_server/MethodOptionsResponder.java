package java_server;

public class MethodOptionsResponder implements Responder {
    private String options;

    public MethodOptionsResponder(String options) {
        this.options = options;
    }

    @Override
    public byte[] getMessageBody() {
        return options.getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }
}
