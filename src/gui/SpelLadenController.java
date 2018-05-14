package gui;

import exceptions.SpelerHeeftGeenOpgeslagenSpellenException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Voorziet de functionaliteit om een spel in te laden
 * @author Groep 77
 */
public class SpelLadenController implements Initializable {
    private String naam;
    private int mg;
    private Dialog dg;
    
    /**
     * Initialiseerd de controller klasse
     * @param url URL
     * @param rb ResourceBundle
     * @author thormas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * gaat de naam en moeilijkheidsGraad instellen 
     * @param naam String
     * @param mg Integer
     * @author Michiel S.
     */
    protected void stelGegevensIn(String naam, int mg){
        this.naam = naam;
        this.mg = mg;
    }
    
    /**
     * Sluit de geopende Dialog
     * @author Michiel S.
     */
    protected void sluitDialog(){
        Stage stg = (Stage) this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }
    
    /**
     * Opent een dialog met daarin een TableView van de spellen.
     * deze spellen zijn ingeladen doormiddel van de domeinController methode 
     * geefOpgeslagenSpellen. de table view wordt opgesteld doormiddel van laadSpelRij klassen.
     * Handeld ook de SpelerHeeftGeenOpgeslagenSpellenException af.
     * @see domein.DomeinController#geefOpgeslagenSpellen() geefOpgeslagenSpellen
     * @author Michiel S.
     */
    protected void toonSpellen(){
        try{
            String[][] spellen = WelkomController.dc.geefOpgeslagenSpellen();
            ObservableList<laadSpelRij> lijst = FXCollections.observableArrayList();

            for(String[] spel: spellen){
                lijst.add(new laadSpelRij(spel[0],Integer.valueOf(spel[1])));
            }
            
            TableView<laadSpelRij> tv = new TableView<>();
            
            TableColumn<laadSpelRij,String> naam = new TableColumn<>();
            naam.setText("Naam");
            naam.setMinWidth(150);
            naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
            
            TableColumn<laadSpelRij,String> moeilijkheidsGraad= new TableColumn<>();
            moeilijkheidsGraad.setText("MoeilijkheidsGraad");
            moeilijkheidsGraad.setMinWidth(200);
            moeilijkheidsGraad.setCellValueFactory(new PropertyValueFactory<>("mg"));
            
            TableColumn<laadSpelRij,Button> laadRij= new TableColumn<>();
            laadRij.setMinWidth(100);
            laadRij.setSortable(false);
            laadRij.setCellValueFactory(new PropertyValueFactory<>("btn"));
            
            tv.getColumns().addAll(naam,moeilijkheidsGraad,laadRij);
            tv.setItems(lijst);
            
            dg = new Dialog();
            dg.setTitle("Laad een spel menu");
            dg.getDialogPane().setContent(tv);
            
            Stage stg = (Stage) dg.getDialogPane().getScene().getWindow();
            stg.setAlwaysOnTop(true);
            stg.toFront();
            stg.setOnCloseRequest(evt->stg.close());
            stg.getIcons().add(new Image("/gui/images/pika.png"));
                    
            dg.show();
        }catch(SpelerHeeftGeenOpgeslagenSpellenException shg){
            WelkomController.Error("Geen opgeslagen spellen", "je hebt geen opgeslagen spellen");
        }
    }
    
    /**
     * laad een spel in, veranderd het scherm naar spelbord en maakt doormiddel
     * van de spelbordController het juiste spelbord aan
     * @see domein.DomeinController#selecteerSpel(java.lang.String) selecteerSpel
     * @see WelkomController#veranderScherm(java.lang.String) veranderScherm
     * @see WelkomController#geefController() geefController
     * @see SpelbordController#setMoeilijkheidsGraad(int) setMoeilijkheidsGraad
     * @see SpelbordController#buildGui() buildGui()
     * @author Michiel S.
     */
    protected void laadSpel(){
        WelkomController.dc.selecteerSpel(naam);

        WelkomController.veranderScherm("Spelbord.fxml");
        
        SpelbordController sc = (SpelbordController)WelkomController.geefController();
        sc.setMoeilijkheidsGraad(mg);
        sc.buildGui();
    }
    
}
