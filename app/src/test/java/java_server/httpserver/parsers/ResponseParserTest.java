package java_server.httpserver.parsers;

import java_server.Constants;
import java_server.httpserver.Request;
import java_server.httpserver.Response;
import java_server.httpserver.Routes;
import java_server.httpserver.StatusCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseParserTest {
    private ResponseParser responseParser;
    private Request request;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        StatusCode statusCode = new StatusCode();
        Routes routes = new Routes(Constants.DEFAULT_TEST_DIRECTORY);
        int port = 5000;
        Response response = new Response(statusCode, routes, port, Constants.DEFAULT_TEST_DIRECTORY);
        responseParser = new ResponseParser(response);
        request.setHTTPMethod("GET");
        request.setURI("file1");
        response.setResponder(request);
        response.getResponder().processResponse();
        response.getMessageBody();
    }

    @Test
    public void formatsHttpStatusMessageHeader() throws Exception {
        Assert.assertEquals("HTTP/1.1 200 OK\r\n", new String(responseParser.formatHTTPStatusMessage(request)));
    }

    @Test
    public void returnsTheResponseMessageBody() throws Exception {
        Assert.assertEquals("file1 contents", new String(responseParser.formatMessageBody()));
    }

    @Test
    public void formatsLocationHeader() throws Exception {
        Assert.assertEquals("Location: http://localhost:5000/\r\n", new String(responseParser.formatLocationHeader()));
    }

    @Test
    public void formatsContentTypeHeader() throws Exception {
        Assert.assertEquals("Content-Type: text/html\r\n", new String(responseParser.formatContentTypeHeader(request)));
    }

    @Test
    public void formatsAllowHeader() throws Exception {
        Assert.assertEquals("Allow: HEAD,DELETE,GET,OPTIONS,PUT\r\n", new String(responseParser.formatAllowHeader(request)));
    }

    @Test
    public void formatsContentLength() throws Exception {
        Assert.assertEquals("Content-Length: 14\r\n\r\n", new String(responseParser.formatContentLength(request)));
    }

    @Test
    public void formatsContentRange() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("partial_content.txt");
        request.setByteRange("0-4");
        request.setByteLength("77");
        Assert.assertEquals("Content-Range: bytes 0-4/77\r\n", new String(responseParser.formatContentRange(request)));
    }

    @Test
    public void presentsTheResponseInAnOutputStream() throws Exception {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Location: http://localhost:5000/\r\n" +
                "Content-Type: text/html\r\n" +
                "Allow: HEAD,DELETE,GET,OPTIONS,PUT\r\n" +
                "Content-Length: 14\r\n" +
                "\r\n" +
                "file1 contents";

        Assert.assertEquals(response, new String(responseParser.buildResponse(request)));
    }

    @Test
    public void presentsTheResponseInAnOutputStreamForRange() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("partial_content.txt");
        request.setByteRange("0-4");
        request.setByteLength("77");

        String response = "HTTP/1.1 206 Partial Content\r\n" +
                "Location: http://localhost:5000/\r\n" +
                "Content-Type: text/plain\r\n" +
                "Allow: HEAD,DELETE,GET,OPTIONS,PUT\r\n" +
                "Content-Range: bytes 0-4/77\r\n" +
                "Content-Length: 5\r\n" +
                "\r\n" +
                "This ";

        Assert.assertEquals(response, new String(responseParser.buildResponse(request)));
    }
}
