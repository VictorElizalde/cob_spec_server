package java_server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
    private Request defaultRequest;

    public Request setRequest(String request_body) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(request_body.getBytes());
        Request request = new RequestParser(inputStream).parse();

        return request;
    }

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
        defaultRequest = new RequestParser(inputStream).parse();
    }

    @Test
    public void returnsHttpRequestMethod() throws IOException {
        Assert.assertEquals("GET", defaultRequest.getHTTPMethod());
    }

    @Test
    public void returnsHttpRequestOPTIONS() throws Exception {
        Request request = setRequest("OPTIONS /method_options HTTP/1.1Connection: closeHost: localhost: 5000");
        Assert.assertEquals("OPTIONS", request.getHTTPMethod());
    }

    @Test
    public void returnsHttpRequestURI() throws Exception {
        Assert.assertEquals("file1", defaultRequest.getURI());
    }

    @Test
    public void returnsParameterRequestUri() throws Exception {
        Request request = setRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");

        Assert.assertEquals("parameters", request.getURI());
    }

    @Test
    public void returnsRootRequestUri() throws Exception {
        Request request = setRequest("GET / HTTP/1.1Connection: closeHost: localhost:5000");

        Assert.assertEquals("/", request.getURI());
    }

    @Test
    public void returnsRangeRequestHeader() throws Exception {
        Request request = setRequest("GET /file1 HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");

        Assert.assertEquals("Range:", request.getHeaderField());
    }

    @Test
    public void returnsAuthorizationRequestHeader() throws Exception {
        Request request = setRequest("GET /logs HTTP/1.1Authorization: Basic JaASDJ4347qljA43J1SJD==Connection: closeHost: localhost:5000");

        Assert.assertEquals("Authorization:", request.getHeaderField());
    }

    @Test
    public void returnsPartialRequestByteRange() throws Exception {
        Request request = setRequest("GET /file1 HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");

        Assert.assertEquals("0-4", request.getByteRange());
    }

    @Test
    public void returnsNegativeRangeByte() throws Exception {
        Request request = setRequest("GET /file1 HTTP/1.1Range: bytes=-4Connection: closeHost: localhost:5000");

        Assert.assertEquals("-4", request.getByteRange());
    }

    @Test
    public void returnsTheFullHttpRequest() throws Exception {
        Assert.assertEquals("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000", defaultRequest.getFullRequest());
    }

    @Test
    public void returnsFullRequestOfOptionsRequest() throws Exception {
        Request request = setRequest("OPTIONS /method_options HTTP/1.1Connection: closeHost: localhost: 5000");

        Assert.assertEquals("OPTIONS /method_options HTTP/1.1Connection: closeHost: localhost: 5000", request.getFullRequest());
    }

    @Test
    public void returnsTrueIfAuthRequestIsBasic() throws Exception {
        Request request = setRequest("GET /logs HTTP/1.1Authorization: Basic dmljdG9yX2VsaXphbGRlOmFzZGZhc2Rm==Connection: closeHost: localhost:5000");

        Assert.assertEquals(true, request.isABasicAuthRequest());
    }

    @Test
    public void returnsFalseIfAuthRequestIsNotBasic() throws Exception {
        Request request = setRequest("GET /logs HTTP/1.1Authorization: NotBasic dmljdG9yX2VsaXphbGRlOmFzZGZhc2Rm==Connection: closeHost: localhost:5000");

        Assert.assertEquals(false, request.isABasicAuthRequest());
    }

    @Test
    public void returnsTheEtagForPatchRequests() throws Exception {
        Request request = setRequest("PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8becConnection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");

        Assert.assertEquals("dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", request.getEtag());
    }

    @Test
    public void returnsDecodedParameterKeyAndValuePairs() throws Exception {
        Request request = setRequest("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");

        Assert.assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"? variable_2 = stuff", request.getParameterValues());
    }
}