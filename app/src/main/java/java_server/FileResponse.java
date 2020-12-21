package java_server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileResponse {
    private String directory;
    private String uri;

    public FileResponse(String directory, String uri) {
        this.directory = directory;
        this.uri = uri;
    }

    public byte[] getMessageBody() {
        try {
            return Files.readAllBytes(getPath());
        } catch (IOException e) {

            return "File Could Not Be Read".getBytes();
        }
    }

    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(200);
    }

    private Path getPath() {
        return Paths.get(directory + "/" + uri).toAbsolutePath();
    }
}
