package gui;

import exceptions.AanmeldException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Groep 77
 */
public class LogInController implements Initializable
{

    private ResourceBundle r;
    

    @FXML
    private Label logInLabel;
    @FXML
    private Label gebruikersnaamLabel;
    @FXML
    private TextField logInNaam;
    @FXML
    private Label wachtwoordLabel;
    @FXML
    private PasswordField logInWachtwoord;
    @FXML
    private Button logInKnop;

    /**
     * Initialiseerd de controller klasse en update de labels volgens de resourcebundle
     * in de welkomController klasse
     * @param url URL
     * @param rb resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        updateLabels();
    }    
    
    /**
     * Update de resourcebundle in de welkomController klasse volgens de parameter
     * en updated de labels
     * @see updateLabels
     * @param taal 
     * @author Ferre
     */
    private void updateResourceBundle(String taal){
        WelkomController.setResourceBundle(taal);
        updateLabels();
    }
    
    /**
     * Update de labels volgens de resourceBundle in de welkomController
     * @author Ferre
     */
    
    private void updateLabels(){
        gebruikersnaamLabel.setText(WelkomController.r.getString("gebruikersNaam"));
        wachtwoordLabel.setText(WelkomController.r.getString("wachtwoord"));
        logInKnop.setText(WelkomController.r.getString("meldAan"));
        logInLabel.setText(WelkomController.r.getString("meldAan"));
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
     * roept updateresourcebundle aan met als param Nederlands
     * @see updateResourceBundle
     * @author Ferre
     */
    @FXML
    protected void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    }
    
    /**
     * Is verantwoordelijk voor het inloggen en afhandelen van de AanmeldExceptie
     * roept ook het menu aan
     * @see openMenu
     * @author Michiel S.
     */
    @FXML
    protected void logIn(){
        try{
        WelkomController.dc.meldAan(logInNaam.getText(),
                logInWachtwoord.getText());
        }
        catch(AanmeldException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Combinatie wachtwoord en speler");
            alert.setHeaderText("Kan speler niet inloggen");
            alert.setContentText("combinatie speler en wachtwoord niet gekend in het systeem");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true);
            stage.toFront();
            stage.show();
            return;
        }
        logInNaam.clear();
        logInWachtwoord.clear();
        openMenu();
    }
    
    /**
     * opent het menu doormiddel van de WelkomController
     * @see veranderScherm
     * @author Michiel S.
     */
    protected void openMenu(){
        WelkomController.veranderScherm("Menu.fxml");
    }

}
