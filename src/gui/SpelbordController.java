package gui;

import exceptions.NietGenoegGewonnenException;
import exceptions.ServerOnbereikbaarException;
import exceptions.SpelNaamNietUniekException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Verantwoordelijk voor het verloop van Spelbord en het spelen ervan
 * @author Ferre
 */
public class SpelbordController implements Initializable
{
    private ResourceBundle r;
    private int moeilijkheidsGraad=1;
    
    @FXML
    private Button engels;
    @FXML
    private Button frans;
    @FXML
    private Button nederlands;

    @FXML
    private ComboBox keuze0;
    @FXML
    private ComboBox keuze1;
    @FXML
    private ComboBox keuze2;
    @FXML
    private ComboBox keuze3;
    
    private ComboBox keuze4;
    
    @FXML
    private Button opslaanKnop;
    
    @FXML
    private GridPane spelbord;
    @FXML
    private GridPane evaluatie;
    
    private final String[] kleuren = WelkomController.dc.geefKleuren();
    @FXML
    private AnchorPane window;
    @FXML
    private Button doePoging;
    
    /**
     * Initialiseerd de controller klasse  en update de labels
     * @param url URL
     * @param rb ResourceBundle
     * @see updateLabels
     * @author Thomas
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        updateLabels();
    }    
    
    /**
     * update de resourcebundle in WelkomController en de labels
     * @author Ferre
     * @param taal 
     * @see SetResourceBundle
     */
    private void updateResourceBundle(String taal){
        WelkomController.setResourceBundle(taal);
        updateLabels();
    }
    
    /**
     * Update de labels volgens de resourceBundle in de WelkomController
     * @author Ferre
     */
    private void updateLabels(){
        opslaanKnop.setText(WelkomController.r.getString("slaOp"));
        doePoging.setText(WelkomController.r.getString("wachtwoord"));
        
    }
    
    
    /**
     * roept updateresourcebundle aan met als param engels
     * @see updateResourceBundle
     * @author Ferre
     */
    @FXML
    protected void engelsGeklikt(){
        updateResourceBundle("engels");
    }
    
    /**
     * roept updateresourcebundle aan met als param Frans
     * @see updateResourceBundle
     * @author Ferre
     */
    @FXML
    protected void fransGeklikt(){
        updateResourceBundle("frans");
    }
    
