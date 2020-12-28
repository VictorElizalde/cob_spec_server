package java_server;

public interface Responder {
    public byte[] getMessageBody();
    public String getStatusCode(statusCode statusCode);
}
