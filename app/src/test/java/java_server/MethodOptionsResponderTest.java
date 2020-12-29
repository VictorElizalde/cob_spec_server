package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class MethodOptionsResponderTest {
    @Test
    public void returnsGETOPTIONSForRequest() throws Exception {
        statusCode statusCode = new statusCode();
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("GET");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(Arrays.equals(fileBytes, "GET".getBytes()));
        Assert.assertEquals("200 OK", methodOptionsResponder.getStatusCode(statusCode));
    }

    @Test
    public void returnsNOOPTIONSForRequest() throws Exception {
        statusCode statusCode = new statusCode();
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(Arrays.equals(fileBytes, "".getBytes()));
        Assert.assertEquals("200 OK", methodOptionsResponder.getStatusCode(statusCode));
    }

    @Test
    public void returnsOPTIONSForRequest() throws Exception {
        statusCode statusCode = new statusCode();
        MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder("GET,HEAD,OPTIONS,PUT,DELETE");
        byte[] fileBytes = methodOptionsResponder.getMessageBody();

        Assert.assertTrue(Arrays.equals(fileBytes, "GET,HEAD,OPTIONS,PUT,DELETE".getBytes()));
        Assert.assertEquals("200 OK", methodOptionsResponder.getStatusCode(statusCode));
    }
}
