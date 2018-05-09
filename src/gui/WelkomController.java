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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author iamja
 */
public class WelkomController implements Initializable
{
    protected final static ScreenController sc = new ScreenController();
    private final LogInScherm ls = new LogInScherm();
    private final RegistreerScherm rs = new RegistreerScherm();
    protected final static DomeinController dc = new DomeinController();
    protected static ResourceBundle r;
    
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
        updateResourceBundle("nederlands");
    }    
    
    private void updateResourceBundle(String taal){
        this.setResourceBundle(taal);
        updateLabels();
    }
    
    private void updateLabels(){
        welkomLabel.setText(r.getString("welkom"));
        terugkerendLabel.setText(r.getString("terugKerend"));
        logInKeuze.setText(r.getString("meldAan"));
        geenAccountLabel.setText(r.getString("registreerlbl"));
        registreerKeuze.setText(r.getString("registreer"));
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
        Parent pr = ls.changeScreenToLogIn();
        WelkomController.sc.changeScene(pr);
    }
    
    protected static void setResourceBundle(String taal){
        switch(taal){
            case "frans":
                r = ResourceBundle.getBundle("resources/Français_fr");
                break;
            case "nederlands":
                r = ResourceBundle.getBundle("resources/Nederlands_ne");
                break;
            case "engels":
                r = ResourceBundle.getBundle("resources/English_en");
                break;
            default:
                System.err.println("foute keuze");
                break;
        }
    }
    
    protected static void Error(String title, String header, String context){
        Alert al = new Alert(AlertType.ERROR);
        al.setTitle(title);
        al.setHeaderText(header);
        al.setContentText(context);
        
        Stage stg = (Stage) al.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        
        al.showAndWait();
    }
    
}
