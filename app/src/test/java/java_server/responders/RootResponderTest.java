package java_server.responders;

import java_server.httpserver.StatusCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RootResponderTest {
    private StatusCode statusCode;

    @Before
    public void setUp() throws Exception {
        statusCode = new StatusCode();
    }

    @Test
    public void returnsTestFileLink() throws Exception {
        String serverViewsDirectory = "../../java_server/test-2";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("href='/test-file.txt'"));
        assertEquals(statusCode.OK, rootResponder.getStatusCode());
    }

    @Test
    public void returnsDirectoryLinks() throws Exception {
        String serverViewsDirectory = "../../java_server/test-2";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("href='/test-file.txt'"));
        assertTrue(new String(fileBytes).contains("href='/test-file-2.txt'"));
        assertEquals(statusCode.OK, rootResponder.getStatusCode());
    }

    @Test
    public void returnsEmptyStringWhenDirectoryHasNoContents() throws Exception {
        String serverViewsDirectory = "../../java_server/empty-test-directory";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        assertTrue(new String(fileBytes).equals(""));
        assertEquals(statusCode.OK, rootResponder.getStatusCode());
    }
}
