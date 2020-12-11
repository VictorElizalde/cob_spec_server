package java_server;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.net.*;
import java.io.*;

import static com.google.common.base.Predicates.equalTo;
import static org.junit.Assert.*;

public class RequestTest {
    private Socket socket;

    @Before public void setUp() throws Exception {
        Request classUnderTest = new Request();
        InetAddress host = InetAddress.getLocalHost();
        socket = new Socket(host.getHostName(), 5000);
    }

    @Test public void connectToSocket() {
        classUnderTest.startConnection("127.0.0.1", 5000);
        String response = classUnderTest.sendMessage("hello server");
        assertEquals("hello client", response);
    }

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
