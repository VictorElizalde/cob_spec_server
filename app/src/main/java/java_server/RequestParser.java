package java_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestParser {
    private InputStream inputStream;
    private String[] requestArray;

    public RequestParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Request parse() throws IOException {
        Request request = new Request();
        String requestString = convertRequestToString();
        requestArray = requestString.split(" ");

        request.setFullRequest(requestString);
        request.setHTTPMethod(parseHTTPMethod());
        request.setURI(parseURI());
        request.setHeaderField(parseHeaderField());
        request.setByteRange(getByteRange());
        request.setBasicRequestStatus(isABasicAuthRequest());
        request.setData(parseData());

        return request;
    }

    private String convertRequestToString() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder requestBuilder = new StringBuilder();
        String line;
        try {
            do {
                line = bufferedReader.readLine();
                requestBuilder.append(line);
                if (line.equals("")) break;
            } while (bufferedReader.ready());
        } catch (Exception e) {
            System.out.println("Request not readable");
        }

        return requestBuilder.toString();
    }

    private String parseHTTPMethod() throws IOException {
        return requestArray[0];
    }

    private boolean isARootRequest() {
        return (requestArray[1].equals("/"));
    }

    private String parseURI() throws IOException {
        if (isARootRequest()) return "/";

        String[] splitOnBackslash = requestArray[1].split("/");
        return splitOnBackslash[1];
    }

    private String parseHeaderField() throws IOException {
        String header = requestArray[2];
        int lastIndex = header.lastIndexOf("1");
        String protocol = header.substring(0, lastIndex + 1);
        String[] splitOnProtocol = header.split(protocol);

        return splitOnProtocol[1];
    }

    private String getByteRange() throws IOException {
        try {
            String[] splitOnHost = requestArray[3].split("Host:");
            String[] splitOnBytes = splitOnHost[0].split("bytes=");
            return splitOnBytes[1];
        } catch (ArrayIndexOutOfBoundsException e){

            return "Range not given";
        }
    }

    private String parseData() throws IOException {
        return "Some text for a new file";
    }

    private boolean isABasicAuthRequest() throws IOException {
        return requestArray[3].equals("Basic");
    }
}
