
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class RegistreerScherm extends BorderPane
{
    public Parent maakParent(){
        Parent pr = null;
        
        try{
            pr = FXMLLoader.load(getClass().getResource("Registreer.fxml"));
        } catch (IOException ex){
          
        }
        
        return pr;
    }

}
