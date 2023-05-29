package com.example.interfazgrafica;

import com.example.interfazgrafica.model.Controller;
import com.example.interfazgrafica.model.Vertex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectOptionController implements Initializable {

    static List<Vertex<String>> cities = Controller.getInstance().getGraph().getVertices();

    @FXML
    private ChoiceBox<String> destinyCityCB;

    @FXML
    private Button getBestRouteBTN;

    @FXML
    private Button goToMenuBTN;

    @FXML
    private ChoiceBox<String> kindOfGraphCB;

    @FXML
    private ChoiceBox<String> originCityCB;

    @FXML
    private ChoiceBox<String> typeOfTravelingCB;

    @FXML
    void getBestRoute(ActionEvent event) {
        String city1 = originCityCB.getValue();
        String city2 = destinyCityCB.getValue();
        String election = typeOfTravelingCB.getValue();
        if(kindOfGraphCB!=null&&typeOfTravelingCB.getValue()!=null&&originCityCB.getValue()!=null&&destinyCityCB.getValue()!=null) {
            if (kindOfGraphCB.getValue().equals("Graph with adjacency list")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setContentText(Controller.getInstance().bestWayList(city1, city2, election));
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setContentText(Controller.getInstance().bestWayMatriz(city1, city2, election));
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Caution");
            alert.setContentText("Missing fields to fill");
            alert.showAndWait();
        }
    }

    @FXML
    void goToMenu(ActionEvent event) {
        HelloApplication.openWindow("hello-view.fxml"); //Esta me abre el otro fxml, junto con su controlador.
        Stage stage = (Stage) goToMenuBTN.getScene().getWindow(); //Literalmente, me cierro a mi mismo.
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Options kind of graph
        kindOfGraphCB.getItems().add("Graph with adjacency list");
        kindOfGraphCB.getItems().add("Graph with adjacency matriz");

        //Options type of traveling
        typeOfTravelingCB.getItems().add("cost");
        typeOfTravelingCB.getItems().add("distance");

        //Options of cities
        for (int i = 0; i <cities.size() ; i++) {
            originCityCB.getItems().add(cities.get(i).getData());
            destinyCityCB.getItems().add(cities.get(i).getData());
        }
    }
}
