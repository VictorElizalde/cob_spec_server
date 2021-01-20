package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.net.*;

import static org.junit.Assert.assertTrue;

public class RequestTest {
    private Request request;

    @Before
    public void setUp() throws Exception {
        request = new Request();
    }

    @Test
    public void returnsTheFullRequest() throws Exception {
        request.setFullRequest("GET /file1 HTTP/1.1\nRange: bytes=0-4\nHost: localhost:5000");
        Assert.assertEquals("GET /file1 HTTP/1.1\nRange: bytes=0-4\nHost: localhost:5000", request.getFullRequest());
    }

    @Test
    public void returnsTheHttpMethod() throws Exception {
        request.setHTTPMethod("GET");
        Assert.assertEquals("GET", request.getHTTPMethod());
    }

    @Test
    public void returnsTheUri() throws Exception {
        request.setURI("/file1");
        Assert.assertEquals("/file1", request.getURI());
    }

    @Test
    public void returnsTrueIfRequestIsARootRequest() throws Exception {
        request.setURI("/");
        assertTrue(request.isARootRequest());
    }

    @Test
    public void returnsTheHeaderField() throws Exception {
        request.setHeaderField("Range:");
        Assert.assertEquals("Range:", request.getHeaderField());
    }

    @Test
    public void returnsTheByteRange() throws Exception {
        request.setByteRange("bytes=0-4");
        Assert.assertEquals("bytes=0-4", request.getByteRange());
    }

    @Test
    public void returnsTheByteLength() throws Exception {
        request.setByteLength("77");
        Assert.assertEquals("77", request.getByteLength());
    }

    @Test
    public void returnsTrueIfRequestHeaderFieldIsAnAuthorizationRequest() throws Exception {
        request.setBasicRequestStatus(true);
        assertTrue(request.isABasicAuthRequest());
    }

    @Test
    public void returnsBasicAuthCredentials() throws Exception {
        request.setBasicAuthCredentials("Credentials");
        Assert.assertEquals("Credentials", request.getBasicAuthCredentials());
    }
}
