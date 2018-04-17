package gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Michiel S.
 */
public class ScreenController extends Application{
    private static Stage primary;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
      setPrimary(primaryStage);  

      Parent root = new WelkomScherm();
      Scene scene = new Scene(root);
      
      primary.setTitle("Mastermind G77");
      primary.setScene(scene);

      primary.setAlwaysOnTop(true);
      primary.show();
    }
    
    private static void setPrimary(Stage st){
        primary = st;
    }
    
    public void changeScene(Parent pr){
       Scene scene = new Scene(pr);
       primary.setScene(scene);
       primary.show();
    }
}
