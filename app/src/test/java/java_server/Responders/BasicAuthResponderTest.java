package java_server.Responders;

import java_server.Helpers.Constants;
import java_server.Helpers.StatusCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BasicAuthResponderTest {
    private String testDirectory;
    private String uri;
    private StatusCode statusCode = new StatusCode();

    @Before
    public void setUp() throws Exception {
        String testDirectory = Constants.DEFAULT_TEST_DIRECTORY;
        String uri = "logs";
    }

    @Test
    public void returnsAuthenticationRequiredIfRequestDoesNotHaveCredentials() throws Exception {
        String basicAuthCredentials = "invalid:credentials";
        BasicAuthResponder basicAuthResponder = new BasicAuthResponder(testDirectory, uri, basicAuthCredentials);

        assertEquals("Authentication required", new String(basicAuthResponder.getMessageBody()));
        assertEquals(statusCode.UNAUTHORIZED, basicAuthResponder.getStatusCode());
    }

    @Test
    public void returnsOkWhenSuccessfulAuthentication() throws Exception {
        String basicAuthCredentials = "admin:hunter2";
        BasicAuthResponder basicAuthResponder = new BasicAuthResponder(testDirectory, uri, basicAuthCredentials);
        basicAuthResponder.getMessageBody();

        assertEquals(statusCode.OK, basicAuthResponder.getStatusCode());
    }

    @Test
    public void returnsAuthenticationRequiredWhenNullCredentials() throws Exception {
        String basicAuthCredentials = null;
        BasicAuthResponder basicAuthResponder = new BasicAuthResponder(testDirectory, uri, basicAuthCredentials);
        basicAuthResponder.getMessageBody();

        assertEquals("Authentication required", new String(basicAuthResponder.getMessageBody()));
        assertEquals(statusCode.UNAUTHORIZED, basicAuthResponder.getStatusCode());
    }
}
