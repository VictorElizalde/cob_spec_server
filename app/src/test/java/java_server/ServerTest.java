package java_server;

import org.junit.Test;
import org.junit.Before;
import java.net.*;
import java.io.*;

import static org.junit.Assert.*;

public class ServerTest {
    @Before public void setUpVariables() {
        Server classUnderTest = new Server();
    }

    @Test public void clientServerConnection() throws Exception {
        Socket clientSocket = new Socket("localhost", 5000);

        assertEquals(true, clientSocket.isConnected());
    }

    @Test public void respondOk() throws Exception {
        Server classUnderTest = new Server();

        String response = classUnderTest.runServer();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response);
    }

//    @Test public void clientServerConnection() throws Exception {
//        Socket clientSocket = new Socket("localhost", 5000);
//
//        assertEquals("hello client", clientSocket);
//    }

//    @Before public void setUp() throws Exception {
//        Request classUnderTest = new Request();
//        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 5000), 0)
//        HttpClient client = HttpClient.newBuilder()
//                .version(Version.HTTP_1_1)
//                .build();
//    }

//    @Test public void connectToSocket() {
//        classUnderTest.startConnection("127.0.0.1", 5000);
//        String response = classUnderTest.sendMessage("hello server");
//        assertEquals("hello client", response);
//    }

//    @Test public void getFile1Contents()
//            throws ClientProtocolException, IOException {
//
//        // Given
//        HttpUriRequest request = new HttpGet("localhost/file1");
//
//        // When
//        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
//
//        // Then
//        assertThat(
//                httpResponse.getStatusLine().getStatusCode(),
//                equalTo(HttpStatus.SC_NOT_FOUND));
//    }
}
