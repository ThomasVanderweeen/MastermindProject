package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Michiel S.
 */
public class UitdagenScherm extends BorderPane {
        private static FXMLLoader fxml;
        public Parent maakParent(){
            Parent pr =null;
            
            try
            {
                fxml = new FXMLLoader(getClass().getResource("Uitdagen.fxml"));
                pr = fxml.load();
            } catch (IOException ex)
            {
                Logger.getLogger(UitdagenScherm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return pr;
        }
        
        public static UitdagenController geefController(){
            return fxml.getController();
        }
}
