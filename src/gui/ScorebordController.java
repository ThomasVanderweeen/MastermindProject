/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.GeenOpenstaandeUitdagingException;
import exceptions.HeeftLopendeUitdagingException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScorebordController implements Initializable
{

    private Dialog dg = null;
    private int ID = 0;
    private String naam = "";
    private int mg = 0;
    private SpelbordScherm sb;

    private ResourceBundle r;
    @FXML
    private Button engels;
    @FXML
    private Button frans;
    @FXML
    private Button nederlands;
    @FXML
    private Button makkelijk;
    @FXML
    private Button gemiddeld;
    @FXML
    private Button moeilijk;
    @FXML
    private ListView<String> scorebord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateLabels();
        toonMoeilijkheidsgraad(1);
    }

    public void toonScorebord() {

    }

    public void stelGegevensIn(int ID, String naam, int moeilijkheidsGraad) {

    }

    private void updateResourceBundle(String taal) {
        WelkomController.setResourceBundle(taal);
        updateLabels();
    }

    private void updateLabels() {
        makkelijk.setText(WelkomController.r.getString("makkelijk"));
        gemiddeld.setText(WelkomController.r.getString("gemiddeld"));
        moeilijk.setText(WelkomController.r.getString("moeilijk"));

    }

    @FXML
    public void engelsGeklikt() {
        updateResourceBundle("engels");
    }

    @FXML
    public void fransGeklikt() {
        updateResourceBundle("frans");
    }

    @FXML
    public void nederlandsGeklikt() {
        updateResourceBundle("nederlands");
    }

    @FXML
    private void makkelijkGeklikt(ActionEvent event) {
        toonMoeilijkheidsgraad(1);
    }

    @FXML
    private void gemiddeldGeklikt(ActionEvent event) {
        toonMoeilijkheidsgraad(2);
    }

    @FXML
    private void moeilijkGeklikt(ActionEvent event) {
        toonMoeilijkheidsgraad(3);
    }

    private void toonMoeilijkheidsgraad(int graad) {
        List<String[]> spelers = new ArrayList<String[]>();
        spelers = WelkomController.dc.geefKlassement(graad);
        int i = 1;
        
        scorebord.setCellFactory(TextFieldListCell.forListView());
        scorebord.setEditable(false);
        scorebord.getItems().add(0,"Player  Won");
        for(String[] speler: spelers){
            String naam = speler[0];
            int gewonnen = Integer.parseInt(speler[1]);
            scorebord.getItems().add(i,naam+gewonnen);
            scorebord.edit(i);
            i++;
        }
    }

}
