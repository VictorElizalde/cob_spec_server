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
    public void returns200OkFor200Code() throws Exception {
        Assert.assertEquals("200 OK", statusCode.getStatus(200));
        Assert.assertEquals("206 Partial Content", statusCode.getStatus(206));
        Assert.assertEquals("404 Not Found", statusCode.getStatus(404));
        Assert.assertEquals("405 Method Not Allowed", statusCode.getStatus(405));
        Assert.assertEquals("416 Requested Range Not Satisfiable", statusCode.getStatus(416));
        Assert.assertEquals("501 Not Implemented", statusCode.getStatus(501));
    }

    @Test(expected = KeyException.class)
    public void throwsExceptionForInvalidKey() throws Exception {
        statusCode.isAValidKey(12341234);
    }
}
