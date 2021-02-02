package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.MethodOptionsResponder;
import java_server.responders.Responder;

public class MethodOptionsHandler {
    private Routes route;

    public MethodOptionsHandler(Routes route) {
        this.route = route;
    }

    public Responder getMethodOptionsResponder(Request request) {
        if (request.getHTTPMethod().equals("OPTIONS"))
            return new MethodOptionsResponder(route.getOptions(request));
        return null;
    }
}
