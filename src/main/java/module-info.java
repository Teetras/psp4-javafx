module com.example.psp4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.psp4 to javafx.fxml;
    exports com.example.psp4;
}