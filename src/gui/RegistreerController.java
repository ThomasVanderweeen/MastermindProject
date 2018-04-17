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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class RegistreerController{
    
    private final DomeinController controller = new DomeinController();
    private ResourceBundle r;
    private final ScreenController sc = new ScreenController();
    private final LogInScherm ls = new LogInScherm();
    private final DomeinController dc = new DomeinController();
    
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
        controller.registreer(
                registreerNaam.getText().trim(),
                registreerWachtwoord.getText().trim(),
                registreerWachtwoordBevestigen.getText().trim()
                );
        }
        catch(Exception e){
            
        }
        registreerNaam.clear();
        registreerWachtwoord.clear();
        registreerWachtwoordBevestigen.clear();
        LogInController lc = new LogInController();
        lc.setControllers(r, dc, sc);
        Parent pr = RegistreerController.this.ls.changeScreenToLogIn();
        RegistreerController.this.sc.changeScene(pr);
    }

}
