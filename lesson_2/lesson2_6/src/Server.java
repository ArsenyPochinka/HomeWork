import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        try {
            socket = new ServerSocket(8080);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        System.out.println("Server socket started...");
        System.out.println("Server socket is waiting for a connection...");
        Socket accepted = socket.accept();
        System.out.println("Client connected...");
        System.out.println("Address: " + accepted.getInetAddress());

        in = new DataInputStream(accepted.getInputStream());
        out = new DataOutputStream(accepted.getOutputStream());

        listenInboundMessages();
    }

    private void listenInboundMessages() {
        try {
            while (true) {
                String message = readInboundMessage();
                System.out.println("Inbound message detected...");
                System.out.println("Message: " + message);
                sendOutboundMessage(message);
            }
        } catch (MyServerException ex) {
            System.out.println("The client is gone away.");
            System.out.println("Connection gracefully closed.");
            System.out.println("NOTE: Check log files for a detail information.");
        }
    }

    private void sendOutboundMessage(String message) {
        try {
            out.writeUTF("ECHO: " + message);
        } catch (IOException ex) {
            throw new MyServerException("Something went wrong during inbound message read-operation.", ex);
        }
    }

    private String readInboundMessage() {
        try {
            return in.readUTF();
        } catch (EOFException ex) {
            throw new MyServerException("End of stream reached unexpectedly. Probably, client is shutdown.", ex);
        } catch (IOException ex) {
            throw new MyServerException("Something went wrong during inbound message read-operation.", ex);
        }
    }
}
