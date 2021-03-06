package java_server.httpserver.parsers;

import java_server.httpserver.Request;
import java_server.httpserver.Response;

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

    public byte[] formatMessageBody() {
        return response.getMessageBody();
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
        String contentLength = response.getContentLength() + NEW_LINE + NEW_LINE;

        return contentLength.getBytes();
    }

    public byte[] formatContentRange(Request request) {
        String contentRange = response.getContentRange(request) + NEW_LINE;

        return contentRange.getBytes();
    }

    public byte[] buildResponse(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        response.setResponder(request);
        response.getResponder().processResponse();

        byte[] body = formatMessageBody();

        byteArrayOutputStream.write(formatHTTPStatusMessage(request));
        byteArrayOutputStream.write(formatLocationHeader());
        byteArrayOutputStream.write(formatContentTypeHeader(request));
        byteArrayOutputStream.write(formatAllowHeader(request));
        if (request.getByteRange() != null && !request.getByteRange().equals("Range not given"))
            byteArrayOutputStream.write(formatContentRange(request));
        if (request.isABasicAuthRequest())
            byteArrayOutputStream.write("WWW-Authenticate: Basic realm=\"WallyWorld\"\r\n".getBytes());
        byteArrayOutputStream.write(formatContentLength(request));
        byteArrayOutputStream.write(body);

        return byteArrayOutputStream.toByteArray();
    }
}
