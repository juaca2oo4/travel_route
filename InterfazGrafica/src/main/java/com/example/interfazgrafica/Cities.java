package com.example.interfazgrafica;

import com.example.interfazgrafica.model.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Cities implements Initializable{

    public static String information="";

    @FXML
    private Button backToMenuBTN;

    @FXML
    private Label citiesLB;


    @FXML
    void backMenu(ActionEvent event) {
            HelloApplication.openWindow("hello-view.fxml"); //Esta me abre el otro fxml, junto con su controlador.
            Stage stage = (Stage) backToMenuBTN.getScene().getWindow(); //Literalmente, me cierro a mi mismo.
            stage.close();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        citiesLB.setText(information);
    }
}
