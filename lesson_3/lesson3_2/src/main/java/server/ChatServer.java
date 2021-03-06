package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatServer {

    private final ServerSocket socket;
    private final UserService userService;
    private final Set<ClientHandler> loggedClients;
    private final ExecutorService executorService;
    private static final Logger LOGGER = LogManager.getLogger(ChatServer.class);

    public ChatServer() {
        try {
            userService = new UserService();
            loggedClients = new HashSet<>();
            this.socket = new ServerSocket(8888);
            LOGGER.info("The chat service is running.");
            this.executorService = Executors.newCachedThreadPool();

            while (true) {
                LOGGER.debug("Waiting for a new connection...");
                Socket client = socket.accept();
                LOGGER.debug("Client accepted.");
                executorService.submit(() -> new ClientHandler(client, this));
            }
        } catch (IOException e) {
            LOGGER.error("Something went wrong during connection establishing.");
            throw new RuntimeException("Something went wrong during connection establishing.", e);
        }
    }

    public UserService getUserService() {
        return userService;
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
        }
            else if(splitMessage[2].equals("/ch")){
                changeUsername(splitMessage);
            } else {
            broadcastMessage(message);
        }
    }

    public synchronized void changeUsername(String[] splitMessage) {
        getUserService().changeUsername(splitMessage[0], splitMessage[3]);
        loggedClients.stream()
                        .filter(ch -> ch.getName().equals(splitMessage[0]))
                                .forEach(ch -> ch.setName(splitMessage[3]));
        loggedClients.forEach(ch -> ch.sendMessage(splitMessage[0] + " changed his/her name to " + splitMessage[3] + "\n"));
        LOGGER.debug("Some user changed his/her name");
    }

    public synchronized void hiddenMessage(String message,String[] splitMessage) {
        String hiddenMessage = splitMessage[0] + " (hidden message to " + splitMessage[3] + ") -->" + message.substring(message.indexOf(splitMessage[4]));
        loggedClients.stream()
                .filter(ch -> ch.getName().equals(splitMessage[3]) || ch.getName().equals(splitMessage[0]))
                .forEach(ch -> ch.sendMessage(hiddenMessage + "\n"));
        LOGGER.debug("Some user sent hidden message");
    }

    public synchronized void broadcastMessage(String message) {
        loggedClients.forEach(ch -> {
                ch.sendMessage(message + "\n");
                ch.recordingLocalHistory(message + "\n");
        });
        LOGGER.debug("The general message has been sent");
    }
}
