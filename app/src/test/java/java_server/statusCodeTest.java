package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyException;

public class statusCodeTest {
    private statusCode statusCode;

    @Before
    public void setUp() throws Exception {
        statusCode = new statusCode();
    }

    @Test
    public void returns200OKFor200Code() throws Exception {
        Assert.assertEquals("200 OK", statusCode.getStatus(200));
    }

    @Test(expected = KeyException.class)
    public void throwsExceptionForInvalidKey() throws Exception {
        statusCode.isAValidKey(12341234);
    }
}
