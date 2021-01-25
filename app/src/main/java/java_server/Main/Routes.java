package java_server.Main;

import java_server.Responders.*;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Routes {
    private String directory;

    private HashMap<String, HashMap<String, Responder>> routesMap = new HashMap<String, HashMap<String, Responder>>();
    private HashMap<String, Responder> rootMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> fileRouteMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> basicAuthRouteMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> partialContentMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> CRUDRouteMap = new HashMap<String, Responder>();

    public Routes(String directory) {
        this.directory = directory;
    }

    private HashMap<String, HashMap<String, Responder>> getRoutesMap(Request request) {
        routesMap.put("/", getRootMap());
        routesMap.put("logs", getBasicAuthMap(request));
        routesMap.put("requests", getBasicAuthMap(request));

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

    private HashMap<String, Responder> getCRUDRouteMap(Request request) {
        CRUDRouteMap.put("GET", new FileResponder(directory, request.getURI()));
        CRUDRouteMap.put("PUT", new CRUDResponder(directory, request.getHTTPMethod(), request.getURI(), request.getData()));
        CRUDRouteMap.put("DELETE", new CRUDResponder(directory, request.getHTTPMethod(), request.getURI(), request.getData()));

        return CRUDRouteMap;
    }

    public String getOptions(Request request) {
        try {
            return String.join(",", getRoutesMap(request).get(request.getURI()).keySet());
        } catch (NullPointerException e) {

            return "GET,HEAD,OPTIONS,PUT,DELETE";
        }
    }

    private HashMap<String, Responder> getPartialContentMap(Request request) {
        partialContentMap.put("GET", new PartialContentResponder(directory, request.getURI(), request.getByteRange()));

        return partialContentMap;
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

    public Responder getHandler(Request request) {
        if (request.getByteRange() != null && !request.getByteRange().equals("Range not given") && isAnExistingFileInDirectory(getDirectoryFileNames(), request)) {
            return getPartialContentMap(request).get(request.getHTTPMethod());
        }
        if (request.getHTTPMethod().equals("PUT") || (request.getHTTPMethod().equals("DELETE") && isAnExistingFileInDirectory(getDirectoryFileNames(), request))) {
            return getCRUDRouteMap(request).get(request.getHTTPMethod());
        }
        if (!"GET,POST,HEAD,OPTIONS,PUT,DELETE".contains(request.getHTTPMethod())) return new NotImplementedResponder();
        if (request.getHTTPMethod().equals("HEAD") && request.getURI().equals("/")) return new HeadResponder();
        if (request.getHTTPMethod().equals("OPTIONS")) return new MethodOptionsResponder(getOptions(request));
        if (isAValidMethod(request)) return getRoutesMap(request).get(request.getURI()).get(request.getHTTPMethod());
        if (!isAValidMethod(request) && isAnExistingFileInDirectory(getDirectoryFileNames(), request)) return new MethodNotAllowedResponder();
        return new NotFoundResponder();
    }
}
