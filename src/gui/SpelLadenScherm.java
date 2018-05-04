/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ThomasV
 */
public class SpelLadenScherm extends AnchorPane{
    
    
    public Parent maakParent(){
        Parent pr = null;
        
        try{
            pr = FXMLLoader.load(getClass().getResource("SpelLaden.fxml"));
        } catch (IOException ex){
          
        }
        
        return pr;
    }
}
