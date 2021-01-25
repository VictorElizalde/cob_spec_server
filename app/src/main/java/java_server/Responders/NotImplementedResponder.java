package java_server.Responders;

public class NotImplementedResponder implements Responder {

    @Override
    public byte[] getMessageBody() {
        return statusMessageCode.NOT_IMPLEMENTED.getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.NOT_IMPLEMENTED;
    }
}
