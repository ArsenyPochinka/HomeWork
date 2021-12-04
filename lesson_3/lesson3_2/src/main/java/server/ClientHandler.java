package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {

    private final Socket socket;
    private final ChatServer server;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final LocalHistory localHistory;
    private String name;
    private volatile String message;
    private final Thread t;
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket, ChatServer server) {
        localHistory = new LocalHistory();
        // There can be any string here
        this.message = "start client";
        this.socket = socket;
        this.server = server;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            closeConnection();
            throw new RuntimeException("Something went wrong during a client connection establishing.");
        }
        new Thread(this::closing).start();
        t = new Thread(() -> {
            doAuthentication();
            redirectMessages();
        });
        t.start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void doAuthentication()  {
        try {
            socket.setSoTimeout(120000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (!Thread.currentThread().isInterrupted()) {
            sendMessage("Please, input login: ");
            String login = readMessage();
            sendMessage(login + "\n");
            sendMessage("Please, input password: ");
            String password = readMessage();
            sendMessage(password + "\n");

                AtomicBoolean isSuccess = new AtomicBoolean(false);
            server.getUserService()
                    .findUsernameByLoginAndPassword(login, password)
                    .ifPresentOrElse(username -> {
                        if (!server.isUsernameOccupied(username)) {
                            server.broadcastMessage(String.format("%s is logged in", username));
                            sendMessage("Correct data, you are connected to the chat! \n\n" +
                                    "*If you want to send a hidden message, write \"/w\" \nand then recipient's username at the beginning of the line. \nFor example: \"/w user2...\" \n\n" +
                                    "*If you want to change your username, write \"/ch\" \nand then your new username at the beginning of the line. \nFor example: \"/ch user4...\" \n\n" +
                                    "*If you want to leave the chat, write \"/end\" \n\n");

                            name = username;
                            server.addClient(this);
                            isSuccess.set(true);

                            sendLocalHistory();
                        } else {
                            sendMessage("This user is already connected. \n\n");
                        }
                    }, () -> sendMessage("Bad credentials. \n\n"));

                if (isSuccess.get()) {
                    try {
                        socket.setSoTimeout(0);
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
    }

    public void sendLocalHistory() {
        localHistory.doBufferedInputStream();
        int length = localHistory.getStringsList().size();
        sendMessage("**************Local history of chat**************\n!!!!!!!!!!!!Hidden messages are not displayed here!!!!!!!!!!!!\n");
        if(length>100) {
            localHistory.getStringsList().subList(length-100, length)
                    .forEach(str -> this.sendMessage(str + "\n"));
        }
        else {
            localHistory.getStringsList()
                    .forEach(str -> this.sendMessage(str + "\n"));
            }
        sendMessage("**************Local history of chat**************\n");
    }

    public void recordingLocalHistory(String message) {
        localHistory.doBufferedOutputStream(message);
    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            LOGGER.error("Something went wrong during connection establishing.");
            closeConnection();
            throw new RuntimeException("Something went wrong during a client connection establishing..");
        }
    }

    public String readMessage() {
        try {
            message = in.readUTF();
            return message;
        } catch (IOException e) {
            LOGGER.error("Something went wrong during connection establishing.");
            closeConnection();
            throw new RuntimeException("Something went wrong during a client connection establishing..");
        }
    }

    public void readAndRedirectMessage() {
        server.redirectMessage(this.getName() + " --> " + readMessage());
    }

    public String getMessage() {
        return message;
    }

    public void redirectMessages() {
        while (!Thread.currentThread().isInterrupted()) {
            readAndRedirectMessage();
        }
    }

    public  void closing(){
        while (true) {
            if (getMessage().startsWith("/end")) {
                closeConnection();
                LOGGER.debug("Client exited.");
                break;
            }
        }
    }

    public void closeConnection() {
        t.interrupt();
        server.broadcastMessage("Attention: " + this.getName() + " exited the chat.");
        server.removeUsername(this);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
