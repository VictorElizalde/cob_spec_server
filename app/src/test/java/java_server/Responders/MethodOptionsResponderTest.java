package java_server.Responders;

import java_server.Helpers.StatusCode;
import java_server.Responders.MethodOptionsResponder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class MethodOptionsResponderTest {
    private StatusCode statusCode;

    @Before
    public void setUp() throws Exception {
        statusCode = new StatusCode();
    }

    @Test
    public void returnsGetOptionsForRequest() throws Exception {
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("GET");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(Arrays.equals(fileBytes, "GET".getBytes()));
        Assert.assertEquals(statusCode.OK, methodOptionsResponder.getStatusCode());
    }

    @Test
    public void returnsNoOptionsForRequest() throws Exception {
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(Arrays.equals(fileBytes, "".getBytes()));
        Assert.assertEquals(statusCode.OK, methodOptionsResponder.getStatusCode());
    }

    @Test
    public void returnsOptionsForRequest() throws Exception {
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("GET,HEAD,OPTIONS,PUT,DELETE");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(Arrays.equals(fileBytes, "GET,HEAD,OPTIONS,PUT,DELETE".getBytes()));
        Assert.assertEquals(statusCode.OK, methodOptionsResponder.getStatusCode());
    }
}
