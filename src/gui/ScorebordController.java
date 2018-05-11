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
        toonMoeilijkheidsgraad(1);
    }

    public void toonScorebord() {

    }



    public void toonMoeilijkheidsgraad(int graad) {
        List<String[]> spelers = new ArrayList<String[]>();
        spelers = WelkomController.dc.geefKlassement(graad);
        dg = new Dialog();
        dg.setTitle("MoeilijkheidsGraad");
        
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
        
        dg.getDialogPane().setContent(scorebord);
        Stage sg = (Stage) dg.getDialogPane().getScene().getWindow();
        sg.setAlwaysOnTop(true);
        sg.toFront();
        
        dg.show();
    }

}
