import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("welcome to the chat");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            System.out.println("Type 'exit' to end the chat.");

            while (true) {
                System.out.print("You: ");
                clientMessage = consoleInput.readLine();
                output.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Chat ended.");
                    break;
                }

                serverMessage = input.readLine();
                if (serverMessage == null || serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("sorry the server does't like the way you talk :-( ");
                    break;
                }
                System.out.println("Srever: " + serverMessage);
            }

            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
