package java_server.responders;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BasicAuthResponder implements Responder {
    private String directory;
    private String basicAuthCredentials;
    private String uri;
    public byte[] body;

    public BasicAuthResponder(String directory, String uri, String basicAuthCredentials) {
        this.directory = directory;
        this.basicAuthCredentials = basicAuthCredentials;
        this.uri = uri;
    }

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return isCredentialMatch() ? statusMessageCode.OK : statusMessageCode.UNAUTHORIZED;
    }

    @Override
    public void processResponse() {
        if (isCredentialMatch()) {
            try {
                body = Files.readAllBytes(Paths.get(directory + uri));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            logRequests();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        body = "Authentication required".getBytes();
    }

    private boolean isCredentialMatch() {
        if (basicAuthCredentials != null) {
            for (String credential : credentialsList()) {
                if (basicAuthCredentials.equals(credential)) {
                    return true;
                }
            }
        }

        return false;
    }

    private ArrayList<String> credentialsList() {
        ArrayList<String> credentials = new ArrayList<String>();
        credentials.add("admin:hunter2");

        return credentials;
    }

    private void logRequests() throws FileNotFoundException {
        File file = new File(directory + uri);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
    }
}
