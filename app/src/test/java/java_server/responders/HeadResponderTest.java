package java_server.responders;

import java_server.httpserver.StatusCode;
import org.junit.Assert;
import org.junit.Test;

public class HeadResponderTest {
    @Test
    public void returns200WhenHeadIsNull() throws Exception {
        StatusCode statusCode = new StatusCode();
        HeadResponder headResponder = new HeadResponder();
        byte[] fileBytes = headResponder.getMessageBody();

        Assert.assertTrue(fileBytes.toString().contains(""));
        Assert.assertEquals(statusCode.OK, headResponder.getStatusCode());
    }
}
