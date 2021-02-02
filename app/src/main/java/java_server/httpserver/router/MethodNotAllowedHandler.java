package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.MethodNotAllowedResponder;
import java_server.responders.Responder;

public class MethodNotAllowedHandler {
    private Routes route;

    public MethodNotAllowedHandler(Routes route) {
        this.route = route;
    }
    public Responder getMethodNotAllowedResponder(Request request) {
        if (!route.isAValidMethod(request) && (route.isAnExistingFileInDirectory(route.getDirectoryFileNames(), request) || request.getURI().equals("logs")))
            return new MethodNotAllowedResponder();
        return null;
    }
}
