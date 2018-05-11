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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ThomasV
 */
public class UitdagingenController implements Initializable
{
    private Dialog dg=null;
    private int ID = 0;
    private String naam = "";
    private int mg =0;

    private ResourceBundle r;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void closeDialog(){
        Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }

    public void toonUitdagingen() {
        try{
            String[][] uitdagingen = WelkomController.dc.geefLijstUitdagingen();
            String[] moeilijkheidsgraden = {WelkomController.r.getString("makkelijk"), WelkomController.r.getString("gemiddeld"),
                WelkomController.r.getString("moeilijk")};
            Label[] naam,moeilijkheidsgraad = new Label[uitdagingen.length];
            
        
            ObservableList<UitdagingRij> lijst = FXCollections.observableArrayList();
            for(String[] uitdaging:uitdagingen){
                lijst.add(new UitdagingRij(Integer.valueOf(uitdaging[0]),
                uitdaging[1],Integer.valueOf(uitdaging[2])));
            }
            
            TableView<UitdagingRij> tv = new TableView<>();
            
            TableColumn<UitdagingRij,String> naamUitdager = new TableColumn<>("Naam");
            naamUitdager.setMinWidth(100);
            naamUitdager.setCellValueFactory(new PropertyValueFactory<>("naam"));
            
            TableColumn<UitdagingRij,String> mg = new TableColumn<>("MoesilijkheidsGraad");
            mg.setMinWidth(150);
            mg.setCellValueFactory(new PropertyValueFactory<>("moeilijkheidsGraad"));
            
            TableColumn<UitdagingRij,Button> accept = new TableColumn<>("");
            accept.setMinWidth(150);
            accept.setCellValueFactory(new PropertyValueFactory<>("accepteer"));
            
            tv.getColumns().addAll(naamUitdager,mg,accept);
            tv.setItems(lijst);
            
            this.dg = new Dialog<>();
            this.dg.getDialogPane().setContent(tv);
            this.dg.setTitle("Accepteer Uitdaging");

            Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
            stg.setAlwaysOnTop(true);
            stg.toFront();
            stg.setOnCloseRequest(evt->stg.close());
            stg.getIcons().add(new Image("/gui/images/pika.png"));
            this.dg.show();
            
        }catch(GeenOpenstaandeUitdagingException goue){
            WelkomController.Error("Geen openstaande Exception", "Sorry je hebt geen openstaande uitdaging", "probeer later opnieuw");
        }catch(HeeftLopendeUitdagingException hlue){
            WelkomController.Error("heeft Lopende uitdaging", "Sorry je reeds een lopendeUitdaging", "Je hebt al reeds een lopende uitdaging.");
        }


    }

    public void kiesUitdaging() {
        BorderPane bp = new BorderPane();
        
        Label lb = new Label();
        lb.setText("Wat wil je doen met deze uitdaging?");
        bp.setCenter(lb);
        
        HBox hb = new HBox();
        
        Button verwijder = new Button();
        verwijder.setText("Verwijder");
        verwijder.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                UitdagingenController.this.closeDialog();
                UitdagingenController.this.verwijderUitdaging();
            } 
        });
        
        Button accepteer = new Button();
        accepteer.setText("Accepteer");
        accepteer.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                UitdagingenController.this.closeDialog();
                UitdagingenController.this.accepteerUitdaging();
            }
            
        });
        
        hb.getChildren().addAll(verwijder,accepteer);
        bp.setBottom(hb);
        
        this.dg.getDialogPane().setContent(bp);
        Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        stg.getIcons().add(new Image("/gui/images/pika.png"));
        
        this.dg.show();
    }
    
    public void stelGegevensIn(int ID,String naam,int moeilijkheidsGraad){
        this.ID = ID;
        this.naam = naam;
        this.mg = moeilijkheidsGraad;
    }
    
    private void verwijderUitdaging(){
        WelkomController.dc.verwijderUitdaging(this.ID);
        
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setTitle("Uitdaging Verwijderd");
        al.setContentText("De uitdaging is verwijderd");
        al.setHeaderText("Uitdaging succesvol verwijderd");
        
        Stage stg = (Stage) al.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        
        al.show();
    }
    
    private void accepteerUitdaging(){
        WelkomController.dc.laadUitdaging(ID, naam);
        WelkomController.veranderScherm("spelbord.fxml");
        
        SpelbordController sc = (SpelbordController) WelkomController.geefController();
        sc.setMoeilijkheidsGraad(mg);
        sc.buildGui();
        
    }


}
