package java_server;

import java.io.File;

public class ArgsParser {
    String[] args;

    public ArgsParser(String[] args) {
        this.args = args;
    }

    public int getPort() {
        return (isAValidPortNumber()) ? Integer.parseInt(args[1]) : Constants.DEFAULT_PORT;
    }

    public String getDirectory() {
        return (isAValidDirectory()) ? args[0] : Constants.DEFAULT_SERVER_DIRECTORY;
    }

    public boolean isAValidPortNumber() {
        try {
            return args[1] != null && Integer.parseInt(args[1]) > 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isAValidDirectory() {
        try {

            if (args[0] != null) {
                File file = new File(args[0]);
                if (file != null)
                    return true;
            }

            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
