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
import exceptions.AanmeldException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class RegistreerController{
   
    private ResourceBundle r;
    private final ScreenController sc = new ScreenController();
    private final LogInScherm ls = new LogInScherm();
    
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

    public void registreer(){
        try{
        WelkomController.dc.registreer(
                registreerNaam.getText().trim(),
                registreerWachtwoord.getText().trim(),
                registreerWachtwoordBevestigen.getText().trim()
                );
        }
        catch(IllegalArgumentException e){
            if(!registreerWachtwoord.getText().trim().equals(registreerWachtwoordBevestigen.getText().trim())){
//                foutmelding.setText("Ga na of je wachtwoord en bevestiging overeenkomen");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Wachtwoord en bevestiging niet gelijk");
                alert.setHeaderText("Kan speler niet toevoegen");
                alert.setContentText("Ga na of je wachtwoord en bevestiging overeenkomen");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.setAlwaysOnTop(true);
                stage.toFront();
                stage.show();
                return;
            }
            else{
//                foutmelding.setText("Je wachtwoord moet beginnen met een cijfer gevolgd door zes letters en eindigen op een cijfer");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Wachtwoord formatting fout");
                alert.setHeaderText("Kan speler niet toevoegen");
                alert.setContentText("Je wachtwoord moet beginnen met een cijfer gevolgd door zes letters en eindigen op een cijfer");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.setAlwaysOnTop(true);
                stage.toFront();
                stage.show();
                return;
            }
            
        }
        registreerNaam.clear();
        registreerWachtwoord.clear();
        registreerWachtwoordBevestigen.clear();
        LogInController lc = new LogInController();
        lc.setControllers(r);
        Parent pr = RegistreerController.this.ls.changeScreenToLogIn();
        RegistreerController.this.sc.changeScene(pr);
    }

}
