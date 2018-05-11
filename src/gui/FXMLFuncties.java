package gui;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class FXMLFuncties extends BorderPane
{
    
    private static FXMLLoader fxml;
    

        
        public Parent maakParent(String Resource){
            Parent pr = null;
            
            try{
                fxml = new FXMLLoader(getClass().getResource(Resource));
                pr = fxml.load();
                
            } catch (IOException ex){
                
            }
        
            return pr;
        }
        
        public Object geefController(){
            return fxml.getController();
        }
     
        public void setController(Object controller){
            fxml.setController(controller);
        }
}