    /**
     * roept updateresourcebundle aan met als param nederlands
     * @see updateResourceBundle
     * @author Ferre
     */
    @FXML
    protected void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    } 
    
    /**
     * geeft de combo boxen weer die als input zullen dienen
     * in het geval van moeilijk 5 anders 4.
     * @author Thomas
     * @return ComboBox[] 
     */
    private ComboBox[] getKeuzeBoxen(){
        ComboBox[] cb = {keuze0,keuze1,keuze2,keuze3};
        
        if(this.moeilijkheidsGraad==3){
            ComboBox[] cba =  {keuze0,keuze1,keuze2,keuze3,keuze4};
            return cba;
        }
        
        return cb;
    }
    
    /**
     * start een nieuw spel op via de domeinController.
     * @see registreerSpel
     * @throws NietGenoegGewonnenException
     * @author Ferre
     */
    protected void startNieuwSpel(){
         try{
            WelkomController.dc.registreerSpel(this.moeilijkheidsGraad);
        }catch(NietGenoegGewonnenException ngge){
            throw ngge;
        }
    }
    
    /**
     * gaat het spelbord opbouwen, oftewel leeg oftewel al reeds opgevuld (via de methode
     * setSpelbord).
     * @see getKeuzeBoxen
     * @see setSpelbord
     * @author Ferre
     */
    protected void buildGui(){

        
        ObservableList<String> keuzes = FXCollections.observableList(Arrays.asList(this.kleuren));
        
        if(this.moeilijkheidsGraad==3)
            voorzieMoeilijk();
        
        for(ComboBox keuze:getKeuzeBoxen()){
            keuze.setItems(keuzes);
            keuze.setValue(keuzes.get(0));
        }
        
        this.setSpelbord();
    }
    
    /**
     * Doet een poging via de domeinController en update het spelbord
     * gaat ook controlleren op winst en verlies.
     * @see getKeuzeBoxen
     * @see doePoging
     * @see isEindeSpel
     * @see isGewonnen
     * @author Ferre
     */
    @FXML
    protected void doePoging(){
        String[] kleuren =  WelkomController.dc.geefKleuren();
        int[] poging = new int[4];
        
        if(this.moeilijkheidsGraad==3)
            poging = new int[5];
        
        int i =0;
        
        for(ComboBox CB: this.getKeuzeBoxen()){
            poging[i] = Arrays.asList(this.kleuren).indexOf(CB.getValue());
            i++;
        }
        
        WelkomController.dc.doePoging(poging);
        setSpelbord();
        
        if(WelkomController.dc.isEindeSpel()){
            if(WelkomController.dc.isGewonnen())
                gewonnen();
            else
                verloren();
        }
    }
    
    /**
     * setSpelbord gaat de gridpanes opvullen met de reeds gedaane pogingen en
     * evaluatie de gegevens worden hiervoor opgevraagd via de domeinController
     * @see geefSpelBord
     * @see getKeuzeBoxen
     * @author Thomas
     */
    private void setSpelbord(){
        String[][] spelbord= WelkomController.dc.geefSpelBord();
        int rijindxEva=11,rijindxSpelbord=11,kolom=0,lengteSpelbord = this.getKeuzeBoxen().length;
        
        for(String[] rij:spelbord){
            for(String kleur:rij){
                ImageView Kleur = new ImageView(new Image(getClass().getResourceAsStream("/gui/images/"+kleur+".png")));
                Kleur.setFitHeight(20.5);
                Kleur.setFitWidth(20.5);
                
                if(rij.length>lengteSpelbord){
                    if(kolom>=lengteSpelbord)
                        this.evaluatie.add(Kleur, kolom%lengteSpelbord, rijindxEva);
                    else
                        if(!kleur.equals("leeg"))
                        this.spelbord.add(Kleur, kolom, rijindxSpelbord);
                }
                
                kolom++;
            }
            
            rijindxSpelbord--;
            if(rij.length>lengteSpelbord)
                rijindxEva--;
            kolom=0;
        }
    }
    
    /**
     * creert een alert met daarin de code en de boodschap dat je verloren bent
     * @see geefCode
     * @author Ferre
     */
    private void verloren(){
        Alert gewonnen = new Alert(AlertType.WARNING);
        gewonnen.setTitle("Verloren...");
        gewonnen.setContentText("Sorry je hebt de code niet kunnen raden.De code was: "
                +String.join(" ", WelkomController.dc.geefCode()));
        gewonnen.setHeaderText("Verloren...");
        
         Stage stg = (Stage) gewonnen.getDialogPane().getScene().getWindow();
         stg.setAlwaysOnTop(true);
         stg.toFront();

         gewonnen.showAndWait();
         toonMenu();
    }
    
    /**
     * laat het menu in via de welkomController
     * @see veranderScherm
     * @author Thomas
     */
    private void toonMenu(){
        WelkomController.veranderScherm("Menu.fxml");
    }
    
    /**
     * Voegt een extra combobox en evaluatie label toe indien de moeilijkheidsgraad moeilijk is
     * @author Thomas
     */
    private void voorzieMoeilijk(){
        ComboBox keuze = new ComboBox();
        this.spelbord.add(keuze, 4, 12);
        
        Label l = new Label(" ");
        l.setPrefWidth(100);
        this.evaluatie.add(l, 4, 12);
        
        this.keuze4 =keuze;
    }
    
    /**
     * stelt de moeilijkheidsgraad in
     * @param mg 
     * @author Ferre
     */
    protected void setMoeilijkheidsGraad(int mg){
        this.moeilijkheidsGraad = mg;
        
    }
    
    /**
     * Vraagt de naam op en indien deze niet leeg is roept het slaOp aan
     * @see geefNaam
     * @see slaOp
     * @author Ferre
     */
    @FXML
    protected void opslaan(){
        String naam = geefNaam();
        if(naam!=null)
            slaOp(naam);
    }
    
    /**
     * creert een alert met daarin de code en de boodschap dat je gewonnen bent
     * geef eveneens weer hoeveel sterren dat je hebt en hoeveel spelletjes nodig tot 
     * volgende ster
     * @see geefEindSituatie
     * @see geefCode
     * @author Thomas
     */
    
    private void gewonnen(){
        String[][] eindSituatie = WelkomController.dc.geefEindSituatie();
        
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setTitle("Aantal Sterren");
        
        al.setHeaderText(String.format("%s%n%s%s%n%s%s%s","Gefeliciteerd je bent gewonnen!","De code was: "
                ,String.join(" ", WelkomController.dc.geefCode()),"Je had ",
                eindSituatie[1][0]," pogingen nodig"));
        
        DialogPane dp = al.getDialogPane();
        GridPane gp = new GridPane();
        VBox vb = new VBox();
        
        for(int x=0;x<5;x++){
            ImageView iv = new ImageView();
           
            if(x<Integer.valueOf(eindSituatie[0][0]))
                iv.setImage(new Image(getClass().getResourceAsStream("/gui/images/goldStar.png")));
            else
                iv.setImage(new Image(getClass().getResourceAsStream("/gui/images/greyStar.png")));
            
            iv.setFitHeight(50.0);
            iv.setFitWidth(50.0);

            gp.add(iv, x, 0);
        }
        
        Label lb = new Label(String.format("%s%s%s","je moet nog ",eindSituatie[0][1],
                " spelletjes winnen voor de volgende ster"));
        
        vb.getChildren().addAll(gp,lb);
        dp.setContent(vb);
        
        Stage stg = (Stage)al.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        
        al.showAndWait();
        toonMenu();
    }
    
    /**
     * Vraagt een naam op en geeft deze weer.
     * @return String
     * @author Thomas
     */
    private String geefNaam(){
        TextInputDialog geefNaam = new TextInputDialog();
        geefNaam.setTitle("Sla spel op");
        geefNaam.setHeaderText("geef De naam van het spel:");
        
        Stage stg = (Stage) geefNaam.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        stg.getIcons().add(new Image("/gui/images/pika.png"));
                    
        
        Optional<String> naam = geefNaam.showAndWait();
        if(naam.isPresent()){
            return naam.get();
        }
        
        return null;
    }
    
    /**
     * slaat het spel op via de domeinController geeft een alert terug indien succesvol en roep toonMenu op
     * handelt ook SpelNaamNietUniekException af
     * @param naam 
     * @see slaOp
     * @see toonMenu
     * @author Thomas
     */
    private void slaOp(String naam){
        try{
            WelkomController.dc.slaOp(naam);
            
            Alert gelukt = new Alert(AlertType.CONFIRMATION);
            gelukt.setTitle("Spel opgeslagen");
            gelukt.setContentText("Het spel is correct opgeslagen in de database onder de naam: \""+naam+"\"");
            
            Stage stg = (Stage)gelukt.getDialogPane().getScene().getWindow();
            stg.setAlwaysOnTop(true);
            stg.toFront();
            stg.showAndWait();
            stg.getIcons().add(new Image("/gui/images/pika.png"));
                    
            toonMenu();
        }catch(SpelNaamNietUniekException sn){
            error("Deze spelnaam is al in gebruik. Probeer opnieuw.","spelnaam niet uniek");
        }
    }
    
    /**
     * geeft een error weer met als content de msg param, en als titel de title param
     * @param msg
     * @param title 
     * @author Ferre
     */
    private void error(String msg,String title){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        
        Stage stg = (Stage) alert.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        alert.showAndWait();
    }
}

