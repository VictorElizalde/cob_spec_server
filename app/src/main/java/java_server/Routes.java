package java_server;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Routes {
    private String directory;

    private HashMap<String, HashMap<String, Responder>> routesMap = new HashMap<String, HashMap<String, Responder>>();
    private HashMap<String, Responder> rootMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> fileRouteMap = new HashMap<String, Responder>();
    private HashMap<String, Responder> logRouteMap = new HashMap<String, Responder>();


    public Routes(String directory) {
        this.directory = directory;
    }

    private HashMap<String, HashMap<String, Responder>> getRoutesMap(Request request) {
        routesMap.put("/", getRootMap());
        routesMap.put("file1", getFileRouteMap(request));
        routesMap.put("file2", getFileRouteMap(request));
        routesMap.put("image.jpeg", getFileRouteMap(request));
        routesMap.put("image.png", getFileRouteMap(request));
        routesMap.put("image.gif", getFileRouteMap(request));
        routesMap.put("text-file.txt", getFileRouteMap(request));
        routesMap.put("logs", getLogRouteMap(request));

        return routesMap;
    }

    private HashMap<String, Responder> getRootMap() {
        rootMap.put("GET", new RootResponder("/Users/victorelizalde/Documents/Github/java_server/default-server-views"));

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

    private HashMap<String, Responder> getLogRouteMap(Request request) {
        LogResponder logResponder = new LogResponder();
        logRouteMap.put("GET", logResponder);
        logRouteMap.put("HEAD", logResponder);
        logRouteMap.put("OPTIONS", logResponder);
        return logRouteMap;
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
        File file = new File("/Users/victorelizalde/Documents/Github/cob_spec/public");
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
        if (isAValidMethod(request)) return getRoutesMap(request).get(request.getURI()).get(request.getHTTPMethod());
        if (request.getHTTPMethod().equals("HEAD") && request.getURI().equals("/")) return new HeadResponder();
        if (request.getHTTPMethod().equals("OPTIONS")) return new MethodOptionsResponder(getOptions(request));
        if (!isAValidMethod(request)) return new MethodNotAllowedResponder();
        return new NotFoundResponder();
    }
}
