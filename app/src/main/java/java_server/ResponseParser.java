package java_server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseParser {
    private Response response;
    private static final String NEW_LINE = "\r\n";

    public ResponseParser(Response response) {
        this.response = response;
    }

    public byte[] formatHTTPStatusMessage(Request request) {
        String message = "HTTP/1.1 " + response.getStatusMessage(request) + NEW_LINE;
        return message.getBytes();
    }

    public byte[] formatMessageBody(Request request) {
        return response.getMessageBody(request);
    }

    public byte[] formatLocationHeader() {
        String location = response.getLocation() + NEW_LINE;

        return location.getBytes();
    }

    public byte[] formatContentTypeHeader(Request request) {
        String type = response.getContentType(request) + NEW_LINE;

        return type.getBytes();
    }

    public byte[] formatAllowHeader(Request request) {
        String allowHeader = response.getAllowHeader(request) + NEW_LINE;

        return allowHeader.getBytes();
    }

    public byte[] formatContentLength(Request request) {
        String contentLength = response.getContentLength(request) + NEW_LINE + NEW_LINE;

        return contentLength.getBytes();
    }

    public byte[] present(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] body = formatMessageBody(request);

        byteArrayOutputStream.write(formatHTTPStatusMessage(request));
        byteArrayOutputStream.write(formatLocationHeader());
        byteArrayOutputStream.write(formatContentTypeHeader(request));
        byteArrayOutputStream.write(formatAllowHeader(request));
        byteArrayOutputStream.write(formatContentLength(request));
        byteArrayOutputStream.write(body);

        return byteArrayOutputStream.toByteArray();
    }
}