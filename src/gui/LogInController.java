/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author iamja
 */
public class LogInController implements Initializable
{

    private DomeinController dc;
    private ResourceBundle r;
    private ScreenController sc;
    
    @FXML
    private Button engels;
    @FXML
    private Button frans;
    @FXML
    private ImageView nederlands;
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
        // TODO
    }    
    
    protected void setControllers(ResourceBundle r,DomeinController dc, ScreenController sc){
        this.r= r;
        this.dc = dc;
        this.sc = sc;
    }
    
    public void logIn(){
        try{
        dc.meldAan(logInNaam.getText().trim(),
                logInWachtwoord.getText().trim());
        }
        catch(Exception e){
            
        }
    }
    
    
}
