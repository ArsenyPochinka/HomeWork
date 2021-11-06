import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private Thread t1;
    private Thread t2;

    public Client() {
        try {
            this.socket = new Socket("127.0.0.1", 8888);
            this.scanner = new Scanner(System.in);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        authentication();
        listenInboundMessages();
        sendOutboundMessage();
    }

    private void authentication() {
        while (true) {
            System.out.print("Please, enter your login: ");
            String login = scanner.nextLine();
            System.out.print("Please, enter your password: ");
            String password = scanner.nextLine();
            try {
                out.writeUTF("-auth " + login + " " + password);
                String outboundMessage = readInboundMessage();
                System.out.println(outboundMessage);
                if(outboundMessage.equals("Correct data, you are connected to the chat!")) {
                    break;
                }
            } catch (EOFException ex) {
                throw new MyServerException("End of stream reached unexpectedly. Probably, server is shutdown.", ex);
            } catch (IOException ex) {
                throw new MyServerException("Something went wrong during inbound message read-operation.", ex);
            }
        }
    }

    private void listenInboundMessages() {
        t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String inboundMessage = readInboundMessage();
                    System.out.println(inboundMessage);
                } catch (MyServerException ex) {
                    System.out.println("The server is close.");
                    closeConnection();
                }
            }
        });
                t1.start();
    }

    private String readInboundMessage() {
        try {
            return in.readUTF();
        } catch (EOFException ex) {
            throw new MyServerException("End of stream reached unexpectedly. Probably, server is shutdown.", ex);
        } catch (IOException ex) {
            throw new MyServerException("Something went wrong during inbound message read-operation.", ex);
        }
    }

    private void sendOutboundMessage() {
            t2 = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    String outboundMessage = scanner.nextLine();
                    try {
                        out.writeUTF(outboundMessage);
                    } catch (EOFException ex) {
                        throw new MyServerException("End of stream reached unexpectedly. Probably, server is shutdown.", ex);
                    } catch (IOException ex) {
                        throw new MyServerException("Something went wrong during inbound message read-operation.", ex);
                    }
                }
            });
                t2.start();
    }

    public void closeConnection() {
        t2.interrupt();
        t1.interrupt();
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
        System.out.println("Connection gracefully closed.");
        System.out.println("NOTE: Check log files for a detail information.");
        scanner.close();
        System.exit(0);
    }

}
