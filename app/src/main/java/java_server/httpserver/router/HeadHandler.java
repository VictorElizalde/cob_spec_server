package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.HeadResponder;
import java_server.responders.Responder;

public class HeadHandler {
    public Responder getHeadResponder(Request request) {
        if (request.getHTTPMethod().equals("HEAD") && request.getURI().equals("/"))
            return new HeadResponder();
        return null;
    }
}
