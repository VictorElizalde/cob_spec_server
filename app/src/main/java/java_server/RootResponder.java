package java_server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RootResponder implements Responder {
    private String serverViewsDirectory;

    public RootResponder(String serverViewsDirectory) {
        this.serverViewsDirectory = serverViewsDirectory;
    }

    @Override
    public byte[] getMessageBody() {
        try {
            return Files.readAllBytes(Paths.get(serverViewsDirectory + "/index.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "File Could Not Be Read".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(200);
    }
}
