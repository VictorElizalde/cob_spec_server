package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.*;
import java.util.Arrays;
import static org.junit.matchers.JUnitMatchers.hasItems;

import static org.junit.Assert.assertTrue;

public class RoutesTest {
    Request request;
    Routes routes;
    String testDirectory;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        String testDirectory = "../cob_spec/public";
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
    public void returnsFalseIfFileIsNotAMatchedFile() throws Exception {
        request.setHTTPMethod("GET");
        request.setURI("file_ghost");

        Assert.assertEquals(false, routes.isAValidMethod(request));
    }

    @Test
    public void returnsACollectionOfFileNamesWhenGivenADirectory() throws Exception {
        File file = new File(testDirectory);
        String[] fileList = file.list();

        Assert.assertThat(Arrays.asList(routes.getDirectoryFileNames()), hasItems(fileList));
    }

    @Test(expected = NullPointerException.class)
    public void throwsNullPointerExceptionWhenDirectoryDoesNotExist() throws Exception {
        String directory = "";
        File file = new File(directory);
        String[] fileList = file.list();

        Assert.assertThat(Arrays.asList(routes.getDirectoryFileNames()), hasItems(fileList));
    }
}
