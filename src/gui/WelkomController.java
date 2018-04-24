/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author iamja
 */
public class WelkomController implements Initializable
{
    private final ScreenController sc = new ScreenController();
    private final LogInScherm ls = new LogInScherm();
    private final RegistreerScherm rs = new RegistreerScherm();
    protected final static DomeinController dc = new DomeinController();
    private ResourceBundle r;
    
    @FXML
    private ImageView nederlands;
    @FXML
    private ImageView frans;
    @FXML
    private ImageView engels;
    @FXML
    private Label welkomLabel;
    @FXML
    private Label terugkerendLabel;
    @FXML
    private Button logInKeuze;
    @FXML
    private Label geenAccountLabel;
    @FXML
    private Button registreerKeuze;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      
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
        welkomLabel.setText(this.r.getString("welkom"));
        terugkerendLabel.setText(this.r.getString("terugKerend"));
        logInKeuze.setText(this.r.getString("meldAan"));
        geenAccountLabel.setText(this.r.getString("registreerlbl"));
        registreerKeuze.setText(this.r.getString("registreer"));
    }
    
    public void registreergeklikt(){
        Parent pr = WelkomController.this.rs.maakParent();
        WelkomController.this.sc.changeScene(pr);
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
    
    public void LoginGeklikt(){
        LogInController lc = new LogInController();
        lc.setControllers(r, sc);
        Parent pr = WelkomController.this.ls.changeScreenToLogIn();
        WelkomController.this.sc.changeScene(pr);
    }
}
