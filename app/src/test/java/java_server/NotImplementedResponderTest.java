package java_server;

import org.junit.Assert;
import org.junit.Test;

public class NotImplementedResponderTest {
    @Test
    public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
        statusCode statusCode = new statusCode();
        NotImplementedResponder notImplementedResponder = new NotImplementedResponder();
        byte[] fileBytes = notImplementedResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("501 Not Implemented"));
        Assert.assertEquals("501 Not Implemented", notImplementedResponder.getStatusCode());
    }
}
