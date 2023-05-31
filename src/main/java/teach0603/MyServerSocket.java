package teach0603;

import java.io.*;
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
        receiveClient(clientSocket);
//        toClient(clientSocket);
//        }
    }

    private static void receiveClient(Socket clientSocket) throws IOException {
        InputStream in = clientSocket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println("server 端收到了=" + line);
        }
        System.out.println("結束");
    }

    private static void toClient(Socket clientSocket) throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        out.write("data".getBytes());
        out.flush();
        System.out.println("server 端已送出資料");
    }
}
