module com.example.hopeitworks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hopeitworks to javafx.fxml;
    exports com.example.hopeitworks;
}