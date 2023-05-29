package teach0603;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyClientSocket {
    public static void main(String[] args) throws IOException {
        try (Socket clientSocket = new Socket("localhost", 12345)) {
            OutputStream out = clientSocket.getOutputStream();
            out.write("abcde".getBytes());
        }
    }
}
