package java_server;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;

public class RoutesTest {
    private Request request;
    private Routes routes;
    private String testDirectory;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        testDirectory = Constants.DEFAULT_TEST_DIRECTORY;
        routes = new Routes(testDirectory);
    }

    @Test
    public void returnsTrueIfMethodIsValidOnUri() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals(true, routes.isAValidMethod(request));
    }

    @Test
    public void returnsFalseIfMethodIsNotValidOnUri() throws Exception {
        request.setHTTPMethod("POST");
        request.setURI("file1");

        Assert.assertEquals(false, routes.isAValidMethod(request));
    }

    @Test
    public void returnsTrueIfMethodIsRootRequest() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("/");

        Assert.assertEquals(true, routes.isAValidMethod(request));
    }

    @Test
    public void returnsFalseIfFileIsNotAMatchedFile() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file_ghost");

        Assert.assertEquals(false, routes.isAValidMethod(request));
    }

    @Test
    public void returnsACollectionOfFileNamesWhenGivenADirectory() throws Exception {
        File file = new File(testDirectory);
        String[] fileList = file.list();

        Assert.assertThat(Arrays.asList(routes.getDirectoryFileNames()), CoreMatchers.hasItems(fileList));
    }

    @Test
    public void returnTrueIfFile1UriExistsInDirectory() throws Exception {
        String[] fileList = {"file1", "file2", "file3"};
        request.setURI("file1");

        Assert.assertEquals(true, routes.isAnExistingFileInDirectory(fileList, request));
    }

    @Test
    public void returnTrueIfFile2UriExistsInDirectory() throws Exception {
        String[] fileList = {"file1", "file2", "file3"};
        request.setURI("file2");

        Assert.assertEquals(true, routes.isAnExistingFileInDirectory(fileList, request));
    }

    @Test
    public void returnsANullPointerExceptionStringIfUriDoesntExistInDirectory() throws Exception {
        String [] fileList = {"file1", "file2", "file3"};
        request.setURI("ghostFile1");

        Assert.assertEquals(false, routes.isAnExistingFileInDirectory(fileList, request));
    }

    @Test
    public void returnsFileResponseInstanceWhenValid() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertThat(routes.getHandler(request), instanceOf(FileResponder.class));
    }

    @Test
    public void returnsTheMethodOptionsForFile1Path() throws Exception {
        request.setHTTPMethod("OPTIONS");
        request.setURI("file1");

        Assert.assertEquals("HEAD,DELETE,GET,OPTIONS,PUT", routes.getOptions(request));
    }

    @Test
    public void returnsRootHandlerWhenRootRequestIsMade() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("/");

        Assert.assertThat(routes.getHandler(request), instanceOf(RootResponder.class));
    }

    @Test
    public void returnsHead() throws Exception {
        request.setHTTPMethod("HEAD");
        request.setURI("/");

        Assert.assertThat(routes.getHandler(request), instanceOf(HeadResponder.class));
    }

    @Test
    public void returnsOptions() throws Exception {
        request.setHTTPMethod("OPTIONS");
        request.setURI("file1");

        Assert.assertThat(routes.getHandler(request), instanceOf(FileResponder.class));
    }

    @Test
    public void returnsNotFound() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("foobar");

        Assert.assertThat(routes.getHandler(request), instanceOf(NotFoundResponder.class));
    }

    @Test
    public void returnsMethodNotAllowed() throws Exception {
        request.setHTTPMethod("POST");
        request.setURI("file1");

        Assert.assertThat(routes.getHandler(request), instanceOf(MethodNotAllowedResponder.class));
    }

    @Test
    public void returnsNotImplemented() throws Exception {
        request.setHTTPMethod("AAFFSS");
        request.setURI("file1");

        Assert.assertThat(routes.getHandler(request), instanceOf(NotImplementedResponder.class));
    }
}
