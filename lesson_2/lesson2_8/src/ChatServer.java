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
                System.out.println("Client accepted.");
                new Thread(() -> new ClientHandler(client, this)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during connection establishing.", e);
        }
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void addClient(ClientHandler client) {
        loggedClients.add(client);
    }

    public void removeUsername(ClientHandler client) {
        loggedClients.remove(client);
    }

    public boolean isUsernameOccupied(String username) {
        return loggedClients.stream()
                .anyMatch(c -> c.getName().equals(username));
    }

    public void broadcastMessage(String message) {
            String[] splitMessage = message.split("\\s");
            if(splitMessage[2].equals("/w")) {
            String hiddenMessage = splitMessage[0] + " (hidden message) --> " + message.substring(message.indexOf(splitMessage[4]));
            loggedClients.stream()
                    .filter(ch -> ch.getName().equals(splitMessage[3]))
                    .findFirst()
                    .get().sendMessage(hiddenMessage);
        } else if(loggedClients.stream().anyMatch(ch -> ch.getName().equals(splitMessage[0]))) {
                loggedClients
                        .stream()
                        .filter(ch -> !(ch.getName().equals(splitMessage[0])))
                        .forEach(ch -> ch.sendMessage(message));
        }
        else {
            loggedClients.forEach(ch -> ch.sendMessage(message));
        }
    }
}
