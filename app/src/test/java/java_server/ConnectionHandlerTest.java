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
        InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
        RequestParser requestParser = new RequestParser(inputStream);

        Request request = requestParser.parse();
        assertEquals("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000", request.getFullRequest());
    }


//    @Test
//    public void returnsTheOutputStreamInTextWhenARequestIsMade() throws Exception {
//        InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
//        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
//        RequestHandler requestHandler = new RequestHandler(mockSocket);
//        int port = 5000;
//        ConnectionHandler connectionHandler = new ConnectionHandler(requestHandler, port, "/Users/victorelizalde/Documents/Github/cob_spec/public");
//
//        String response = "HTTP/1.1 200 OK\r\n" +
//                          "file1 contents";
//
//        connectionHandler.run();
//        assertEquals(response, requestHandler.getOutputStream().toString());
//    }
}
