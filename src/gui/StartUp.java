package gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Michiel S.
 */
public class StartUp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
      Parent root = new WelkomScherm();
      Scene scene = new Scene(root);
      
      primaryStage.setTitle("Mastermind G77");
      primaryStage.setScene(scene);
      
      primaryStage.show();
    }

}
