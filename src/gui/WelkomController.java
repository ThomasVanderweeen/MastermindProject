package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Groep 77
 */
public class WelkomController implements Initializable
{
    protected final static ScreenController sc = new ScreenController();
    protected final static DomeinController dc = new DomeinController();
    protected static ResourceBundle r;
    private static FXMLFuncties wsc;

    
    @FXML
    private ImageView nederlands;
    @FXML
    private ImageView frans;
    @FXML
    private ImageView engels;
    @FXML
    private Label welkomLabel;
    @FXML
    private Label terugkerendLabel;
    @FXML
    private Button logInKeuze;
    @FXML
    private Label geenAccountLabel;
    @FXML
    private Button registreerKeuze;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        updateResourceBundle("nederlands");
    }    
    
    private void updateResourceBundle(String taal){
        this.setResourceBundle(taal);
        updateLabels();
    }

    private void updateLabels(){
        welkomLabel.setText(r.getString("welkom"));
        terugkerendLabel.setText(r.getString("terugKerend"));
        logInKeuze.setText(r.getString("meldAan"));
        geenAccountLabel.setText(r.getString("registreerlbl"));
        registreerKeuze.setText(r.getString("registreer"));
    }
    
    @FXML
    protected void registreergeklikt(){
        veranderScherm("Registreer.fxml");
    }
    
    
    @FXML
    protected void engelsGeklikt(){
        updateResourceBundle("engels");
    }
    
    @FXML
    protected void fransGeklikt(){
        updateResourceBundle("frans");
    }
    
    @FXML
    protected void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    }
    
    @FXML
    protected void LoginGeklikt(){
        veranderScherm("LogIn.fxml");
    }
    
    protected void setWelkomScherm(FXMLFuncties ws){
       wsc = ws;
    }
    
    protected static void setResourceBundle(String taal){
        switch(taal){
            case "frans":
                r = ResourceBundle.getBundle("resources/Français_fr");
                break;
            case "nederlands":
                r = ResourceBundle.getBundle("resources/Nederlands_ne");
                break;
            case "engels":
                r = ResourceBundle.getBundle("resources/English_en");
                break;
            default:
                System.err.println("foute keuze");
                break;
        }
    }
    
    protected static void Error(String title, String context){
        Alert al = new Alert(AlertType.ERROR);
        al.setTitle(title);
        al.setHeaderText("Exception");
        
        al.setContentText(context);
        
        Stage stg = (Stage) al.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        
        al.showAndWait();
    }
    
    
    protected static void veranderScherm(String resource){
        Parent pr = wsc.maakParent(resource);
        sc.changeScene(pr);
    }
    
    protected static Object geefController(){
        return wsc.geefController();
    }
        
    protected static void setController(Object controller){
        wsc.setController(controller);
    }
   
}
