package gui;

import exceptions.GeenOpenstaandeUitdagingException;
import exceptions.HeeftLopendeUitdagingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * alle functionaliteit om een uitdaging te accepteren wordt hier afgehandeld.
 * @author Groep 77
 */
public class UitdagingenController implements Initializable
{
    private Dialog dg=null;
    private int ID = 0;
    private String naam = "";
    private int mg =0;

    /**
     * initialiseerd de controller
     * @param url URL
     * @param rb ResourceBundle
     * @author Thomas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    /**
     * sluit het geopend dialoog venster
     * @author Michiel S.
     */
    protected void closeDialog(){
        Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }

    /**
     * toont alle uitdagingen die worden opgehaald uit de domeinController
     * deze uitdagingen worden weergegeven in een tableview van UitdagingRij
     * handeld ook: GeenOpenstaandeUitdagingException en HeeftLopendeUitdagingException af
     * @see domein.DomeinController#geefLijstUitdagingen() geefLijstUitdagingen
     * @author Michiel S.
     */
    protected void toonUitdagingen() {
        try{
            String[][] uitdagingen = WelkomController.dc.geefLijstUitdagingen();
            String[] moeilijkheidsgraden = {WelkomController.r.getString("makkelijk"), WelkomController.r.getString("gemiddeld"),
                WelkomController.r.getString("moeilijk")};
            Label[] naam,moeilijkheidsgraad = new Label[uitdagingen.length];
            
        
            ObservableList<UitdagingRij> lijst = FXCollections.observableArrayList();
            for(String[] uitdaging:uitdagingen){
                lijst.add(new UitdagingRij(Integer.valueOf(uitdaging[0]),
                uitdaging[1],Integer.valueOf(uitdaging[2])));
            }
            
            TableView<UitdagingRij> tv = new TableView<>();
            
            TableColumn<UitdagingRij,String> naamUitdager = new TableColumn<>("Naam");
            naamUitdager.setMinWidth(100);
            naamUitdager.setCellValueFactory(new PropertyValueFactory<>("naam"));
            
            TableColumn<UitdagingRij,String> mg = new TableColumn<>("MoesilijkheidsGraad");
            mg.setMinWidth(150);
            mg.setCellValueFactory(new PropertyValueFactory<>("moeilijkheidsGraad"));
            
            TableColumn<UitdagingRij,Button> accept = new TableColumn<>("");
            accept.setMinWidth(150);
            accept.setCellValueFactory(new PropertyValueFactory<>("accepteer"));
            
            tv.getColumns().addAll(naamUitdager,mg,accept);
            tv.setItems(lijst);
            
            this.dg = new Dialog<>();
            this.dg.getDialogPane().setContent(tv);
            this.dg.setTitle("Accepteer Uitdaging");

            Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
            stg.setAlwaysOnTop(true);
            stg.toFront();
            stg.setOnCloseRequest(evt->stg.close());
            stg.getIcons().add(new Image("/gui/images/pika.png"));
            this.dg.show();
            
        }catch(GeenOpenstaandeUitdagingException goue){
            WelkomController.Error("Geen openstaande Exception",
                    "probeer later opnieuw");
        }catch(HeeftLopendeUitdagingException hlue){
            WelkomController.Error("heeft Lopende uitdaging",
                    "Je hebt al reeds een lopende uitdaging.");
        }


    }

    /**
     * eenmaal de uitdaging is gekozen moet er beslist worden of deze wordt verwijderd
     * of aanvaard
     * @see verwijderUitdaging
     * @see accepteerUitdaging
     * @author Michiel S.
     */
    protected void kiesUitdaging() {
        BorderPane bp = new BorderPane();
        
        Label lb = new Label();
        lb.setText("Wat wil je doen met deze uitdaging?");
        bp.setCenter(lb);
        
        HBox hb = new HBox();
        
        Button verwijder = new Button();
        verwijder.setText("Verwijder");
        verwijder.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                UitdagingenController.this.closeDialog();
                UitdagingenController.this.verwijderUitdaging();
            } 
        });
        
        Button accepteer = new Button();
        accepteer.setText("Accepteer");
        accepteer.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
                UitdagingenController.this.closeDialog();
                UitdagingenController.this.accepteerUitdaging();
            }
            
        });
        
        hb.getChildren().addAll(verwijder,accepteer);
        bp.setBottom(hb);
        
        this.dg.getDialogPane().setContent(bp);
        Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        stg.getIcons().add(new Image("/gui/images/pika.png"));
        
        this.dg.show();
    }
    
    /**
     * Stelt de ID, naam en moeilijkheidsgraad in van de uitdaging volgens de
     * meegeleverde parameters
     * @param ID Integer
     * @param naam String
     * @param moeilijkheidsGraad Integer
     * @author Michiel S.
     */
    protected void stelGegevensIn(int ID,String naam,int moeilijkheidsGraad){
        this.ID = ID;
        this.naam = naam;
        this.mg = moeilijkheidsGraad;
    }
    
    /**
     * Verwijderd de uitdaging uit de database door de methode verwijderUitdaging
     * van de DomeinController mee te geven en geeft eveneens een boodschap dat dit succevol gebeurd is
     * @see verwijderUitdaging
     * @author Michiel S.
     */
    private void verwijderUitdaging(){
        WelkomController.dc.verwijderUitdaging(this.ID);
        
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setTitle("Uitdaging Verwijderd");
        al.setContentText("Exception");
        al.setHeaderText("Uitdaging succesvol verwijderd");
        
        Stage stg = (Stage) al.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        
        al.show();
    }
    
    /**
     * De uitdaging wordt geacepteer volgens de methode laadUitdaging van de domeinController
     * en het scherm wordt veranderd naar dat van het spelbord
     * @see veranderScherm
     * @see laadUitdaging
     * @see setMoeilijkheidsGraad
     * @see buildGui
     * @see geefController
     * @author Michiel S.
     */
    private void accepteerUitdaging(){
        WelkomController.dc.laadUitdaging(ID, naam);
        WelkomController.veranderScherm("spelbord.fxml");
        
        SpelbordController sc = (SpelbordController) WelkomController.geefController();
        sc.setMoeilijkheidsGraad(mg);
        sc.buildGui();
        
    }


}
