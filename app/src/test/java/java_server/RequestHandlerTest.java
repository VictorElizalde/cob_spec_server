package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestHandlerTest {
    private InputStream inputStream;
    private OutputStream byteArrayOutputStream;
//    private MockSocket mockSocket;
    private RequestHandler requestHandler;

    @Before
    public void setUp() throws Exception {
        inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
//        mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
//        requestHandler = new RequestHandler(mockSocket);
    }

    @Test
    public void returnsInputStreamOfSocket() throws Exception {
        Assert.assertEquals(inputStream, requestHandler.getInputStream());
    }

    @Test
    public void returnsOutputStreamOfSocket() throws Exception {
        Assert.assertEquals(byteArrayOutputStream, requestHandler.getOutputStream());
    }
}
