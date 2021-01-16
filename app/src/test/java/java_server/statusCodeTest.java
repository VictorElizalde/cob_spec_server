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
        Assert.assertEquals("200 OK", statusCode.OK);
        Assert.assertEquals("201 Created", statusCode.CREATED);
        Assert.assertEquals("206 Partial Content", statusCode.PARTIAL_CONTENT);
        Assert.assertEquals("404 Not Found", statusCode.NOT_FOUND);
        Assert.assertEquals("405 Method Not Allowed", statusCode.METHOD_NOT_ALLOWED);
        Assert.assertEquals("416 Requested Range Not Satisfiable", statusCode.REQUEST_RANGE_NOT_SATISFIABLE);
        Assert.assertEquals("501 Not Implemented", statusCode.NOT_IMPLEMENTED);
    }

//    @Test(expected = KeyException.class)
//    public void throwsExceptionForInvalidKey() throws Exception {
//        statusCode.isAValidKey(12341234);
//    }
}
