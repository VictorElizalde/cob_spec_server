package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MethodNotAllowedResponderTest {
    @Test
    public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
        StatusCode statusCode = new StatusCode();
        MethodNotAllowedResponder methodNotAllowedResponder = new MethodNotAllowedResponder();
        byte[] fileBytes = methodNotAllowedResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("405 Method Not Allowed"));
        Assert.assertEquals(statusCode.METHOD_NOT_ALLOWED, methodNotAllowedResponder.getStatusCode());
    }
}
