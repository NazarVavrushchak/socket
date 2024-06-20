package client;

import server.MessageHandlerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import static client.Client.*;

public class MessageHandlerClient {
    private static final String SERVER_ADDRESS = MessageHandlerServer.HOST;
    private static final int SERVER_PORT = MessageHandlerServer.PORT;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = openConsoleReader()) {
            String message = readMessage(reader);

            while (!message.equals("q")) {
                try (Socket socket = openSocket(SERVER_ADDRESS, SERVER_PORT)) {
                    writeToSocket(message, socket);
                    String response = readFromSocket(socket);
                    System.out.println("Server response: " + response);
                }
                message = readMessage(reader);
            }
        }
    }
}