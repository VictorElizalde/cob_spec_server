package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotFoundResponderTest {
    @Test
    public void returns404NotFoundAsResponse() throws Exception {
        statusCode statusCode = new statusCode();
        NotFoundResponder notFoundResponder = new NotFoundResponder();
        byte[] fileBytes = notFoundResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("404 File Not Found"));
        Assert.assertEquals("404 Not Found", notFoundResponder.getStatusCode(statusCode));
    }
}
