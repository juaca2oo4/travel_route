package com.example.interfazgrafica;

import com.example.interfazgrafica.model.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {

    Controller controller = new Controller();
    Cities cities = new Cities();
    static boolean isLoaded;

    @FXML
    private Button loadDB_BTN;

    @FXML
    private Button optionsBTN;

    @FXML
    private Button seeCitiesBTN;
    @FXML
    void loadDB(ActionEvent event) {
        if(!isLoaded) {
            String rutaProyecto = System.getProperty("user.dir");
            String rutaCarpetaData = rutaProyecto + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "example" + File.separator + "interfazgrafica" + File.separator + "data";
            String nombreArchivo = "cities.txt";
            File archivo = new File(rutaCarpetaData, nombreArchivo);
            String rutaAbsoluta = archivo.getAbsolutePath();
            controller.addDataVertex(rutaAbsoluta);

            String nombreArchivoEdge = "connections.txt";
            File archivo2 = new File(rutaCarpetaData, nombreArchivoEdge);
            String rutaAbsoluta2 = archivo2.getAbsolutePath();
            controller.addDataEdgeList(rutaAbsoluta2);
            controller.addDataEdgeMatriz(rutaAbsoluta2);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully importation");
            alert.setContentText("The data base has been loaded successfully");
            alert.showAndWait();

            Cities.information = controller.printCities();
            isLoaded = true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("The data base has been already loaded");
            alert.showAndWait();
        }
    }
    @FXML
    void seeCities(ActionEvent event) {
        if(isLoaded){
            HelloApplication.openWindow("Cities.fxml"); //Esta me abre el otro fxml, junto con su controlador.
            Stage stage = (Stage) seeCitiesBTN.getScene().getWindow(); //Literalmente, me cierro a mi mismo.
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Caution");
            alert.setContentText("The data base has not been loaded");
            alert.showAndWait();
        }
    }
}