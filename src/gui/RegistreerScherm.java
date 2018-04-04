
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class RegistreerScherm extends BorderPane
{
    public RegistreerScherm(){
        try{
            this.setCenter(FXMLLoader.load(getClass().getResource("Registreer.fxml")));
        } catch (IOException ex){
          
        }
    }

}
