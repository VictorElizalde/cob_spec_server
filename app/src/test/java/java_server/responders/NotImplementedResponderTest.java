package java_server.responders;

import java_server.constants.StatusCode;
import org.junit.Assert;
import org.junit.Test;

public class NotImplementedResponderTest {
    @Test
    public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
        StatusCode statusCode = new StatusCode();
        NotImplementedResponder notImplementedResponder = new NotImplementedResponder();
        byte[] fileBytes = notImplementedResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains(statusCode.NOT_IMPLEMENTED));
        Assert.assertEquals(statusCode.NOT_IMPLEMENTED, notImplementedResponder.getStatusCode());
    }
}
