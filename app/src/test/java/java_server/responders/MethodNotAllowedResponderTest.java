package java_server.responders;

import java_server.constants.StatusCode;
import org.junit.Assert;
import org.junit.Test;

public class MethodNotAllowedResponderTest {
    @Test
    public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
        StatusCode statusCode = new StatusCode();
        MethodNotAllowedResponder methodNotAllowedResponder = new MethodNotAllowedResponder();
        byte[] fileBytes = methodNotAllowedResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains(statusCode.METHOD_NOT_ALLOWED));
        Assert.assertEquals(statusCode.METHOD_NOT_ALLOWED, methodNotAllowedResponder.getStatusCode());
    }
}
