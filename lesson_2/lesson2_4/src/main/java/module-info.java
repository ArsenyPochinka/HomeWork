module com.example.lesson2_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson2_4 to javafx.fxml;
    exports com.example.lesson2_4;
}