package java_server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseParser {
    private Response response;

    public ResponseParser(Response response) {
        this.response = response;
    }

    public byte[] formatHTTPStatusMessage(Request request) {
        String message = "HTTP/1.1 " + response.getStatusMessage(request) + "\r\n";
        return message.getBytes();
    }

    public byte[] formatMessageBody(Request request) {
        return response.getMessageBody(request);
    }

    public byte[] present(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] body = formatMessageBody(request);

        byteArrayOutputStream.write(formatHTTPStatusMessage(request));
        byteArrayOutputStream.write(body);

        return byteArrayOutputStream.toByteArray();
    }
}
