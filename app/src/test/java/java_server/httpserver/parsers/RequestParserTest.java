package java_server.httpserver.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java_server.Constants;
import java_server.httpserver.Request;
import java_server.httpserver.parsers.RequestParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
    private Request defaultRequest;

    public Request setRequest(String request_body) throws Exception {
        String directory = Constants.DEFAULT_TEST_DIRECTORY;
        InputStream inputStream = new ByteArrayInputStream(request_body.getBytes());
        Request request = new RequestParser(inputStream, directory).parse();

        return request;
    }

    @Before
    public void setUp() throws Exception {
        String directory = Constants.DEFAULT_TEST_DIRECTORY;
        InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1\nHost: localhost:5000".getBytes());
        defaultRequest = new RequestParser(inputStream, directory).parse();
    }

    @Test
    public void returnsHttpRequestMethod() throws IOException {
        Assert.assertEquals("GET", defaultRequest.getHTTPMethod());
    }

    @Test
    public void returnsHttpRequestOPTIONS() throws Exception {
        Request request = setRequest("OPTIONS /method_options HTTP/1.1\nHost: localhost:5000");
        Assert.assertEquals("OPTIONS", request.getHTTPMethod());
    }

    @Test
    public void returnsHttpRequestURI() throws Exception {
        Assert.assertEquals("file1", defaultRequest.getURI());
    }

    @Test
    public void returnsRootRequestUri() throws Exception {
        Request request = setRequest("GET / HTTP/1.1\nHost: localhost:5000");

        Assert.assertEquals("/", request.getURI());
    }

    @Test
    public void returnsRangeRequestHeader() throws Exception {
        Request request = setRequest("GET /file1 HTTP/1.1\nRange: bytes=0-4\nHost: localhost:5000");

        Assert.assertEquals("Range:", request.getHeaderField());
    }

    @Test
    public void returnsAuthorizationRequestHeader() throws Exception {
        Request request = setRequest("GET /logs HTTP/1.1\nAuthorization: Basic JaASDJ4347qljA43J1SJD==\nHost: localhost:5000");

        Assert.assertEquals("Authorization:", request.getHeaderField());
    }

    @Test
    public void returnsPartialRequestByteRange() throws Exception {
        Request request = setRequest("GET /file1 HTTP/1.1\nRange: bytes=0-4\nHost: localhost:5000");

        Assert.assertEquals("0-4", request.getByteRange());
    }

    @Test
    public void returnsNegativeRangeByte() throws Exception {
        Request request = setRequest("GET /file1 HTTP/1.1\nRange: bytes=-4\nHost: localhost:5000");

        Assert.assertEquals("-4", request.getByteRange());
    }

    @Test
    public void returnsByteLength() throws Exception {
        Request request = setRequest("GET /partial_content.txt HTTP/1.1\nRange: bytes=0-4\nHost: localhost:5000");

        Assert.assertEquals("0-4", request.getByteRange());
        Assert.assertEquals("77", request.getByteLength());
    }

    @Test
    public void returnsTheFullHttpRequest() throws Exception {
        Assert.assertEquals("GET /file1 HTTP/1.1\nHost: localhost:5000", defaultRequest.getFullRequest());
    }

    @Test
    public void returnsFullRequestOfOptionsRequest() throws Exception {
        Request request = setRequest("OPTIONS /method_options HTTP/1.1\nHost: localhost:5000");

        Assert.assertEquals("OPTIONS /method_options HTTP/1.1\nHost: localhost:5000", request.getFullRequest());
    }

    @Test
    public void returnsTrueIfAuthRequestIsBasic() throws Exception {
        Request request = setRequest("GET /logs HTTP/1.1\nAuthorization: Basic dmljdG9yX2VsaXphbGRlOmFzZGZhc2Rm==\nHost: localhost:5000");

        Assert.assertEquals(true, request.isABasicAuthRequest());
    }

    @Test
    public void returnsFalseIfAuthRequestIsNotBasic() throws Exception {
        Request request = setRequest("GET /logs HTTP/1.1\nAuthorization: NotBasic dmljdG9yX2VsaXphbGRlOmFzZGZhc2Rm==\nHost: localhost:5000");

        Assert.assertEquals(false, request.isABasicAuthRequest());
    }
}