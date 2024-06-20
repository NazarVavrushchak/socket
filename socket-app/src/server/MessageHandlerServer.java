package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static server.Server.*;

public class MessageHandlerServer {
    public static final String HOST;

    static {
        try {
            HOST = Server.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static final int PORT = 8899;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = createServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = acceptClientSocket(serverSocket)) {
                    String message = readMessageFromSocket(clientSocket);
                    printMessage(clientSocket, message);
                    int number = Integer.parseInt(message);
                    long factorial = calculateFactorial(number);
                    sendMessageToSocket("Factorial of " + number + " is " + factorial, clientSocket);
                }
            }
        }
    }
}