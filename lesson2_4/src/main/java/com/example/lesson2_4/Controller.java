package com.example.lesson2_4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button button;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;

    @FXML
    protected void buttonClick() {
        textArea.setText(textArea.getText()+"\n" + textField.getText());
        textField.clear();
    }
}