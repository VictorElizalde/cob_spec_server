package java_server.Responders;

import java_server.Helpers.Constants;
import java_server.Helpers.StatusCode;
import java_server.Responders.PartialContentResponder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PartialContentResponderTest {
    private PartialContentResponder partialContentResponder;
    private String testDirectory;
    private StatusCode statusCode = new StatusCode();

    @Before
    public void setUp() throws Exception {
        testDirectory = Constants.DEFAULT_TEST_DIRECTORY;
        String byteRange = "-6";
        String uri = "partial_content.txt";
        partialContentResponder = new PartialContentResponder(testDirectory, uri, byteRange);
    }

    @Test
    public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
        Assert.assertEquals(" 206.\n" , new String(partialContentResponder.getMessageBody()));
        Assert.assertEquals(statusCode.PARTIAL_CONTENT, partialContentResponder.getStatusCode());
    }

    @Test
    public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
        String uri = "partial_content.txt";
        String byteRange = "0-6";
        PartialContentResponder partialContentResponder = new PartialContentResponder(testDirectory, uri, byteRange);

        Assert.assertEquals("This is" , new String(partialContentResponder.getMessageBody()));
        Assert.assertEquals(statusCode.PARTIAL_CONTENT, partialContentResponder.getStatusCode());
    }
}
