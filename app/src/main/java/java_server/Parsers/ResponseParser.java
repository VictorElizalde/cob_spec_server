package java_server.Parsers;

import java_server.Main.Request;
import java_server.Main.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        byte[] body = formatMessageBody(request);

        byteArrayOutputStream.write(formatHTTPStatusMessage(request));
        byteArrayOutputStream.write(formatLocationHeader());
        byteArrayOutputStream.write(formatContentTypeHeader(request));
        byteArrayOutputStream.write(formatAllowHeader(request));
        if (request.getByteRange() != null && !request.getByteRange().equals("Range not given")) byteArrayOutputStream.write(formatContentRange(request));
        byteArrayOutputStream.write(formatContentLength(request));
//        if (request.isABasicAuthRequest())
//            byteArrayOutputStream.write("WWW-Authenticate: Basic realm=\"WallyWorld\"".getBytes());
        byteArrayOutputStream.write(body);

        return byteArrayOutputStream.toByteArray();
    }
}
