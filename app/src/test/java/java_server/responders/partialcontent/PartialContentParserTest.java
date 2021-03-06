package java_server.responders.partialcontent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PartialContentParserTest {
    private PartialContentParser partialContentParser;
    private byte[] data;

    @Before
    public void setUp() throws Exception {
        String byteRange = "-6";
        partialContentParser = new PartialContentParser(byteRange);
        data =  "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        
    }

    @Test
    public void returnsTotalSizeMinusNegativeRange() throws Exception {
        Assert.assertEquals(data.length - 5, partialContentParser.getMinRange("", "5", data.length));
    }

    @Test
    public void returnsTheMinRangePositive4ByteRange() throws Exception {
        Assert.assertEquals(4, partialContentParser.getMinRange("4", "4", data.length));
    }

    @Test
    public void returnsTheMaxRangePositive76WhenMinMatchIs4() throws Exception {
        Assert.assertEquals(data.length, partialContentParser.getMaxRange("4", "", data.length));
    }

    @Test
    public void returnsTheMaxRangePositive5WhenMinMatchIs0AndMaxMatchIs4() throws Exception {
        Assert.assertEquals(5, partialContentParser.getMaxRange("0", "4", data.length));
    }

    @Test
    public void returnsTheMaxRangePositive76WhenMinMatchIs0AndMaxMatchIs78() throws Exception {
        Assert.assertEquals(76, partialContentParser.getMaxRange("", "78", data.length));
    }

    @Test
    public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
        Assert.assertEquals("a 206." , new String(partialContentParser.getPartialContent(data)));
    }

    @Test
    public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
        String byteRange = "0-6";
        PartialContentParser partialContentParser = new PartialContentParser(byteRange);

        Assert.assertEquals("This is" , new String(partialContentParser.getPartialContent(data)));
    }

    @Test
    public void returnsPartialContentForByteRangeThatExceeds() throws Exception {
        String byteRange = "-78";
        PartialContentParser partialContentParser = new PartialContentParser(byteRange);

        Assert.assertEquals("This is a file that contains text to read part of in order to fulfill a 206." , new String(partialContentParser.getPartialContent(data)));
    }

    @Test
    public void returns0WhenRangeExceedsTotal() {
        Assert.assertEquals(0, partialContentParser.getMinRange("", "78", data.length));
    }
}
