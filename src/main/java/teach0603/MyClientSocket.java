package teach0603;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyClientSocket {
    public static void main(String[] args) throws IOException {
        try (Socket clientSocket = new Socket("localhost", 12345)) {
            toServer(clientSocket);
//            receiveServer(clientSocket);
        }
    }

    private static void toServer(Socket clientSocket) throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        out.write("abcde".getBytes());
        System.out.println("client 端已送出資料");
    }

    private static void receiveServer(Socket clientSocket) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        String temp;
        while ((temp = in.readLine()) != null) {
            System.out.println("client 端收到了=" + temp);
        }
    }
}
