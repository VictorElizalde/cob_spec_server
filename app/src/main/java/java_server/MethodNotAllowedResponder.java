package java_server;

public class MethodNotAllowedResponder implements Responder {

    @Override
    public byte[] getMessageBody() {
        return "405 Method Not Allowed".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.METHOD_NOT_ALLOWED;
    }
}
