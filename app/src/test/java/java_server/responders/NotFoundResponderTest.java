package java_server.responders;

import java_server.httpserver.StatusCode;
import org.junit.Assert;
import org.junit.Test;

public class NotFoundResponderTest {
    @Test
    public void returns404NotFoundAsResponse() throws Exception {
        StatusCode statusCode = new StatusCode();
        NotFoundResponder notFoundResponder = new NotFoundResponder();
        notFoundResponder.processResponse();
        byte[] fileBytes = notFoundResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains(statusCode.NOT_FOUND));
        Assert.assertEquals(statusCode.NOT_FOUND, notFoundResponder.getStatusCode());
    }
}
