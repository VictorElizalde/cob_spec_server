package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileResponderTest {
    private statusCode statusCode;
    private String testDirectory;

    @Before
    public void setUp() throws Exception {
        testDirectory = "/Users/victorelizalde/Documents/Github/cob_spec/public";
        statusCode = new statusCode();
    }

    @Test
    public void returnsThePathOfTheRequestedUriFile() throws Exception {
        String uri = "file1";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertEquals("file1 contents", new String(fileBytes));

        Assert.assertEquals("200 OK", fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsFileCouldNotBeReadWhenUriInvalid() throws Exception {
        String uri = "GhostURI";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        fileResponse.getMessageBody();

        Assert.assertEquals("File Could Not Be Read", new String(fileResponse.getMessageBody()));
    }
}

