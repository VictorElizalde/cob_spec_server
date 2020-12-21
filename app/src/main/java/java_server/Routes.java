package java_server;

import java.io.File;

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

    public boolean isAnExistingFileInDirectory(String[] fileList, Request request) {
        String URI = request.getURI();
        for (String file : fileList) {
            if (file.equals(URI)) return true;
        }

        return false;
    }
}
