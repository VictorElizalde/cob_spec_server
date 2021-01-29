package java_server.responders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileResponder implements Responder {
    private String directory;
    private String uri;
    public byte[] body;

    public FileResponder(String directory, String uri) {
        this.directory = directory;
        this.uri = uri;
    }

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }

    @Override
    public void processResponse() {
        try {
            body = Files.readAllBytes(getPath());
        } catch (IOException e) {
            body = "File Could Not Be Read".getBytes();
        }
    }

    private Path getPath() {
        return Paths.get(directory + "/" + uri).toAbsolutePath();
    }
}

