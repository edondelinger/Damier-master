module com.example.damier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.damier to javafx.fxml;
    exports com.example.damier;
}