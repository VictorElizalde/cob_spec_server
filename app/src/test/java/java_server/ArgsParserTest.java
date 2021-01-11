package java_server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArgsParserTest {
    @Test
    public void returnsTrueIfPortIsValid() throws Exception {
        String[] args = {"", "5000"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertTrue(argsParser.isAValidPortNumber());
    }

    @Test
    public void returnsFalseIfPortIsNotValid() throws Exception {
        String[] args = {"", "-1"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertFalse(argsParser.isAValidPortNumber());
    }

    @Test
    public void returnsThePortNumber() throws Exception {
        String[] args = {"", "5000"};
        ArgsParser argsParser = new ArgsParser(args);

        Assert.assertEquals(5000, argsParser.getPort());
    }

    @Test
    public void returnsTheDefaultPortIfPortIsInvalid() throws Exception {
        String[] args = {"", "-1"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(5000, argsParser.getPort());
    }

    @Test
    public void returnsDefaultPortIfArgIsNull() throws Exception {
        String[] args = {"", null};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(5000, argsParser.getPort());
    }

    @Test
    public void returnsTheDirectory() throws Exception {
        String[] args = {Constants.DEFAULT_TEST_DIRECTORY, "5000"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_TEST_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTheDefaultDirectoryWhenNull() throws Exception {
        String[] args = {null, "5000"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
    }

    @Test
    public void returnsTrueIfDirectoryIsAValidString() throws Exception {
        String[] args = {Constants.DEFAULT_TEST_DIRECTORY, "5000"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertTrue(argsParser.isAValidDirectory());
    }

    @Test
    public void returnsFalseIfDirectoryIsNotValid() throws Exception {
        String[] args = {null, "5000"};
        ArgsParser argsParser = new ArgsParser(args);
        Assert.assertFalse(argsParser.isAValidDirectory());
    }
}
