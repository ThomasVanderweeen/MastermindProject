
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class MenuScherm extends BorderPane{
    
    public MenuScherm(){
        try
        {
            FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex){
  
        }
    }
    
    public Parent maakParent(){
        Parent pr = null;
        
        try{
            pr = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex){
          
        }
        
        return pr;
    }
}
