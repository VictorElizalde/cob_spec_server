package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {
    private Request request;
    private statusCode statusCode;
    private Response response;
    private Routes routes;

    @Before
    public void setUp() throws Exception {
        statusCode = new statusCode();
        routes = new Routes("/Users/victorelizalde/Documents/Github/cob_spec/public");
        int port = 5000;
        response = new Response(statusCode, routes, port);
        request = new Request();
    }

    @Test
    public void returns200OkStatusWhenSuccessfulGetRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");
        response.getMessageBody(request);

        Assert.assertEquals("200 OK", response.getStatusMessage(request));
    }

    @Test
    public void returnsAFilesContentsInTheMessageBodyWhenASuccessfulRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals("file1 contents", new String(response.getMessageBody(request)));
    }
}
