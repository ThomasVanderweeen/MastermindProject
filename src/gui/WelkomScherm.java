package gui;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class WelkomScherm extends BorderPane
{
    
    private RegistreerScherm rs;
    private LogInScherm lis;
    
        public WelkomScherm(){
            try{
                this.setCenter(FXMLLoader.load(getClass().getResource("Welkom.fxml")));
                
            } catch (IOException ex){
                
            }
        }
        
}