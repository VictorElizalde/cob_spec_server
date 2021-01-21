package java_server.Main;

import java_server.Helpers.Constants;
import java_server.Helpers.StatusCode;
import java_server.Main.Request;
import java_server.Main.Response;
import java_server.Main.Routes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {
    private Request request;
    private StatusCode statusCode;
    private Response response;
    private Routes routes;

    @Before
    public void setUp() throws Exception {
        statusCode = new StatusCode();
        routes = new Routes(Constants.DEFAULT_TEST_DIRECTORY);
        int port = 5000;
        response = new Response(statusCode, routes, port, Constants.DEFAULT_TEST_DIRECTORY);
        request = new Request();
    }

    @Test
    public void returns200OkStatusWhenSuccessfulGetRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");
        response.setResponder(request);

        Assert.assertEquals(statusCode.OK, response.getStatusMessage(request));
    }

    @Test
    public void returnsAFilesContentsInTheMessageBodyWhenASuccessfulRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");
        response.setResponder(request);

        Assert.assertEquals("file1 contents", new String (response.getMessageBody(request)));
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
    public void returnsTheContentTypeForAPNGImage() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("image.png");

        Assert.assertEquals("Content-Type: image/png", response.getContentType(request));
    }

    @Test
    public void returnsTheContentTypeForAGIFImage() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("image.gif");

        Assert.assertEquals("Content-Type: image/gif", response.getContentType(request));
    }

    @Test
    public void returnsTheContentTypeForATXTFile() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("text-file.txt");

        Assert.assertEquals("Content-Type: text/plain", response.getContentType(request));
    }

    @Test
    public void returnsTheByteLengthForFile1() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");
        response.setResponder(request);

        byte[] responseBody = response.getMessageBody(request);

        Assert.assertEquals("Content-Length: 14", response.getContentLength());
    }

    @Test
    public void returnsTheByteLengthForAnImageFile() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("image.png");
        response.setResponder(request);

        byte[] responseBody = response.getMessageBody(request);

        Assert.assertEquals("Content-Length: 108763", response.getContentLength());
    }

    @Test
    public void returnsTheFormattedAllowHeader() throws Exception {
        request.setURI("file1");

        Assert.assertEquals("Allow: HEAD,DELETE,GET,OPTIONS,PUT", response.getAllowHeader(request));
    }

    @Test
    public void returnContentRangeForPartialContentFileWithDefinedRange() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("partial_content.txt");
        request.setByteRange("0-4");
        request.setByteLength("77");
        response.setResponder(request);

        byte[] responseBody = response.getMessageBody(request);

        Assert.assertEquals("Content-Range: bytes 0-4/77", response.getContentRange(request));
    }

    @Test
    public void returnContentRangeForPartialContentFileWithOnlyMaxRange() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("partial_content.txt");
        request.setByteRange("-6");
        request.setByteLength("77");
        response.setResponder(request);

        byte[] responseBody = response.getMessageBody(request);

        Assert.assertEquals("Content-Range: bytes 71-76/77", response.getContentRange(request));
    }

    @Test
    public void returnContentRangeForPartialContentFileWithOnlyMinRange() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("partial_content.txt");
        request.setByteRange("4-");
        request.setByteLength("77");
        response.setResponder(request);

        byte[] responseBody = response.getMessageBody(request);

        Assert.assertEquals("Content-Range: bytes 4-76/77", response.getContentRange(request));
    }

    @Test
    public void returnContentRangeForPartialContentFileWithWrongRange() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("partial_content.txt");
        request.setByteRange("5-0");
        request.setByteLength("77");
        response.setResponder(request);

        byte[] responseBody = response.getMessageBody(request);

        Assert.assertEquals("Content-Range: bytes */77", response.getContentRange(request));
    }
}
