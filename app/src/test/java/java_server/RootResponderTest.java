package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RootResponderTest {
    @Test
    public void returnsTheIndexPageWhenRootRequestIsMade() throws Exception {
        statusCode statusCode = new statusCode();
        String serverViewsDirectory = Constants.DEFAULT_SERVER_VIEWS_DIRECTORY;
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("Welcome to the index page!"));
        Assert.assertEquals(statusCode.getStatus(200), rootResponder.getStatusCode(statusCode));
    }
}
