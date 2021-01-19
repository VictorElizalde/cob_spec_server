package java_server;

public interface Responder {
    public StatusCode statusMessageCode = new StatusCode();
    public byte[] getMessageBody();
    public String getStatusCode();
}
