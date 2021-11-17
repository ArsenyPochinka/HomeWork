package gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame {

    private final JFrame mainFrame;
    private final gui.ChattingFrame chattingFrame;
    private final gui.SendingFrame sendingFrame;

    public ChatFrame(Consumer<String> onSubmit) {
        mainFrame = new JFrame();
        chattingFrame = new gui.ChattingFrame();
        sendingFrame = new gui.SendingFrame(onSubmit);
        init();
    }

    private void init() {
        mainFrame.setBounds(new Rectangle(500, 700));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("Chat v1.0");

        mainFrame.add(chattingFrame.getFrame(), BorderLayout.CENTER);
        mainFrame.add(sendingFrame.getFrame(), BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    public Consumer<String> onReceive() {
        return chattingFrame.getOnReceive();
    }

}
