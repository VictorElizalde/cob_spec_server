package java_server.Responders;

public class HeadResponder implements Responder {
    @Override
    public byte[] getMessageBody() {
        return "".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }
}
