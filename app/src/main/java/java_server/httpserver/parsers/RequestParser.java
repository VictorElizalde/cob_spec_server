package java_server.httpserver.parsers;

import java_server.httpserver.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestParser {
    private InputStream inputStream;
    private String[] requestArray;
    private String directory;
    private Request request;

    public RequestParser(InputStream inputStream, String directory) {
        this.inputStream = inputStream;
        this.directory = directory;
    }

    public Request parse() throws IOException {
        request = new Request();
        String requestString = convertRequestToString();
        requestArray = requestString.split("\n");

        request.setFullRequest(requestString);
        request.setHTTPMethod(parseHTTPMethod());
        request.setURI(parseURI());
        request.setHeaderField(parseHeaderField());
        request.setByteRange(getByteRange());
        request.setByteLength(getByteLength());
        request.setBasicRequestStatus(isABasicAuthRequest());
        request.setData(parseData());

        return request;
    }

    private String convertRequestToString() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder requestBuilder = new StringBuilder();

        try {
            do {
                int c = bufferedReader.read();
                if (c > -1) requestBuilder.append((char) c);
                else break;
            } while (bufferedReader.ready());
        } catch (Exception e) {
            System.out.println("Request not readable");
        }
        return requestBuilder.toString();
    }

    private String parseHTTPMethod() throws IOException {
        return requestArray[0].split(" ")[0];
    }

    private boolean isARootRequest() {
        return (requestArray[0].split(" ")[1].equals("/"));
    }

    private String parseURI() throws IOException {
        if (isARootRequest()) return "/";

        String[] splitOnBackslash = requestArray[0].split(" ")[1].split("/");
        return splitOnBackslash[1];
    }

    private String parseHeaderField() throws IOException {
        return requestArray[1].split(" ")[0];
    }

    private String getByteRange() throws IOException {
        try {
            String[] splitOnBytes = requestArray[1].split("bytes=");
            String cleanBytes = splitOnBytes[1].replace("\n", "").replace("\r", "");
            return cleanBytes;
        } catch (ArrayIndexOutOfBoundsException e){
            return "Range not given";
        }
    }

    private String getByteLength() throws IOException {
        try {
            return Integer.toString(Files.readAllBytes(Paths.get(directory + "/" + request.getURI())).length);
        } catch (IOException e){
            return "File doesn't exist";
        }
    }

    private String parseData() throws IOException {
        return requestArray[requestArray.length-1];
    }

    private boolean isABasicAuthRequest() throws IOException {
        return requestArray[1].split(" ")[1].equals("Basic");
    }
}
