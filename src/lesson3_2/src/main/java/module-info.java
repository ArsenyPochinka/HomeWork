module com.example.lesson3_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson3_2 to javafx.fxml;
    exports com.example.lesson3_2;
}