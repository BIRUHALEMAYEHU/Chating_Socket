import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat server is running on port " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            System.out.println("Type 'exit' to end the chat.");

            while (true) {
                clientMessage = input.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                System.out.print("You: ");
                serverMessage = consoleInput.readLine();
                output.println(serverMessage);
                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Chat ended.");
                    break;
                }
            }

            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}