package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MethodOptionsResponderTest {
    @Test
    public void returnsOPTIONSForRequest() throws Exception {
        statusCode statusCode = new statusCode();
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("GET,HEAD,OPTIONS,PUT,DELETE");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(new String(fileBytes).contains("GET,HEAD,OPTIONS,PUT,DELETE"));
        Assert.assertEquals("200 OK", methodOptionsResponder.getStatusCode(statusCode));
    }
}
