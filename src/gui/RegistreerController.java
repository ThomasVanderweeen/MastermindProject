package gui;

import exceptions.SpelerBestaatAlException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * verantwoordelijk voor het reguleren van alles binnen het registreer scherm
 * @author Groep 77
 */
public class RegistreerController{
   
    private ResourceBundle r;
    
    @FXML
    private Label registreerLabel;

    @FXML
    private Label gebruikersnaamLabel;

    @FXML
    private TextField registreerNaam;

    @FXML
    private Label wachtwoordLabel;

    @FXML
    private PasswordField registreerWachtwoord;

    @FXML
    private Tooltip toolTipWw;

    @FXML
    private Label wachtwoordBevestigenLabel;

    @FXML
    private PasswordField registreerWachtwoordBevestigen;

    @FXML
    private Tooltip toolTipWwHerhalen;

    @FXML
    private Button registreerKnop;

    @FXML
    private Button engels;

    @FXML
    private Button frans;
    
    @FXML
    private Label foutmelding;
    @FXML
    private Button nederlands;
    @FXML
    private Button terugKnop;

    /**
     * registreerd de speler d.m.v de registreer methode in de domeinController
     * handelt ook de eventuele exceptions af die kunnen worden gethrowed in het domein
     * @author Michiel S.
     * @see domein.DomeinController#registreer(java.lang.String, java.lang.String, java.lang.String) registreer
     */
    @FXML
    protected void registreer(){
        try{
        WelkomController.dc.registreer(
                registreerNaam.getText().trim(),
                registreerWachtwoord.getText().trim(),
                registreerWachtwoordBevestigen.getText().trim()
                );

        WelkomController.veranderScherm("LogIn.fxml");
        }
        catch(IllegalArgumentException e){
            if(!registreerWachtwoord.getText().trim().equals(registreerWachtwoordBevestigen.getText().trim())){
//                foutmelding.setText("Ga na of je wachtwoord en bevestiging overeenkomen");
                exception("exceptionWWnietGelijkTitel","exceptionWWnietGelijkHeader",
                       "exceptionWWnietGelijkContext");
            }
            else{
//                foutmelding.setText("Je wachtwoord moet beginnen met een cijfer gevolgd door zes letters en eindigen op een cijfer");
                exception("exceptionWWFoutFormatTitel","exceptionWWFoutFormatHeader",
                        "exceptionWWFoutFormatContext");
            }
            
        }catch(SpelerBestaatAlException sbae){
            exception("exceptionSpelerBestaatTitel","exceptionSpelerBestaatHeader",
                    "exceptionSpelerBestaatContext");
        }
            


    }
    
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
        gebruikersnaamLabel.setText(WelkomController.r.getString("gebruikersNaam"));
        wachtwoordLabel.setText(WelkomController.r.getString("wachtwoord"));
        registreerLabel.setText(WelkomController.r.getString("registreer"));
        wachtwoordBevestigenLabel.setText(WelkomController.r.getString("wachtwoordBevestigen"));
        registreerKnop.setText(WelkomController.r.getString("registreer"));
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
     * Gooit een exception met de bijgevoegde parameters als titel, header en context
     * @param title
     * @param header
     * @param context 
     * @author Michiel S.
     */
    private void exception(String title,String header,String context){
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle(r.getString(title));
        alert.setHeaderText(r.getString(header));
        alert.setContentText(r.getString(context));
                
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.show();
        
        registreerNaam.clear();
        registreerWachtwoord.clear();
        registreerWachtwoordBevestigen.clear();
    }

    @FXML
    private void terugKnopGeklikt() {
        WelkomController.veranderScherm("Welkom.fxml");
    }

}
