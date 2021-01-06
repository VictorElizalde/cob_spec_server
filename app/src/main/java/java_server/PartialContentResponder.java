package java_server;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PartialContentResponder implements Responder {
    private String directory;
    private String uri;
    private String byteRange;
    private Integer statusCode;

    public PartialContentResponder(String directory, String uri, String byteRange) {
        this.directory = directory;
        this.uri = uri;
        this.byteRange = byteRange;
        this.statusCode = 206;
    }

    @Override
    public byte[] getMessageBody() {
        PartialContentParser partialContentParser = new PartialContentParser(byteRange);
        try {
            return partialContentParser.getPartialContent(Files.readAllBytes(Paths.get(directory + "/" + uri)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.statusCode = 416;
        return "Request Invalid".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(this.statusCode);
    }
}
