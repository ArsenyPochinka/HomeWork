import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;

    public Server() {
        try {
            socket = new ServerSocket(8089);
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

        this.scanner = new Scanner(System.in);

        listenInboundMessages();
        sendOutboundMessage();
    }

    private void listenInboundMessages() {
        new Thread(() -> {
            while (true) {
                try {
                    String inboundMessage = readInboundMessage();
                    System.out.println("MESSAGE FROM CLIENT: " + inboundMessage);
                } catch (MyServerException ex) {
                    System.out.println("The client is gone away.");
                    System.out.println("Connection gracefully closed.");
                    System.out.println("NOTE: Check log files for a detail information.");
                    break;
                }
            }
        }).start();
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

    private void sendOutboundMessage() {
            while (true) {
                try {
                    String outboundMessage = scanner.nextLine();
                    out.writeUTF(outboundMessage);
                } catch (IOException ex) {
                    throw new MyServerException("Connection closed.", ex);
                }
            }
    }

}
