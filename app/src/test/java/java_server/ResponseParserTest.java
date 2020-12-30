package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseParserTest {
    private ResponseParser responseParser;
    private Request request;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        statusCode statusCode = new statusCode();
        Routes routes = new Routes("/Users/victorelizalde/Documents/Github/cob_spec/public");
        int port = 5000;
        Response response = new Response(statusCode, routes, port);
        responseParser = new ResponseParser(response);
        request.setHTTPMethod("GET");
        request.setURI("file1");
        response.getMessageBody(request);
    }

    @Test
    public void formatsHttpStatusMessageHeader() throws Exception {
        Assert.assertEquals("HTTP/1.1 200 OK\r\n", new String(responseParser.formatHTTPStatusMessage(request)));
    }

    @Test
    public void returnsTheResponseMessageBody() throws Exception {
        Assert.assertEquals("file1 contents", new String(responseParser.formatMessageBody(request)));
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
}
