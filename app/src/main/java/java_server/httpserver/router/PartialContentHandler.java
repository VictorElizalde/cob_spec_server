package java_server.httpserver.router;

import java_server.httpserver.Request;
import java_server.responders.Responder;
import java_server.responders.partialcontent.PartialContentResponder;

import java.util.HashMap;

public class PartialContentHandler {
    private String directory;
    private Routes route;

    private HashMap<String, Responder> partialContentMap = new HashMap<String, Responder>();

    public PartialContentHandler(String directory, Routes route) {
        this.directory = directory;
        this.route = route;
    }

    private HashMap<String, Responder> getPartialContentMap(Request request) {
        partialContentMap.put("GET", new PartialContentResponder(directory, request.getURI(), request.getByteRange()));

        return partialContentMap;
    }

    public Responder getPartialContentResponder(Request request) {
        if (request.getByteRange() != null && !request.getByteRange().equals("Range not given") && route.isAnExistingFileInDirectory(route.getDirectoryFileNames(), request))
            return getPartialContentMap(request).get(request.getHTTPMethod());
        return null;
    }
}
