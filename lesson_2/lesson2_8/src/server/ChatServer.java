package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {

    private final ServerSocket socket;
    private final AuthenticationService authenticationService;
    private final Set<ClientHandler> loggedClients;

    public ChatServer() {
        try {
            authenticationService = new AuthenticationService();
            loggedClients = new HashSet<>();
            this.socket = new ServerSocket(8888);

            while (true) {
                System.out.println("Waiting for a new connection...");
                Socket client = socket.accept();
                System.out.println("Client.Client accepted.");
                new Thread(() -> new ClientHandler(client, this)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during connection establishing.", e);
        }
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public synchronized void addClient(ClientHandler client) {
        loggedClients.add(client);
    }

    public synchronized void removeUsername(ClientHandler client) {
        loggedClients.remove(client);
    }

    public synchronized boolean isUsernameOccupied(String username) {
        return loggedClients.stream()
                .anyMatch(c -> c.getName().equals(username));
    }

    public synchronized void redirectMessage(String message) {
            String[] splitMessage = message.split("\\s");
            if(splitMessage[2].equals("/w")) {
            hiddenMessage(message, splitMessage);
        } else {
            broadcastMessage(message);
        }
    }

    public synchronized void hiddenMessage(String message,String[] splitMessage) {
        String hiddenMessage = splitMessage[0] + " (hidden message to " + splitMessage[3] + ") -->" + message.substring(message.indexOf(splitMessage[4]));
        loggedClients.stream()
                .filter(ch -> ch.getName().equals(splitMessage[3]) || ch.getName().equals(splitMessage[0]))
                .forEach(ch -> ch.sendMessage(hiddenMessage + "\n"));
    }

    public synchronized void broadcastMessage(String message) {
        loggedClients.forEach(ch -> ch.sendMessage(message + "\n"));
    }
}
