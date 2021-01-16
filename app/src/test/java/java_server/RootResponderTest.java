package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RootResponderTest {
    @Test
    public void returnsTestFileLink() throws Exception {
        statusCode statusCode = new statusCode();
        String serverViewsDirectory = "../../java_server/test-2";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("href='/test-file.txt'"));
        assertEquals(statusCode.getStatus(200), rootResponder.getStatusCode(statusCode));
    }

    @Test
    public void returnsDirectoryLinks() throws Exception {
        statusCode statusCode = new statusCode();
        String serverViewsDirectory = "../../java_server/test-2";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("href='/test-file.txt'"));
        assertTrue(new String(fileBytes).contains("href='/test-file-2.txt'"));
        assertEquals(statusCode.getStatus(200), rootResponder.getStatusCode(statusCode));
    }

    @Test
    public void returnsEmptyStringWhenDirectoryHasNoContents() throws Exception {
        statusCode statusCode = new statusCode();
        String serverViewsDirectory = "../../java_server/empty-test-directory";
        RootResponder rootResponder = new RootResponder(serverViewsDirectory);
        byte[] fileBytes = rootResponder.getMessageBody();

        assertTrue(new String(fileBytes).equals(""));
        assertEquals(statusCode.getStatus(200), rootResponder.getStatusCode(statusCode));
    }
}
