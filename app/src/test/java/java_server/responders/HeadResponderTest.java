package java_server.Responders;

import java_server.Helpers.StatusCode;
import java_server.Responders.HeadResponder;
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
