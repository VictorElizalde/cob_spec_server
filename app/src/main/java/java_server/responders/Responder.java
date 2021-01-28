package java_server.responders;

import java_server.httpserver.StatusCode;

public interface Responder {
    public StatusCode statusMessageCode = new StatusCode();
    public byte[] getMessageBody();
    public String getStatusCode();
}
