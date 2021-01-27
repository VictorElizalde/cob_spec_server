package java_server.Parsers;

import java_server.Helpers.Constants;

import java.io.File;
import java.util.Arrays;

public class ArgsParser {
    String[] args;

    public ArgsParser(String[] args) {
        this.args = args;
    }

    public static int findIndex(String arr[], String t)
    {
        return Arrays.asList(arr).indexOf(t);
    }

    public int getPort() {
        return (isAValidPortNumber()) ? Integer.parseInt(args[findIndex(args, "-p") + 1]) : Constants.DEFAULT_PORT;
    }

    public String getDirectory() {
        return (isAValidDirectory()) ? String.valueOf(args[findIndex(args, "-d") + 1]) : Constants.DEFAULT_SERVER_DIRECTORY;
    }

    private boolean isAValidPortNumber() {
        try {
            return findIndex(args, "-p") != -1 && args[findIndex(args, "-p") + 1] != null && Integer.parseInt(args[findIndex(args, "-p") + 1]) > 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isAValidDirectory() {
        try {

            if (findIndex(args, "-d") != -1 && args[findIndex(args, "-d") + 1] != null) {
                File file = new File(args[findIndex(args, "-d") + 1]);
                if (file != null)
                    return true;
            }

            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
