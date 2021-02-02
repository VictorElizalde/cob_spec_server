package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.CRUDResponder;
import java_server.responders.FileResponder;
import java_server.responders.Responder;

import java.util.HashMap;

public class CRUDHandler {
    private String directory;
    private Routes route;

    private HashMap<String, Responder> CRUDRouteMap = new HashMap<String, Responder>();

    public CRUDHandler(String directory, Routes route) {
        this.directory = directory;
        this.route = route;
    }

    private HashMap<String, Responder> getCRUDRouteMap(Request request) {
        CRUDRouteMap.put("GET", new FileResponder(directory, request.getURI()));
        CRUDRouteMap.put("PUT", new CRUDResponder(directory, request.getHTTPMethod(), request.getURI(), request.getData()));
        CRUDRouteMap.put("DELETE", new CRUDResponder(directory, request.getHTTPMethod(), request.getURI(), request.getData()));

        return CRUDRouteMap;
    }

    public Responder getCRUDResponder(Request request) {
        if (request.getHTTPMethod().equals("PUT") || (request.getHTTPMethod().equals("DELETE") && route.isAnExistingFileInDirectory(route.getDirectoryFileNames(), request)))
            return getCRUDRouteMap(request).get(request.getHTTPMethod());
        return null;
    }
}
