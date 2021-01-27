package java_server.responders;

import java_server.parsers.PartialContentParser;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PartialContentResponder implements Responder {
    private String directory;
    private String uri;
    private String byteRange;

    public PartialContentResponder(String directory, String uri, String byteRange) {
        this.directory = directory;
        this.uri = uri;
        this.byteRange = byteRange;
        statusMessageCode.setActiveStatus(statusMessageCode.PARTIAL_CONTENT);
    }

    @Override
    public byte[] getMessageBody() {
        PartialContentParser partialContentParser = new PartialContentParser(byteRange);
        try {
            return partialContentParser.getPartialContent(Files.readAllBytes(Paths.get(directory + "/" + uri)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        statusMessageCode.setActiveStatus(statusMessageCode.REQUEST_RANGE_NOT_SATISFIABLE);
        return "Request Invalid".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.getActiveStatus();
    }
}
