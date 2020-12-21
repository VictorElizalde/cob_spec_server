package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileResponseTest {
    private statusCode statusCode;
    private String testDirectory;

    @Before
    public void setUp() throws Exception {
        testDirectory = "/Users/victorelizalde/Documents/Github/cob_spec/public";
        statusCode = new statusCode();
    }

    @Test
    public void returnsThePathOfTheRequestedURIFile() throws Exception {
        String uri = "file1";
        FileResponse fileResponse = new FileResponse(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertEquals("file1 contents", new String(fileBytes));

        Assert.assertEquals("200 OK", fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsFileCouldNotBeReadWhenURIInvalid() throws Exception {
        String uri = "GhostURI";
        FileResponse fileResponse = new FileResponse(testDirectory, uri);
        fileResponse.getMessageBody();

        Assert.assertEquals("File Could Not Be Read", new String(fileResponse.getMessageBody()));
    }
}