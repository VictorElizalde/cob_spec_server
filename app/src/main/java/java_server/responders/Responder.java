package java_server.Responders;

import java_server.Helpers.StatusCode;

public interface Responder {
    public StatusCode statusMessageCode = new StatusCode();
    public byte[] getMessageBody();
    public String getStatusCode();
}
