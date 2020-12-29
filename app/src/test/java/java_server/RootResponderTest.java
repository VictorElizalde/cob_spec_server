package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RootResponderTest {
    @Test
    public void returnsTheIndexPageWhenRootRequestIsMade() throws Exception {
        statusCode statusCode = new statusCode();
        String serverViewsDirectory = "/Users/victorelizalde/Documents/Github/java_server/default-server-views";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("Welcome to the index page!"));
        Assert.assertEquals("200 OK", rootResponder.getStatusCode(statusCode));
    }
}
