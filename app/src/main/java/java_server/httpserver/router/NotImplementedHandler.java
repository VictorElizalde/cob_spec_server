package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.NotImplementedResponder;
import java_server.responders.Responder;

public class NotImplementedHandler {
    public Responder getNotImplementedResponder(Request request) {
        if (!"GET,POST,HEAD,OPTIONS,PUT,DELETE".contains(request.getHTTPMethod()))
            return new NotImplementedResponder();
        return null;
    }
}
