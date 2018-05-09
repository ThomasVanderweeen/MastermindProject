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
public class UitdagingScherm extends BorderPane {
        public Parent maakParent(){
            Parent pr =null;
            
            try
            {
                pr = FXMLLoader.load(getClass().getResource("Uitdaging.fxml"));
            } catch (IOException ex)
            {
                Logger.getLogger(UitdagingScherm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return pr;
        }
}
