package java_server.responders.partialcontent;

import java_server.responders.Responder;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PartialContentResponder implements Responder {
    private String directory;
    private String uri;
    private String byteRange;
    public byte[] body;

    public PartialContentResponder(String directory, String uri, String byteRange) {
        this.directory = directory;
        this.uri = uri;
        this.byteRange = byteRange;
        statusMessageCode.setActiveStatus(statusMessageCode.PARTIAL_CONTENT);
    }

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.getActiveStatus();
    }

    @Override
    public void processResponse() {
        PartialContentParser partialContentParser = new PartialContentParser(byteRange);
        try {
            body = partialContentParser.getPartialContent(Files.readAllBytes(Paths.get(directory + "/" + uri)));
        } catch (Exception e) {
            statusMessageCode.setActiveStatus(statusMessageCode.REQUEST_RANGE_NOT_SATISFIABLE);
            body = "Request Invalid".getBytes();
            e.printStackTrace();
        }
    }
}
