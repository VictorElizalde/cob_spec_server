package java_server;

import java.io.File;
import java.util.Set;

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

    public String[] getDirectoryFileNames() {
        File file = new File(directory);
        return file.list();
    }
}
