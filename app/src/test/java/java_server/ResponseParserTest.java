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
    public void formatsHTTPStatusMessageHeader() throws Exception {
        Assert.assertEquals("HTTP/1.1 200 OK\r\n", new String(responseParser.formatHTTPStatusMessage(request)));
    }

    @Test
    public void returnsTheResponseMessageBody() throws Exception {
        Assert.assertEquals("file1 contents", new String(responseParser.formatMessageBody(request)));
    }

    @Test
    public void presentsTheResponseInAnOutputStream() throws Exception {
        String response = "HTTP/1.1 200 OK\r\n" +
                "file1 contents";

        Assert.assertEquals(response, new String(responseParser.present(request)));
    }
}
