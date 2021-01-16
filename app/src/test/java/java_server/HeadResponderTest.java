package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeadResponderTest {
    @Test
    public void returns200WhenHeadIsNull() throws Exception {
        statusCode statusCode = new statusCode();
        HeadResponder headResponder = new HeadResponder();
        byte[] fileBytes = headResponder.getMessageBody();

        Assert.assertTrue(fileBytes.toString().contains(""));
        Assert.assertEquals(statusCode.OK, headResponder.getStatusCode());
    }
}
