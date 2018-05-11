/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import exceptions.AanmeldException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author iamja
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        updateLabels();
    }    
    
    
    private void updateResourceBundle(String taal){
        WelkomController.setResourceBundle(taal);
        updateLabels();
    }
    
    private void updateLabels(){
        gebruikersnaamLabel.setText(WelkomController.r.getString("gebruikersNaam"));
        wachtwoordLabel.setText(WelkomController.r.getString("wachtwoord"));
        logInKnop.setText(WelkomController.r.getString("meldAan"));
        logInLabel.setText(WelkomController.r.getString("meldAan"));
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
    
    @FXML
    public void logIn(){
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
    
    public void openMenu(){
        WelkomController.veranderScherm("Menu.fxml");
    }

}
