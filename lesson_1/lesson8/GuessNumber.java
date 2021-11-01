package lesson_1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessNumber extends JFrame {
    private int randomNumber;
    private int numberOfGame = 3;

    private JTextField textField;

    public GuessNumber() {

        setTitle("Guess the number");
        setBounds(600, 300, 600, 140);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        textField = new JTextField();
        add(textField, BorderLayout.NORTH);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        setRandomNumber();

        Font font = new Font("Arial", Font.PLAIN, 18);
        textField.setFont(font);

        JButton[] buttons = new JButton[10];

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 10));
        add(buttonsPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 10; i++) {
            buttons[i - 1] = new JButton(String.valueOf(i));
            buttons[i - 1].setFont(font);
            buttonsPanel.add(buttons[i - 1]);
            int buttonIndex = i;
            buttons[i - 1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tryToAnswer(buttonIndex, buttons[buttonIndex - 1]);
                }
            });
        }

        JButton buttonrestart = new JButton("Restart");
        buttonrestart.setFont(font);
        add(buttonrestart, BorderLayout.SOUTH);
        buttonrestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRandomNumber();
                numberOfGame=3;
                for (JButton b : buttons) {
                    b.setBackground(null);
                }
            }
        });

        setVisible(true);
    }

    public void setRandomNumber() {
        randomNumber = (int) (Math.random() * 10) + 1; // [1 ; 10]
        textField.setText("The game has started! The program made a number from 1 to 10");
    }

    public void tryToAnswer(int answer, JButton button) {
        numberOfGame--;
        if(numberOfGame>0) {
            if (answer == randomNumber) {
                textField.setText("You have guessed! Answer: " + randomNumber);
                button.setBackground(Color.GREEN);
            } else if (answer > randomNumber) {
                textField.setText("You haven't guessed! The number is less. You have " + numberOfGame + " attempts");
            } else {
                textField.setText("You haven't guessed! The number is greater. You have " + numberOfGame + " attempts");
            }
        }
        else {
            textField.setText("Unfortunately, you are lose");
        }
    }
}

