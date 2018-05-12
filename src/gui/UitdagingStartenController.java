package gui;

import exceptions.HeeftLopendeUitdagingException;
import exceptions.NiemandBeschikbaarVoorUitdagingException;
import exceptions.SpelerAlUitgedaagdException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
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
import javafx.stage.WindowEvent;

/**
 * alle functies met betrekking tot een uitdaging te starten gaan hierin
 * @author Groep 77
 */
public class UitdagingStartenController implements Initializable{
    Dialog dg;
    int mg;
    String naamTegenstander;
    
    /**
     * initialiseer de controller klasse
     * @param location URL
     * @param resources ResourceBundle
     * @author Thomas
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       
    }
    
    /**
     * sluit het dialog venster
     * @author Michiel S.
     */
    protected void sluitDialog(){
        Stage stg = (Stage) this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }
    
    /**
     * opent het dialog venster
     * @author Michiel S.
     */
    private void openDialog(){
        Stage stg = (Stage) this.dg.getDialogPane().getScene().getWindow();
        stg.setAlwaysOnTop(true);
        stg.toFront();
        stg.getIcons().add(new Image("/gui/images/pika.png"));
        stg.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event)
            {
                sluitDialog();
            }
        
        });
        this.dg.show();
    }
    
    /**
     * stelt het scherm op dat de moeiljkheidsGraad opstelt
     * @author Michiel S.
     */
    protected void selecteerMoeilijkheidsgraad(){
        this.dg = new Dialog();
        BorderPane bp = new BorderPane();
        List<Button> knoppen = new ArrayList<>();
        
        Label lb = new Label("Kies de moeilijkheidsgraad waarin je iemand wilt uitdagen");
        bp.setCenter(lb);
        
        HBox hb = new HBox();
        
        Button makkelijk = new Button();
        makkelijk.setText("Makkelijk");
        makkelijk.setId("1");
        knoppen.add(makkelijk);
        
        Button gemiddeld = new Button();
        gemiddeld.setText("Gemiddeld");
        gemiddeld.setId("2");
        knoppen.add(gemiddeld);
        
        Button moeilijk= new Button();
        moeilijk.setText("Moeilijk");
        moeilijk.setId("3");
        knoppen.add(moeilijk);
        
        for(Button bt:knoppen){
            bt.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event)
                {
                   UitdagingStartenController.this.mg = Integer.valueOf(bt.getId());
                   sluitDialog();
                   UitdagingStartenController.this.toonBeschikbareUitdagers();
                }
            });
            
            hb.getChildren().add(bt);
        }
        
        bp.setBottom(hb);
        this.dg.getDialogPane().setContent(bp);
        openDialog();
    }
    
    /**
     * stelt de naam in van de tegenstander volgens de meegegeven parameter
     * @param naam String
     * @author Michiel S.
     */
    protected void setGegevens(String naam){
        this.naamTegenstander = naam;
    }
    
    /**
     * Haalt de tegenstanders op van de moeilijkheidsgraad in de domeinController
     * en geeft deze weer in een table view van UitdagersRij handeld ook de 
     * NiemandBeschikbaarVoorUitdagingException af.
     * @see selecteerMoeilijkheidsGraadUitdaging
     * @author Michiel S.
     */
    private void toonBeschikbareUitdagers(){
        
        try{
            this.dg = new Dialog();
            List<String[]> uitdagers = WelkomController.dc.selecteerMoeilijkheidsGraadUitdaging(mg);
            ObservableList<UitdagersRij> lijst = FXCollections.observableArrayList();
            
            for(String[] uit:uitdagers){
                lijst.add(new UitdagersRij(uit[0],
                        (uit[1]==null?0:Integer.valueOf(uit[1]))));
            }
            
            TableView<UitdagersRij> tv = new TableView<>();
            
            TableColumn<UitdagersRij,String> naam = new TableColumn<>("Naam");
            naam.setMinWidth(150);
            naam.setCellValueFactory(new PropertyValueFactory<>("Naam"));
            
            TableColumn<UitdagersRij,Integer> gewonnen = new TableColumn<>("Aantal Gewonnen");
            gewonnen.setMinWidth(150);
            gewonnen.setCellValueFactory(new PropertyValueFactory<UitdagersRij,Integer>("gewonnen"));
            
            TableColumn<UitdagersRij,Button> knoppen = new TableColumn<>("");
            knoppen.setSortable(false);
            knoppen.setMinWidth(150);
            knoppen.setCellValueFactory(new PropertyValueFactory<>("btn"));
           

            tv.setItems(lijst);
            tv.getColumns().addAll(naam,gewonnen,knoppen);
            
            this.dg.getDialogPane().setContent(tv);
            openDialog();
            
        }catch(NiemandBeschikbaarVoorUitdagingException nbvue){
            this.sluitDialog();
            WelkomController.Error("Er is niemand beschikbaar op deze moeilijkheidsgraad", 
                    WelkomController.r.getString("geenUitdagersException"));
        }
    }
    
    /**
     * start de uitdaging, veranderd het scherm naar spelbord en doet de nodige 
     * functionaliteit om het spelbord correct weer te geven. handeld ook: 
     * SpelerAlUitgedaagdException en HeeftLopendeUitdagingException af.
     * @see domein.DomeinController#startUitdaging(java.lang.String, int) startUitdaging
     * @see sluitDialog
     * @see WelkomController#veranderScherm(java.lang.String) veranderScherm
     * @see SpelbordController#setMoeilijkheidsGraad(int) setMoeilijkheidsGraad
     * @see SpelbordController#buildGui() buildGui
     * @see WelkomController#geefController() geefController
     */
    protected void startUitdaging(){
        try{
            sluitDialog();
            
            WelkomController.dc.startUitdaging(naamTegenstander, mg);
            WelkomController.veranderScherm("Spelbord.fxml");
            
            SpelbordController sc = (SpelbordController) WelkomController.geefController();
            sc.setMoeilijkheidsGraad(mg);
            sc.buildGui();
        }catch(SpelerAlUitgedaagdException saue){
            WelkomController.Error("Je hebt deze speler al uitgedaagd.","Je hebt deze speler al uitgedaagd.");
        }catch(HeeftLopendeUitdagingException hlue){
            WelkomController.Error("Je hebt nog een lopende uitdaging","Je hebt nog een lopende uitdaging");
        }
        
        
    }
}
