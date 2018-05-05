
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;



public class MenuScherm extends BorderPane{
    
    public Parent maakParent(){
        Parent pr = null;
        
       
        try
        {
            pr = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex)
        {
            Logger.getLogger(MenuScherm.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return pr;
    }
}
