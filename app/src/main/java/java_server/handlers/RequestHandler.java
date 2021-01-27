package java_server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler {
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    public InputStream getInputStream() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    public OutputStream getOutputStream() {
        OutputStream outputStream = null;

        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
