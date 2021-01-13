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
    private static HashMap<String, byte[]> CRUDMap = new HashMap<String, byte[]>();

    public CRUDResponder(String directory, String httpMethod, String uri) {
        this.directory = directory;
        this.httpMethod = httpMethod;
        this.uri = uri;
    }

    @Override
    public byte[] getMessageBody() {
        try {
            writeToCRUD();
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

    private Path writeToCRUD() throws IOException {
        if (httpMethod.equals("DELETE")){
            Files.delete(getPath());
            statusCode = 200;
            return getPath();
        } else if(Files.exists(getPath())){
            System.out.println("Hello");
            statusCode = 200;
            return Files.write(getPath(), "Some updated text".getBytes());
        }

        System.out.println("Goodbye");
        statusCode = 201;
        return Files.write(getPath(), "Some text for a new file".getBytes());
    }

    private Path getPath() {
        return Paths.get(directory + "/" + uri).toAbsolutePath();
    }
}
