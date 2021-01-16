package java_server;

public interface Responder {
    public statusCode statusMessageCode = new statusCode();
    public byte[] getMessageBody();
    public String getStatusCode();
}
