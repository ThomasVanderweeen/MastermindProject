
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class MenuScherm extends BorderPane{
    
    public MenuScherm(){
        try
        {
            FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex){
  
        }
    }
    
}
