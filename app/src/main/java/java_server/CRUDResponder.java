package java_server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class CRUDResponder implements Responder {
    private String directory;
    private String uri;
    private String httpMethod;
    private Integer statusCode;
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
    public String getStatusCode(statusCode statusCode) {
        return statusCode.getStatus(this.statusCode);
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
        return writeToFile(201);
    }

    private Path updatePath() throws IOException {
        return writeToFile(200);
    }

    private Path deletePath() throws IOException {
        Files.delete(getPath());
        statusCode = 200;
        return getPath();
    }

    private Path writeToFile(Integer statusCode) throws IOException {
        this.statusCode = statusCode;
        return Files.write(getPath(), content.getBytes());
    }

    private Path getPath() {
        return Paths.get(directory + "/" + uri).toAbsolutePath();
    }
}
