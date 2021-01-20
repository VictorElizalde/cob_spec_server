package java_server.Responders;

import java_server.Helpers.Constants;
import java_server.Helpers.StatusCode;
import java_server.Responders.CRUDResponder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CRUDResponderTest {
    private StatusCode statusCode;
    private String testDirectory;

    @Before
    public void setUp() {
        testDirectory = Constants.DEFAULT_TEST_DIRECTORY;
    }

    @Test
    public void createdFileWhenPutRequestIsMadeAndReturns201() throws Exception {
        String uri = "new_file.txt";
        String httpMethod = "PUT";
        String content = "Some text for a new file";

        CRUDResponder crudResponder = new CRUDResponder(testDirectory, httpMethod, uri, content);
        byte[] fileBytes = crudResponder.getMessageBody();

        Assert.assertEquals("Some text for a new file", new String(fileBytes));

        Assert.assertEquals(statusCode.CREATED, crudResponder.getStatusCode());
    }

    @Test
    public void updatesFileWhenPutRequestIsMadeAndReturns200() throws Exception {
        String uri = "new_file.txt";
        String httpMethod = "PUT";
        String content = "Some updated text";

        CRUDResponder crudResponder = new CRUDResponder(testDirectory, httpMethod, uri, content);
        byte[] fileBytes = crudResponder.getMessageBody();

        Assert.assertEquals("Some updated text", new String(fileBytes));

        Assert.assertEquals(statusCode.OK, crudResponder.getStatusCode());
    }

    @Test
    public void deletesFileWhenDeleteRequestIsMade() throws Exception {
        String uri = "new_file.txt";
        String httpMethod = "DELETE";
        String content = "";

        CRUDResponder crudResponder = new CRUDResponder(testDirectory, httpMethod, uri, content);
        byte[] fileBytes = crudResponder.getMessageBody();

        Assert.assertEquals("File Could Not Be Read", new String(fileBytes));

        Assert.assertEquals(statusCode.OK, crudResponder.getStatusCode());
    }
}
