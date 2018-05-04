
package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SpelbordController implements Initializable
{
    DomeinController dc;
    ResourceBundle r;
    
    @FXML
    private Button engels;
    @FXML
    private Button frans;
    @FXML
    private ImageView nederlands;

    @FXML
    private ComboBox keuze0;
    @FXML
    private ComboBox keuze1;
    @FXML
    private ComboBox keuze2;
    @FXML
    private ComboBox keuze3;
    
    
    @FXML
    private GridPane spelbord;
    @FXML
    private GridPane evaluatie;
    
    private final String[] kleuren = WelkomController.dc.geefKleuren();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildGui();
    }    
    
    private ComboBox[] getKeuzeBoxen(){
        ComboBox[] cb = {keuze0,keuze1,keuze2,keuze3};
        return cb;
    }
    
    private void buildGui(){
        WelkomController.dc.registreerSpel(1);
        ObservableList<String> keuzes = FXCollections.observableList(Arrays.asList(this.kleuren));
        
        for(ComboBox keuze:getKeuzeBoxen()){
            keuze.setItems(keuzes);
            keuze.setValue(keuzes.get(0));
        }
    }
    
    public void doePoging(){
        String[] kleuren =  WelkomController.dc.geefKleuren();
        int[] poging = new int[4];
        int i =0;
        
        for(ComboBox CB: this.getKeuzeBoxen()){
            poging[i] = Arrays.asList(this.kleuren).indexOf(CB.getValue());
            i++;
        }
        
        WelkomController.dc.doePoging(poging);
        setSpelbord();
        
        if(WelkomController.dc.isGewonnen())
            gewonnen();
    }
    
    private void setSpelbord(){
        String[][] spelbord= WelkomController.dc.geefSpelBord();
        int rijindxEva=11,rijindxSpelbord=11,kolom=0;
        
        for(String[] rij:spelbord){
            for(String kleur:rij){
                ImageView Kleur = new ImageView(new Image(getClass().getResourceAsStream("/gui/images/"+kleur+".png")));
                Kleur.setFitHeight(20.5);
                Kleur.setFitWidth(20.5);
                
                if(rij.length>4){
                    if(kolom>=4)
                        this.evaluatie.add(Kleur, kolom%4, rijindxEva);
                    else
                        if(kleur!="leeg")
                        this.spelbord.add(Kleur, kolom, rijindxSpelbord);
                }
                
                kolom++;
            }
            
            rijindxSpelbord--;
            if(rij.length>4)
                rijindxEva--;
            kolom=0;
        }
    }
    
    
    private void gewonnen(){
        Alert gewonnen = new Alert(AlertType.INFORMATION);
        gewonnen.setTitle("Gefeliciteerd!");
        gewonnen.setContentText("Gefeliciteerd je bent gewonnen! De code was: "
                +String.join(" ", WelkomController.dc.geefCode()));
        gewonnen.setHeaderText("Gewonnen!");
        
         Stage stg = (Stage) gewonnen.getDialogPane().getScene().getWindow();
         stg.setAlwaysOnTop(true);
         stg.toFront();

         gewonnen.showAndWait();
         toonMenu();
    }
    
    private void toonMenu(){
        MenuScherm ms = new MenuScherm();
        Parent pr = ms.maakParent();
        WelkomController.sc.changeScene(pr);
    }
}
