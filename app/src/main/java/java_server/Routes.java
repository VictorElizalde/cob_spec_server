package java_server;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Routes {
    private String directory;

    public Routes(String directory) {
        this.directory = directory;
    }

    public boolean isAValidMethod(Request request) {
        return isURIValid(request) && request.getHTTPMethod() == "GET";
    }

    private boolean isURIValid(Request request) {
        if (request.getURI() == "file1") {
            return true;
        }
        return false;
    }

    public DirectoryStream<Path> getDirectoryFileNames() throws IOException {
        return Files.newDirectoryStream(Paths.get(directory));
    }
}
