package java_server.Responders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class CRUDResponder implements Responder {
    private String directory;
    private String uri;
    private String httpMethod;
    private String statusCode;
    private String content;
    private static HashMap<String, byte[]> CRUDMap = new HashMap<String, byte[]>();

    public CRUDResponder(String directory, String httpMethod, String uri, String content) {
        this.directory = directory;
        this.httpMethod = httpMethod;
        this.uri = uri;
        this.content = content;
    }

    @Override
    public byte[] getMessageBody() {
        try {
            performCRUD();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return Files.readAllBytes(getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "File Could Not Be Read".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    private Path performCRUD() throws IOException {
        if (httpMethod.equals("DELETE")){
            return deletePath();
        } else if(Files.exists(getPath())){
            return updatePath();
        }

        return createPath();
    }

    private Path createPath() throws IOException {
        statusCode = statusMessageCode.CREATED;
        return writeToFile();
    }

    private Path updatePath() throws IOException {
        statusCode = statusMessageCode.OK;
        return writeToFile();
    }

    private Path deletePath() throws IOException {
        Files.delete(getPath());
        statusCode = statusMessageCode.OK;
        return getPath();
    }

    private Path writeToFile() throws IOException {
        return Files.write(getPath(), content.getBytes());
    }

    private Path getPath() {
        return Paths.get(directory + "/" + uri).toAbsolutePath();
    }
}
