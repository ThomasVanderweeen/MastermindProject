package gui;


import exceptions.NietGenoegGewonnenException;
import java.net.URL;
import java.util.List;
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

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * verantwoordelijk voor het reguleren van het menuScherm
 * @author Groep 77
 */
public class MenuSchermController implements Initializable{
    private ResourceBundle r;
    

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

    
    /**
     * update de resourcebundle in WelkomController en de labels
     * @author Ferre
     * @param taal 
     * @see WelkomController#setResourceBundle(java.lang.String) SetResourceBundle
     */
    private void updateResourceBundle(String taal){
        WelkomController.setResourceBundle(taal);
        updateLabels();
    }
    
    /**
     * update de labels volgens de resourceBundle in de welkomController
     * @author Ferre
     * 
     */
    private void updateLabels(){
        startSpel.setText(WelkomController.r.getString("startSpel"));
        laadSpel.setText(WelkomController.r.getString("spelLaden"));
        daagUit.setText(WelkomController.r.getString("daagUit"));
        bekijkUitdagingen.setText(WelkomController.r.getString("uitgedaagd"));
        bekijkScorebord.setText(WelkomController.r.getString("bekijkScorebord"));
        naam.setText(WelkomController.r.getString("meldAanWelkom")+" "+WelkomController.dc.geefSpelerNaam());
    }
    
    /**
     * roept updateresourcebundle aan met als param engels
     * @see #updateResourceBundle(java.lang.String) updateResourceBundle
     * @author Ferre
     */
    
    @FXML
    protected void engelsGeklikt(){
        updateResourceBundle("engels");
    }
     
    /**
     * roept updateresourcebundle aan met als param Frans
     * @see #updateResourceBundle(java.lang.String) updateResourceBundle
     * @author Ferre
     */
    @FXML
    protected void fransGeklikt(){
        updateResourceBundle("frans");
    }
    
    /**
     * roept updateresourcebundle aan met als param Nederlands
     * @see #updateResourceBundle(java.lang.String) updateResourceBundle
     * @author Ferre
     */
    @FXML
    protected void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    }


    /**
     * gaat de moeilijkheidsgraad opvragen, scherm veranderen in spelbord,
     * nieuw spel starten, gui opbouwen en moeilijkheidsgraad instellen.
     * ook verantwoordelijk voor het afhandelen van niet genoeg gewonnen exception
     * @see #vraagMoeilijkheidsGraad() vraagMoeilijkheidsGraad
     * @see WelkomController#veranderScherm(java.lang.String) veranderScherm
     * @see SpelbordController#startNieuwSpel() startNieuwSpel
     * @see SpelbordController#buildGui() buildGui
     * @see SpelbordController#setMoeilijkheidsGraad(int) setMoeilijkheidsGraad
     * @author Michiel S.
     */
     @FXML
    protected void startGeklikt(){
        int mg = vraagMoeilijkheidsGraad();
        if(mg!=0){
            try{
                
                WelkomController.veranderScherm("Spelbord.fxml");
                SpelbordController sc = (SpelbordController) WelkomController.geefController();
                sc.setMoeilijkheidsGraad(mg);
                sc.startNieuwSpel();
                sc.buildGui();

            }catch(NietGenoegGewonnenException ngge){
                WelkomController.Error("Niet genoeg gewonnen", 
                        "Om een spel uit deze moeilijkheids graad te spelen moet je minstens twintig spelletjes spelen uit de vorige"
                         +" moeilijkheidsgraad ");
                WelkomController.veranderScherm("Menu.fxml");
            }
            

        }
    }
    
     /**
     * initialiseerd SpelLadenController, stelt deze in als controller klasse
     * en roept de methode toonSpellen aan van deze controller
     * @see WelkomController#setController(java.lang.Object) setController
     * @see SpelLadenController#toonSpellen() toonSpellen
     * @author Michiel S
     */
    @FXML
    protected void laadGeklikt(){

        SpelLadenController slc = new SpelLadenController();
        WelkomController.setController(slc);
        slc.toonSpellen();
    }
    
    /**
     * initialiseerd UitdagingStartencontroller, stelt deze in als controller klasse
     * en roept de methode selecteerMoeilijkheidsgraad aan van deze controller
     * @see WelkomController#setController(java.lang.Object) setController
     * @see UitdagingStartenController#selecteerMoeilijkheidsgraad() selecteerMoeilijkheidsgraad
     * @author Michiel S
     */
    @FXML
    protected void daagUitGeklikt(){

        UitdagingStartenController usc = new UitdagingStartenController();
        WelkomController.setController(usc);
        usc.selecteerMoeilijkheidsgraad();
    }
        
    /**
     * initialiseerd Uitdagingencontroller, stelt deze in als controller klasse
     * en roept de methode toonUitdagingen aan van deze controller
     * @see WelkomController#setController(java.lang.Object) setController
     * @see UitdagingenController#toonUitdagingen() toonUitdagingen
     * @author Michiel S
     */
    @FXML
    protected void bekijkUitdagingenGeklikt(){
       UitdagingenController uc = new UitdagingenController();
       WelkomController.setController(uc);
       uc.toonUitdagingen();
    }
        
    /**
     * initialiseerd ScorebordController, stelt deze in als controller klasse
     * en roept de methode toonMoeilijkheidsgraad aan van deze controller
     * @see WelkomController#setController(java.lang.Object) setController
     * @see ScorebordController#geefKeuze()  geefKeuze
     * @author Ferre
     */
    @FXML
    protected void bekijkScorebordGeklikt(){
        ScorebordController sc = new ScorebordController();
        WelkomController.setController(sc);
        sc.geefKeuze();
    }
    
    /**
     * vraagt de moeilijkheidsgraad aan de gebruiker door middel van een alert
     * maakt hiervoor gebruikt van de methode startNieuwSpel in de domeinklasse 
     * @see domein.DomeinController#startNieuwSpel() startNieuwSpel
     * @return integer 
     * @author Michiel S.
     */  
    private int vraagMoeilijkheidsGraad(){
        Alert mg = new Alert(AlertType.CONFIRMATION);
        List<Integer> gewin = WelkomController.dc.startNieuwSpel();
        
        GridPane gp = new GridPane();
        
        Label gewonnen = new Label("Het aantal potjes gewonnen:");
        Label makkelijk = new Label("Makkelijk: "+gewin.get(0));
        Label lblgemiddeld = new Label("Gemiddeld: "+gewin.get(1));
        Label lblmoeilijk = new Label("Moeilijk: "+gewin.get(2));
        
        gp.addRow(0, gewonnen);
        gp.addRow(1, makkelijk );
        gp.addRow(2,lblgemiddeld );
        gp.addRow(3, lblmoeilijk);

        mg.getDialogPane().setHeader(gp);
        
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
    /**
     * Initialiseerd de controller klasse en update de naam die wordt weergegeven
     * door middel van de functie geefSpelerNaam in de domeinController
     * @param location URL
     * @see domein.DomeinController#geefSpelerNaam() geefSpelerNaam
     * @param resources ResourceBundle
     * @author Thomas
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        naam.setText(WelkomController.r.getString("meldAanWelkom")+" "+WelkomController.dc.geefSpelerNaam());
    }



    
}

