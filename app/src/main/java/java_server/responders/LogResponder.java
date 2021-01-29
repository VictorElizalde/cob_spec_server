package java_server.responders;

public class LogResponder implements Responder {
    public byte[] body;

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
        body = "".getBytes();
    }
}
