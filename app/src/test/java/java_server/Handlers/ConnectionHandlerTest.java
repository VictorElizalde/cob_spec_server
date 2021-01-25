package java_server.Handlers;

import java_server.Helpers.Constants;
import java_server.Main.Request;
import java_server.Parsers.RequestParser;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ConnectionHandlerTest {
    @Test
    public void returnsFullRequestString() throws Exception {
        String directory = Constants.DEFAULT_TEST_DIRECTORY;
        InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1\nHost: localhost:5000".getBytes());
        RequestParser requestParser = new RequestParser(inputStream, directory);

        Request request = requestParser.parse();
        assertEquals("GET /file1 HTTP/1.1\nHost: localhost:5000", request.getFullRequest());
    }
}
