package java_server.responders;

public class LogResponder implements Responder {
    @Override
    public byte[] getMessageBody() {
        return "".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }
}
