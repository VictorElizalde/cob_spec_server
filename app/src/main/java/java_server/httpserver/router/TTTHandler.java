package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.TTTResponder;
import java_server.responders.Responder;

import java.util.HashMap;

public class TTTHandler {

    private HashMap<String, Responder> TTTRouteMap = new HashMap<String, Responder>();

    private HashMap<String, Responder> getTTTRouteMap(Request request) {
        TTTRouteMap.put("GET", new TTTResponder(request.getHTTPMethod(), request.getURI(), request.getData()));
        TTTRouteMap.put("POST", new TTTResponder(request.getHTTPMethod(), request.getURI(), request.getData()));

        return TTTRouteMap;
    }

    public Responder getTTTResponder(Request request) {
        if (request.getURI().contains("ttt"))
            return getTTTRouteMap(request).get(request.getHTTPMethod());
        return null;
    }
}

