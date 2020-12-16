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
        request.setFullRequest("GET /file1 HTTP/1.1");
        Assert.assertEquals("GET /file1 HTTP/1.1", request.getFullRequest());
    }

    @Test
    public void returnsTheHTTPMethod() throws Exception {
        request.setHTTPMethod("GET");
        Assert.assertEquals("GET", request.getHTTPMethod());
    }

    @Test
    public void returnsTheURI() throws Exception {
        request.setURI("/file1");
        Assert.assertEquals("/file1", request.getURI());
    }

    @Test
    public void returnsTrueIfRequestIsARootRequest() throws Exception {
        request.setURI("/");
        assertTrue(request.isARootRequest());
    }
}
