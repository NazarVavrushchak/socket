package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Client() {
    }

    public static Socket openSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public static BufferedReader openConsoleReader() {
        InputStreamReader consoleInputStream = new InputStreamReader(System.in);
        return new BufferedReader(consoleInputStream);
    }

    public static String readMessage(BufferedReader reader) throws IOException {
        System.out.print("Enter message (q to quit): ");
        return reader.readLine();
    }

    public static void writeToSocket(String message, Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(message);
        bufferedWriter.flush();
    }

    public static String readFromSocket(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return bufferedReader.readLine();
    }
}