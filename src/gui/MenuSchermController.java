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
import exceptions.NietGenoegGewonnenException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MenuSchermController implements Initializable{

    private DomeinController dc;
    private ResourceBundle r;
    
    @FXML
    private Button engels;

    @FXML
    private Button frans;

    @FXML
    private Button nederlands;

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
    @FXML
    private Button terugKnop;
    
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
        startSpel.setText(this.r.getString("startSpel"));
        laadSpel.setText(this.r.getString("spelLaden"));
        daagUit.setText(this.r.getString("daagUit"));
        bekijkUitdagingen.setText(this.r.getString("uitgedaagd"));
        bekijkScorebord.setText(this.r.getString("bekijkScorebord"));
        
    }
    
    
    
    @FXML
    public void engelsGeklikt(){
        updateResourceBundle("engels");
    }
    
    @FXML
    public void fransGeklikt(){
        updateResourceBundle("frans");
    }
    
    @FXML
    public void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    }

//    @FXML
//    private void terugKnopGeklikt() {
//        WelkomController wc = (WelkomController)ws.geefController();
//        wc.setWelkomScherm(ws);
//    }
    
    @FXML
    public void startGeklikt(){
        int mg = vraagMoeilijkheidsGraad();
        if(mg!=0){
            try{
                WelkomController.veranderScherm("Spelbord.fxml");
                SpelbordController sc = (SpelbordController) WelkomController.geefController();
                sc.setMoeilijkheidsGraad(mg);
                sc.startNieuwSpel();
                sc.buildGui();

            }catch(NietGenoegGewonnenException ngge){
                WelkomController.Error("Niet genoeg gewonnen", "Je hebt nog niet genoeg spelletjes gewonnen",
                        "Om een spel uit deze moeilijkheids graad te spelen moet je minstens twintig spelletjes spelen uit de vorige"
                         +" moeilijkheidsgraad ");
            }
            

        }
    }
    
    @FXML
    public void laadGeklikt(){
        SpelLadenController slc = new SpelLadenController();
        WelkomController.setController(slc);
        slc.toonSpellen();
    }
    
    @FXML
    public void daagUitGeklikt(){
        UitdagingStartenController usc = new UitdagingStartenController();
        WelkomController.setController(usc);
        usc.selecteerMoeilijkheidsgraad();
    }
    
    @FXML
    public void bekijkUitdagingenGeklikt(){
       UitdagingenController uc = new UitdagingenController();
       WelkomController.setController(uc);
       uc.toonUitdagingen();
    }
    
    @FXML
    public void bekijkScorebordGeklikt(){
        ScorebordController sc = new ScorebordController();
        WelkomController.setController(sc);
        sc.toonMoeilijkheidsgraad(1);
    }
    
    private int vraagMoeilijkheidsGraad(){
        Alert mg = new Alert(AlertType.CONFIRMATION);
        
        ButtonType gemakkelijk = new ButtonType("Gemakkelijk");
        ButtonType gemiddeld = new ButtonType("Gemiddeld");
        ButtonType moeilijk = new ButtonType("Moeilijk");

        mg.getButtonTypes().setAll(gemakkelijk,gemiddeld,moeilijk);
        
        Stage sg = (Stage)mg.getDialogPane().getScene().getWindow();
        sg.setAlwaysOnTop(true);
        sg.setOnCloseRequest(evt->{sg.close();});
        sg.getIcons().add(new Image("/gui/images/pika.png"));
        
        Optional<ButtonType> res = mg.showAndWait();
        
        if(res.isPresent()){
            switch(res.get().getText()){
                case "Gemakkelijk":
                    return 1;
                case "Gemiddeld":
                    return 2;
                case "Moeilijk":
                    return 3;
            }
        }
        
        return 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        naam.setText("Welkom terug "+WelkomController.dc.geefSpelerNaam());
    }

    @FXML
    private void terugKnopGeklikt(ActionEvent event) {
    }

    
}

