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
import javafx.scene.image.ImageView;

public class MenuSchermController {

    private final ScreenController sc = new ScreenController();
    private final LogInScherm ls = new LogInScherm();
    private final RegistreerScherm rs = new RegistreerScherm();
    private final DomeinController dc = new DomeinController();
    private ResourceBundle r;
    
    @FXML
    private Button engels;

    @FXML
    private Button frans;

    @FXML
    private ImageView nederlands;

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

    public void startGeklikt(){
        Parent pr = MenuSchermController.this.rs.maakParent();
        MenuSchermController.this.sc.changeScene(pr);
    }
    
    public void laadGeklikt(){
        Parent pr = MenuSchermController.this.rs.maakParent();
        MenuSchermController.this.sc.changeScene(pr);
    }
    
    public void daagUitGeklikt(){
        Parent pr = MenuSchermController.this.rs.maakParent();
        MenuSchermController.this.sc.changeScene(pr);
    }
    
    public void bekijkUitdagingenGeklikt(){
        Parent pr = MenuSchermController.this.rs.maakParent();
        MenuSchermController.this.sc.changeScene(pr);
    }
    
    public void bekijkScorebordGeklikt(){
        Parent pr = MenuSchermController.this.rs.maakParent();
        MenuSchermController.this.sc.changeScene(pr);
    }
}

