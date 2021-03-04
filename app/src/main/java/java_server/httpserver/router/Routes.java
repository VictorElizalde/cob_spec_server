package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.*;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Routes {
    private String directory;
    private Responder responder;
    private PartialContentHandler partialContentHandler;
    private CRUDHandler crudHandler;
    private NotImplementedHandler notImplementedHandler;
    private HeadHandler headHandler;
    private MethodOptionsHandler methodOptionsHandler;
    private MethodNotAllowedHandler methodNotAllowedHandler;
    private TTTHandler tttHandler;

    private HashMap<String, HashMap<String, Responder>> routesMap = new HashMap<String, HashMap<String, Responder>>();
    private HashMap<String, Responder> rootMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> fileRouteMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> basicAuthRouteMap = new HashMap<String, Responder>();

    public Routes(String directory) {
        this.directory = directory;
        this.partialContentHandler = new PartialContentHandler(directory, this);
        this.crudHandler = new CRUDHandler(directory, this);
        this.notImplementedHandler = new NotImplementedHandler();
        this.headHandler = new HeadHandler();
        this.methodOptionsHandler = new MethodOptionsHandler(this);
        this.methodNotAllowedHandler = new MethodNotAllowedHandler(this);
        this.tttHandler = new TTTHandler();
    }

    public HashMap<String, HashMap<String, Responder>> getRoutesMap(Request request) {
        routesMap.put("/", getRootMap());
        routesMap.put("logs", getBasicAuthMap(request));

        File routesDirectory = new File(directory);

        if (routesDirectory != null) {
            for (String file : routesDirectory.list()) {
                routesMap.put(file, getFileRouteMap(request));
            }
        }

        return routesMap;
    }

    private HashMap<String, Responder> getRootMap() {
        rootMap.put("GET", new RootResponder(directory));

        return rootMap;
    }

    private HashMap<String, Responder> getFileRouteMap(Request request) {
        FileResponder fileResponder = new FileResponder(directory, request.getURI());
        fileRouteMap.put("GET", fileResponder);
        fileRouteMap.put("HEAD", fileResponder);
        fileRouteMap.put("OPTIONS", fileResponder);
        fileRouteMap.put("PUT", fileResponder);
        fileRouteMap.put("DELETE", fileResponder);
        return fileRouteMap;
    }

    private HashMap<String, Responder> getBasicAuthMap(Request request) {
        BasicAuthResponder basicAuthResponder = new BasicAuthResponder(directory, request.getURI(), request.getBasicAuthCredentials());
        basicAuthRouteMap.put("GET", basicAuthResponder);
        basicAuthRouteMap.put("HEAD", basicAuthResponder);
        basicAuthRouteMap.put("OPTIONS", basicAuthResponder);
        return basicAuthRouteMap;
    }

    public String getOptions(Request request) {
        try {
            return String.join(",", getRoutesMap(request).get(request.getURI()).keySet());
        } catch (NullPointerException e) {

            return "GET,HEAD,OPTIONS,PUT,DELETE";
        }
    }

    public boolean isAValidMethod(Request request) {
        return isURIValid(request) && getRoutesMap(request).get(request.getURI()).containsKey(request.getHTTPMethod());
    }

    private Set<String> getRouteKeySet(Request request) {
        return getRoutesMap(request).keySet();
    }

    private boolean isURIValid(Request request) {
        for (String uri : getRouteKeySet(request)) {
            if (request.getURI().equals(uri))  return true;
        }

        return false;
    }

    public String[] getDirectoryFileNames() {
        File file = new File(directory);
        return file.list();
    }

    public boolean isAnExistingFileInDirectory(String[] fileList, Request request) {
        String URI = request.getURI();
        for (String file : fileList) {
            if (file.equals(URI)) return true;
        }

        return false;
    }

    public Responder getResponder(Request request) {
        return matchResponder(request);
    }

    public Responder matchResponder(Request request) {
        if (tttResponder(request) != null) return responder;
        if (partialContentResponder(request) != null) return responder;
        if (crudResponder(request) != null) return responder;
        if (notImplementedResponder(request) != null) return responder;
        if (headResponder(request) != null) return responder;
        if (methodOptionsResponder(request) != null) return responder;
        if (isAValidMethod(request)) return getRoutesMap(request).get(request.getURI()).get(request.getHTTPMethod());
        if (methodNotAllowedResponder(request) != null) return responder;
        return new NotFoundResponder();
    }

    private Responder tttResponder(Request request) {
        responder = tttHandler.getTTTResponder(request);
        return responder;
    }

    private Responder partialContentResponder(Request request) {
        responder = partialContentHandler.getPartialContentResponder(request);
        return responder;
    }

    private Responder crudResponder(Request request) {
        responder = crudHandler.getCRUDResponder(request);
        return responder;
    }

    private Responder notImplementedResponder(Request request) {
        responder = notImplementedHandler.getNotImplementedResponder(request);
        return responder;
    }

    private Responder headResponder(Request request) {
        responder = headHandler.getHeadResponder(request);
        return responder;
    }

    private Responder methodOptionsResponder(Request request) {
        responder = methodOptionsHandler.getMethodOptionsResponder(request);
        return responder;
    }

    private Responder methodNotAllowedResponder(Request request) {
        responder = methodNotAllowedHandler.getMethodNotAllowedResponder(request);
        return responder;
    }
}
