package java_server;

import org.junit.Assert;
import org.junit.Test;

public class NotImplementedResponderTest {
    @Test
    public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
        StatusCode statusCode = new StatusCode();
        NotImplementedResponder notImplementedResponder = new NotImplementedResponder();
        byte[] fileBytes = notImplementedResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("501 Not Implemented"));
        Assert.assertEquals(statusCode.NOT_IMPLEMENTED, notImplementedResponder.getStatusCode());
    }
}
