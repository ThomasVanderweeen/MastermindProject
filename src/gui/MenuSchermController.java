/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ThomasV
 */
import domein.DomeinController;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuSchermController implements Initializable{

    private final ScorebordScherm sb = new ScorebordScherm();
    //private final UitdagingenScherm us = new UitdagingenScherm();
    private final SpelLadenScherm ss = new SpelLadenScherm();
    private final SpelbordScherm sbs = new SpelbordScherm();
    private DomeinController dc;
    private ResourceBundle r;
    
    @FXML
    private Button engels;

    @FXML
    private Button frans;

    @FXML
    private ImageView nederlands;

    @FXML
    private Label logInLabel;

    @FXML
    private Button startSpel;

    @FXML
    private Button laadSpel;

    @FXML
    private Button daagUit;

    @FXML
    private Button bekijkUitdagingen;

    @FXML
    private Button bekijkScorebord;
    
    @FXML
    private Label naam;
    
    protected void setDomeinController(DomeinController dc){
        this.dc = dc;
    } 
    private void updateResourceBundle(String taal){
        
        switch(taal){
            case "frans":
                this.r = ResourceBundle.getBundle("resources/Français_fr");
                break;
            case "nederlands":
                this.r = ResourceBundle.getBundle("resources/Nederlands_ne");
                break;
            case "engels":
                this.r = ResourceBundle.getBundle("resources/English_en");
                break;
            default:
                System.err.println("foute keuze");
                break;
        }
        
        updateLabels();
    }
    
    private void updateLabels(){
        startSpel.setText(this.r.getString("startSpelGUI"));
        laadSpel.setText(this.r.getString("laadSpelGUI"));
        daagUit.setText(this.r.getString("daagUitGUI"));
        bekijkUitdagingen.setText(this.r.getString("bekijkUitdagingenGUI"));
        bekijkScorebord.setText(this.r.getString("bekijkScorebordGUI"));
        
    }
    
    
    
    public void engelsGeklikt(){
        updateResourceBundle("engels");
    }
    
    public void fransGeklikt(){
        updateResourceBundle("frans");
    }
    
    public void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    }

    public void startGeklikt(){
        int mg = vraagMoeilijkheidsGraad();
        
        if(mg!=0){
            Parent pr = MenuSchermController.this.sbs.maakParent();
            this.sbs.geefController().setMoeilijkheidsGraad(mg);
            WelkomController.sc.changeScene(pr);
        }
    }
    
    public void laadGeklikt(){
        Parent pr = MenuSchermController.this.ss.maakParent();
        WelkomController.sc.changeScene(pr);
    }
    
    public void daagUitGeklikt(){
       // Parent pr = MenuSchermController.this.us.maakParent();
        //WelkomController.sc.changeScene(pr);
    }
    
    public void bekijkUitdagingenGeklikt(){
       // Parent pr = MenuSchermController.this.us.maakParent();
        //WelkomController.sc.changeScene(pr);
    }
    
    public void bekijkScorebordGeklikt(){
        Parent pr = MenuSchermController.this.sb.maakParent();
        WelkomController.sc.changeScene(pr);
    }
    
    private int vraagMoeilijkheidsGraad(){
        Alert mg = new Alert(AlertType.CONFIRMATION);
        
        ButtonType gemakkelijk = new ButtonType("Gemakkelijk");
        ButtonType gemiddeld = new ButtonType("Gemiddeld");
        ButtonType moeilijk = new ButtonType("Moeilijk");
        ButtonType cancel = new ButtonType("Anuleer");
        
        mg.getButtonTypes().setAll(gemakkelijk,gemiddeld,moeilijk,cancel);
        
        Stage sg = (Stage)mg.getDialogPane().getScene().getWindow();
        sg.setAlwaysOnTop(true);
        
        Optional<ButtonType> res = mg.showAndWait();
        
        switch(res.get().getText()){
            case "Gemakkelijk":
                return 1;
            case "Gemiddeld":
                return 2;
            case "Moeilijk":
                return 3;
            case "Anuleer":
                return 0;
        }
        
        return 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        naam.setText("Welkom terug "+WelkomController.dc.geefSpelerNaam());
    }
}

