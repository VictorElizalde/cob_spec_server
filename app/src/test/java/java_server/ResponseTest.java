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

        Assert.assertEquals(statusCode.getStatus(200), response.getStatusMessage(request));
    }

    @Test
    public void returnsAFilesContentsInTheMessageBodyWhenASuccessfulRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals("file1 contents", new String(response.getMessageBody(request)));
    }

    @Test
    public void returnsTheServerLocation() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals("Location: http://localhost:5000/", response.getLocation());
    }

    @Test
    public void returnsTheContentTypeForThePageThatIsDisplayedAfterRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals("Content-Type: text/html", response.getContentType(request));
    }

    @Test
    public void returnsTheContentTypeForAJPEGImage() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("image.jpg");

        Assert.assertEquals("Content-Type: image/jpeg", response.getContentType(request));
    }

    @Test
    public void returnsTheByteLengthForFile1() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals("Content-Length: 14", response.getContentLength(request));
    }

    @Test
    public void returnsTheByteLengthForAnImageFile() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("image.png");

        Assert.assertEquals("Content-Length: 108763", response.getContentLength(request));
    }

    @Test
    public void returnsTheFormattedAllowHeader() throws Exception {
        request.setURI("file1");

        Assert.assertEquals("Allow: HEAD,DELETE,GET,OPTIONS,PUT", response.getAllowHeader(request));
    }
}
