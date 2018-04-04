
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class LogInScherm extends BorderPane{
    
    public LogInScherm(){
        try
        {
            FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException ex){
  
        }
    }
    
}
