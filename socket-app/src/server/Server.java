package server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Server() {
    }

    public static String getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static Socket acceptClientSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public static String readMessageFromSocket(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();
    }

    public static void printMessage(Socket socket, String message) {
        InetAddress clientAddress = socket.getInetAddress();
        System.out.print(LocalDateTime.now().format(TIME_FORMATTER) + " ");
        System.out.printf("[%s]", clientAddress.getHostAddress());
        System.out.println(" -- " + message);
    }

    public static void sendMessageToSocket(String message, Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public static long calculateFactorial(int number) {
        int factorial = 1;
        if (number == 0 || number == 1) {
            return 1;
        } else {
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
        }
        return factorial;
    }
}