package java_server;

import java.io.File;
import java.util.Arrays;

public class ArgsParser {
    String[] args;

    public ArgsParser(String[] args) {
        this.args = args;
    }

    public static int findIndex(String arr[], String t)
    {
        int index = Arrays.asList(arr).indexOf(t);
        return (index < 0) ? -1 : index;
    }

    public int getPort() {
        return (isAValidPortNumber()) ? Integer.parseInt(args[findIndex(args, "-p") + 1]) : Constants.DEFAULT_PORT;
    }

    public String getDirectory() {
        return (isAValidDirectory()) ? String.valueOf(args[findIndex(args, "-d") + 1]) : Constants.DEFAULT_SERVER_DIRECTORY;
    }

    public boolean isAValidPortNumber() {
        try {
            return args[findIndex(args, "-p") + 1] != null && Integer.parseInt(args[findIndex(args, "-p") + 1]) > 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isAValidDirectory() {
        try {

            if (args[findIndex(args, "-d") + 1] != null) {
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
