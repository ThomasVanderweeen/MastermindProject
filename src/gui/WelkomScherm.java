package gui;

/**
 *
 * @author Michiel S.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class WelkomScherm extends BorderPane
{
    
    private RegistreerScherm rs;
    private LogInScherm lis;
    
        public WelkomScherm(){
            try{
                this.setCenter(FXMLLoader.load(getClass().getResource("Welkom.fxml")));
            } catch (IOException ex){
                
            }
        }
    
}