package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotFoundResponderTest {
    @Test
    public void returns404NotFoundAsResponse() throws Exception {
        StatusCode statusCode = new StatusCode();
        NotFoundResponder notFoundResponder = new NotFoundResponder();
        byte[] fileBytes = notFoundResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("404 File Not Found"));
        Assert.assertEquals(statusCode.NOT_FOUND, notFoundResponder.getStatusCode());
    }
}
