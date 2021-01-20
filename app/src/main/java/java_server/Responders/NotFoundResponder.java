package java_server.Responders;

public class NotFoundResponder implements Responder {
    @Override
    public byte[] getMessageBody() {
        return "404 File Not Found".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.NOT_FOUND;
    }
}
