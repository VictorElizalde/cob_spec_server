package java_server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RootResponder implements Responder {
    private String serverViewsDirectory;
    private String directoryLinks;

    public RootResponder(String serverViewsDirectory) {
        this.serverViewsDirectory = serverViewsDirectory;
    }

    @Override
    public byte[] getMessageBody() {
        File directory = new File(serverViewsDirectory);

        if (directory != null) {
            directoryLinks = "";
            for (String file : directory.list()) {
                directoryLinks += addLinkHTML(file);
            }

            return directoryLinks.getBytes();
        }

        return "File Could Not Be Read".getBytes();
    }

    @Override
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(200);
    }

    private String addLinkHTML(String fileName){
        return "<li> <a href='/" + fileName + "'>" + fileName + "</a> </li>";
    }
}
