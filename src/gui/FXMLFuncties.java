package gui;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * klasse verantwoordelijk voor het aanmaken van FXMLloader en inladen van
 * fxml files.
 * @author Thomas V.
 */
public class FXMLFuncties 
{
    
    private static FXMLLoader fxml;
    

        /**
         * Neemt een path aan en laad de xml in op dit path
         * @param  Resource String
         * @return Parent p
         * @author Thomas v.
         */
        protected Parent maakParent(String Resource){
            Parent pr = null;
            
            try{
                fxml = new FXMLLoader(getClass().getResource(Resource));
                pr = fxml.load();
                
            } catch (IOException ex){
                
            }
        
            return pr;
        }
        
        /**
         * Geeft de huidige controller klasse terug die is ingeladen
         * (moet nog correct gecast worden)
         * @return Controller Object
         * @author Michiel S.
         */
        protected Object geefController(){
            return fxml.getController();
        }
     
        /**
         * Zet de huidige controller klasse naar die meegegeven bij de parameter
         * @param controller  Object
         * @author Michiel S.
         */
        protected void setController(Object controller){
            fxml.setController(controller);
        }
}