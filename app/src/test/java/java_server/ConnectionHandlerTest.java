package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class ConnectionHandlerTest {
    @Test
    public void returnsFullRequestString() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1\nHost: localhost:5000".getBytes());
        RequestParser requestParser = new RequestParser(inputStream);

        Request request = requestParser.parse();
        assertEquals("GET /file1 HTTP/1.1\nHost: localhost:5000", request.getFullRequest());
    }
}
