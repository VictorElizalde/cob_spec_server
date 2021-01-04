package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileResponderTest {
    private statusCode statusCode;
    private String testDirectory;

    @Before
    public void setUp() throws Exception {
        testDirectory = "/Users/victorelizalde/Documents/Github/cob_spec/public";
        statusCode = new statusCode();
    }

    @Test
    public void returnsTheContentOfFile1File() throws Exception {
        String uri = "file1";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertEquals("file1 contents", new String(fileBytes));

        Assert.assertEquals(statusCode.getStatus(200), fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsTheContentOfTextFile() throws Exception {
        String uri = "text-file.txt";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertEquals("file1 contents", new String(fileBytes));

        Assert.assertEquals(statusCode.getStatus(200), fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsThePathOfImageJPGFile() throws Exception {
        String uri = "image.jpeg";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertTrue(Arrays.equals(Files.readAllBytes(Paths.get("/Users/victorelizalde/Documents/Github/cob_spec/public/image.jpeg")), fileBytes));

        Assert.assertEquals(statusCode.getStatus(200), fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsThePathOfImagePNGFile() throws Exception {
        String uri = "image.png";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertTrue(Arrays.equals(Files.readAllBytes(Paths.get("/Users/victorelizalde/Documents/Github/cob_spec/public/image.png")), fileBytes));

        Assert.assertEquals(statusCode.getStatus(200), fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsThePathOfImageGIFFile() throws Exception {
        String uri = "image.gif";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        byte[] fileBytes = fileResponse.getMessageBody();
        Assert.assertTrue(Arrays.equals(Files.readAllBytes(Paths.get("/Users/victorelizalde/Documents/Github/cob_spec/public/image.gif")), fileBytes));

        Assert.assertEquals(statusCode.getStatus(200), fileResponse.getStatusCode(statusCode));
    }

    @Test
    public void returnsFileCouldNotBeReadWhenURIInvalid() throws Exception {
        String uri = "GhostURI";
        FileResponder fileResponse = new FileResponder(testDirectory, uri);
        fileResponse.getMessageBody();

        Assert.assertEquals("File Could Not Be Read", new String(fileResponse.getMessageBody()));
    }
}

