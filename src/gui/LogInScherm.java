
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class LogInScherm extends BorderPane{
    
    public Parent changeScreenToLogIn(){    
        Parent pr = null;
        
        try
        {
            pr = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        } catch (IOException ex)
        {
            Logger.getLogger(LogInScherm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pr;
    }
    
}
