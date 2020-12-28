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
        String testDirectory = "/Users/victorelizalde/Documents/Github/cob_spec/public";
        routes = new Routes(testDirectory);
    }

    @Test
    public void returnsTrueIfMethodIsValidOnURI() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file1");

        Assert.assertEquals(true, routes.isAValidMethod(request));
    }

    @Test
    public void returnsFalseIfMethodIsNotValidOnURI() throws Exception {
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
        File file = new File("/Users/victorelizalde/Documents/Github/cob_spec/public");
        String[] fileList = file.list();

        Assert.assertThat(Arrays.asList(routes.getDirectoryFileNames()), CoreMatchers.hasItems(fileList));
    }

    @Test(expected = NullPointerException.class)
    public void throwsNullPointerExceptionWhenDirectoryDoesNotExist() throws Exception {
        File file = new File("");
        String[] fileList = file.list();

        Assert.assertThat(Arrays.asList(routes.getDirectoryFileNames()), CoreMatchers.hasItems(fileList));
    }

    @Test
    public void returnTrueIfFile1URIExistsInDirectory() throws Exception {
        String[] fileList = {"file1", "file2", "file3"};
        request.setURI("file1");

        Assert.assertEquals(true, routes.isAnExistingFileInDirectory(fileList, request));
    }

    @Test
    public void returnTrueIfFile2URIExistsInDirectory() throws Exception {
        String[] fileList = {"file1", "file2", "file3"};
        request.setURI("file2");

        Assert.assertEquals(true, routes.isAnExistingFileInDirectory(fileList, request));
    }

    @Test
    public void returnsANullPointerExceptionStringIfURIDoesntExistInDirectory() throws Exception {
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
}
