/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import exceptions.SpelerHeeftGeenOpgeslagenSpellenException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ThomasV
 */
public class SpelLadenController implements Initializable {
    private String naam;
    private int mg;
    private Dialog dg;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void stelGegevensIn(String naam, int mg){
        this.naam = naam;
        this.mg = mg;
    }
    
    public void sluitDialog(){
        Stage stg = (Stage) this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }
    
    public void toonSpellen(){
        try{
            String[][] spellen = WelkomController.dc.geefOpgeslagenSpellen();
            ObservableList<laadSpelRij> lijst = FXCollections.observableArrayList();
            
            for(String[] spel: spellen){
                lijst.add(new laadSpelRij(spel[0],Integer.valueOf(spel[1])));
            }
            
            TableView<laadSpelRij> tv = new TableView<>();
            
            TableColumn<laadSpelRij,String> naam = new TableColumn<>();
            naam.setText("Naam");
            naam.setMinWidth(150);
            naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
            
            TableColumn<laadSpelRij,String> moeilijkheidsGraad= new TableColumn<>();
            moeilijkheidsGraad.setText("MoeilijkheidsGraad");
            moeilijkheidsGraad.setMinWidth(200);
            moeilijkheidsGraad.setCellValueFactory(new PropertyValueFactory<>("mg"));
            
            TableColumn<laadSpelRij,Button> laadRij= new TableColumn<>();
            laadRij.setMinWidth(100);
            laadRij.setSortable(false);
            laadRij.setCellValueFactory(new PropertyValueFactory<>("btn"));
            
            tv.getColumns().addAll(naam,moeilijkheidsGraad,laadRij);
            tv.setItems(lijst);
            
            dg = new Dialog();
            dg.setTitle("Laad een spel menu");
            dg.getDialogPane().setContent(tv);
            
            Stage stg = (Stage) dg.getDialogPane().getScene().getWindow();
            stg.setAlwaysOnTop(true);
            stg.toFront();
            
            dg.show();
        }catch(SpelerHeeftGeenOpgeslagenSpellenException shg){
            WelkomController.Error("Geen opgeslagen spellen", "je hebt geen opgeslagen spellen", "je hebt geen opgeslagen spellen");
        }
    }
    
    public void laadSpel(){
        WelkomController.dc.selecteerSpel(naam);

        WelkomController.veranderScherm("Spelbord.fxml");
        
        SpelbordController sc = (SpelbordController)WelkomController.geefController();
        sc.setMoeilijkheidsGraad(mg);
        sc.buildGui();
    }
    
}
