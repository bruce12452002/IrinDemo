package teach0603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    public static void main(String[] args) throws IOException {
        Socket clientSocket;
//        while (true) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("等待中…");
            clientSocket = serverSocket.accept();
        }
//        new Thread(() -> {}){}.start();
        InputStream in = clientSocket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println("收到了=" + line);
        }
        System.out.println("完美結束");
//        }
    }
}
