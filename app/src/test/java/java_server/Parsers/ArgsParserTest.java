package java_server.Parsers;

import java_server.Helpers.Constants;
import java_server.Parsers.ArgsParser;
import org.junit.Assert;
import org.junit.Test;

public class ArgsParserTest {

    @Test
    public void returnsFalseIfPortIsNotValid() throws Exception {
        String[] args = {"-p", "-1"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsFalseIfPortIsNull() throws Exception {
        String[] args = {"-p", null};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void settingPortNumberAndDefaultDirectory() throws Exception {
        String[] args = {"-p","5000"};
        ArgsParser argsParser = new ArgsParser(args);

        Assert.assertEquals(5000, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTheDefaultPortIfPortIsInvalid() throws Exception {
        String[] args = {"-p", "-1"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTheDefaultOptionsWhenSendingFakeTagsAndInfo() throws Exception {
        String[] args = {"-x", "foo"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTheDefaultOptionsWhenPassingDirectoryWithNoInfo() throws Exception {
        String[] args = {"-d"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTheDefaultOptionsWhenPassingPortWithNoInfo() throws Exception {
        String[] args = {"-p"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsDefaultPortIfArgIsNull() throws Exception {
        String[] args = {};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTheDirectory() throws Exception {
        String[] args = {"-d", Constants.DEFAULT_TEST_DIRECTORY};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_TEST_DIRECTORY, argsParser.getDirectory());
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
    }

    @Test
    public void returnsTheDefaultDirectoryWhenNull() throws Exception {
        String[] args = {};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
    }

    @Test
    public void returnsTrueIfDirectoryIsAValidString() throws Exception {
        String[] args = {"-d", Constants.DEFAULT_TEST_DIRECTORY};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertTrue(argsParser.isAValidDirectory());
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
    }

    @Test
    public void returnsFalseIfDirectoryIsNotValid() throws Exception {
        String[] args = {"-d", null};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertFalse(argsParser.isAValidDirectory());
        Assert.assertEquals(Constants.DEFAULT_PORT, argsParser.getPort());
    }
}
