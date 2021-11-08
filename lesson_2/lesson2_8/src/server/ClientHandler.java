package server;

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
    private String name;
    private volatile String message;
    private Thread t;

    public ClientHandler(Socket socket, ChatServer server) {
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


    private void doAuthentication()  {
        try {
            socket.setSoTimeout(12000);
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
            server.getAuthenticationService()
                    .findUsernameByLoginAndPassword(login, password)
                    .ifPresentOrElse(username -> {
                        if (!server.isUsernameOccupied(username)) {
                            server.broadcastMessage(String.format("%s is logged in", username));
                            sendMessage("Correct data, you are connected to the chat! \n\n");
                            name = username;
                            server.addClient(this);
                            isSuccess.set(true);
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

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Something went wrong during a client connection establishing..");
        }
    }

    public String readMessage() {
        try {
            message = in.readUTF();
            return message;
        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Something went wrong during a client connection establishing..");
        }
    }

    public void readAndBroadcastMessage() {
        server.broadcastMessage(this.getName() + " --> " + readMessage());
    }

    public String getMessage() {
        return message;
    }

    public void redirectMessages() {
        while (!Thread.currentThread().isInterrupted()) {
            readAndBroadcastMessage();
        }
    }

    public  void closing(){
        while (true) {
            if (getMessage().startsWith("/end")) {
                closeConnection();
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
