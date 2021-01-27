package java_server.Responders;

public class MethodNotAllowedResponder implements Responder {

    @Override
    public byte[] getMessageBody() {
        return statusMessageCode.METHOD_NOT_ALLOWED.getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.METHOD_NOT_ALLOWED;
    }
}
