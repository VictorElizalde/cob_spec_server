package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.net.*;

import static org.junit.Assert.assertTrue;

public class ServerTest {
    private Server server;

    @Before
    public void setUpVariables() throws Exception {
        server = new Server(5000, "/Users/victorelizalde/Documents/Github/cob_spec/public");
    }

    @Test
    public void clientServerConnection() throws Exception {
        Socket clientSocket = new Socket("localhost", 5000);

        Assert.assertEquals(true, clientSocket.isConnected());
    }

    @Test
    public void respondOk() throws Exception {
        String response = server.runServer();

        Assert.assertEquals("HTTP/1.1 200 OK\r\n\r\n", response);
    }
}
