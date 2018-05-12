package gui;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Zorgt voor het voorzien van een opstart van de gui applicatie
 * @author Michiel S.
 */
public class ScreenController extends Application{
    private static Stage primary;
    
    /**
     * Start de gui applicatie op met de default args 
     * @param args String[]
     * @author Thomas
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Maakt een Scene aan brengt deze naar de voorgrond en stelt de controller klasse in
     * initaliseerd de FXMLFuncties klasse stelt deze in
     * @see maakParent
     * @param primaryStage stage
     * @author Thomas
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
      setPrimary(primaryStage);  

      FXMLFuncties ws = new FXMLFuncties();
      Parent root = ws.maakParent("Welkom.fxml");
      
      WelkomController wc = (WelkomController)ws.geefController();
      wc.setWelkomScherm(ws);
      
      Scene scene = new Scene(root);
      scene.getWindow();
      primary.setTitle("Mastermind G77");
      primary.setScene(scene);
      primary.getIcons().add(new Image("/gui/images/pika.png"));
      primary.setAlwaysOnTop(true);
      primary.setResizable(false);
      primary.show();
    }
    
    /**
     * zorgt dat de static variabele primary (stage) wordt ingesteld
     * @param st 
     */
    private static void setPrimary(Stage st){
        primary = st;
    }
    
    /**
     * veranderd de scene
     * @param pr 
     */
    public void changeScene(Parent pr){
       Scene scene = new Scene(pr);
       primary.setScene(scene);
       primary.show();
    }
    

}
