package java_server.responders;

import java_server.Constants;
import java_server.httpserver.StatusCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

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
        crudResponder.processResponse();
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
        crudResponder.processResponse();
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
        crudResponder.processResponse();
        byte[] fileBytes = crudResponder.getMessageBody();

        Assert.assertEquals("File Could Not Be Read", new String(fileBytes));

        Assert.assertEquals(statusCode.OK, crudResponder.getStatusCode());
    }
}
