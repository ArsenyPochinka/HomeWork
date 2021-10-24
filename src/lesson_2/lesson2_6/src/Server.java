import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

    private final int SERVER_PORT = 8189;

    private JTextField msgInputField;
    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        socket = null;
        try {
            connection();
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public void connection() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("The server is running, waiting for connection...");
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("The client is connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String strFromClient = in.readUTF();
                        if (strFromClient.equalsIgnoreCase("/end")) {
                            break;
                        }
                        chatArea.append(strFromClient);
                        chatArea.append("\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

        public void sendMessage() {
            if (!msgInputField.getText().trim().isEmpty()) {
                try {
                    out.writeUTF(msgInputField.getText());
                    msgInputField.setText("");
                    msgInputField.grabFocus();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error sending the message");
                }
            }
        }

    public void closeConnection() {
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

    public void prepareGUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Send");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        msgInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server();
                    }
                });
            }
    }

