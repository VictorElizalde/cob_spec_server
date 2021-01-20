package java_server.Responders;

public class NotImplementedResponder implements Responder {

    @Override
    public byte[] getMessageBody() {
        return "501 Not Implemented".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.NOT_IMPLEMENTED;
    }
}
