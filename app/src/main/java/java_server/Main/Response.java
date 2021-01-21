package java_server.Main;

import java_server.Parsers.PartialContentParser;
import java_server.Responders.Responder;
import java_server.Helpers.StatusCode;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Response {
    private Routes routes;
    private StatusCode statusCode;
    private int port;
    private String directory;
    private Responder responder;
    private byte[] responseBody;

    public Response(StatusCode statusCode, Routes routes, int port, String directory) {
        this.routes = routes;
        this.statusCode = statusCode;
        this.port = port;
        this.directory = directory;
    }

    public void setResponder(Request request) {
        this.responder = routes.getHandler(request);
    }

    public String getStatusMessage(Request request) {
        return responder.getStatusCode();
    }

    public byte[] getMessageBody(Request request) {
        responseBody = responder.getMessageBody();
        return responseBody;
    }

    public String getLocation() {
        String portNumber = Integer.toString(port);
        return "Location: http://localhost:" + portNumber + "/";
    }

    public String getContentType(Request request) {
        String type = URLConnection.guessContentTypeFromName(request.getURI());
        if (type == null) {
            type = "text/html";
        }

        return "Content-Type: " + type;
    }

    public String getContentLength() {
        String httpMessageBodySize = Integer.toString(responseBody.length);
        return "Content-Length: " + httpMessageBodySize;
    }

    public String getContentRange(Request request) {
        String[] minMaxRange = new String[2];
        minMaxRange[0] = "*";
        String fullContentRange;
        PartialContentParser partialContentParser = new PartialContentParser(request.getByteRange());

        try {
            minMaxRange = partialContentParser.getRanges(Files.readAllBytes(Paths.get(directory + "/" + request.getURI())));;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (minMaxRange[0].equals("*")) {
            fullContentRange = "Content-Range: bytes " + minMaxRange[0] + "/" + request.getByteLength();
        } else {
            fullContentRange = "Content-Range: bytes " + minMaxRange[0] + "-" + (Integer.parseInt(minMaxRange[1]) - 1) + "/" + request.getByteLength();
        }

        return fullContentRange;

    }

    public String getAllowHeader(Request request) {
        return "Allow: " + routes.getOptions(request);
    }
}
