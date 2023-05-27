module com.example.interfazgrafica {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.interfazgrafica to javafx.fxml;
    exports com.example.interfazgrafica;
    exports com.example.interfazgrafica.model;
    opens com.example.interfazgrafica.model to javafx.fxml;
}