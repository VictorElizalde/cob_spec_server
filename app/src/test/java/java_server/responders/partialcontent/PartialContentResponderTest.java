package java_server.responders.partialcontent;

import java_server.Constants;
import java_server.httpserver.StatusCode;
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
        partialContentResponder.processResponse();
        Assert.assertEquals(" 206.\n" , new String(partialContentResponder.getMessageBody()));
        Assert.assertEquals(statusCode.PARTIAL_CONTENT, partialContentResponder.getStatusCode());
    }

    @Test
    public void returnsPartialContentForMultipleDigitRange() throws Exception {
        String uri = "partial_content.txt";
        String byteRange = "75-80";
        PartialContentResponder partialContentResponder = new PartialContentResponder(testDirectory, uri, byteRange);
        partialContentResponder.processResponse();

        Assert.assertEquals(".\n" , new String(partialContentResponder.getMessageBody()));
        Assert.assertEquals(statusCode.PARTIAL_CONTENT, partialContentResponder.getStatusCode());
    }
}
