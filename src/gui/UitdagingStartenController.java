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
 *
 * @author Michiel S.
 */
public class UitdagingStartenController implements Initializable{
    Dialog dg;
    int mg;
    String naamTegenstander;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       
    }
    
    protected void sluitDialog(){
        Stage stg = (Stage) this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }

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
    
    public void selecteerMoeilijkheidsgraad(){
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
    
    public void setGegevens(String naam){
        this.naamTegenstander = naam;
    }
    
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
            WelkomController.Error("Er is niemand beschikbaar op deze moeilijkheidsgraad", "Er is niemand beschikbaar op deze moeilijkheidsgraad", 
                    "Er is niemand beschikbaar op deze moeilijkheidsgraad");
        }
    }
    
    public void startUitdaging(){
        try{
            sluitDialog();
            
            WelkomController.dc.startUitdaging(naamTegenstander, mg);
            WelkomController.veranderScherm("Spelbord.fxml");
            
            SpelbordController sc = (SpelbordController) WelkomController.geefController();
            sc.setMoeilijkheidsGraad(mg);
            sc.buildGui();
        }catch(SpelerAlUitgedaagdException saue){
            WelkomController.Error("Je hebt deze speler al uitgedaagd.","Je hebt deze speler al uitgedaagd.", "Je hebt deze speler al uitgedaagd.");
        }catch(HeeftLopendeUitdagingException hlue){
            WelkomController.Error("Je hebt nog een lopende uitdaging", "Je hebt nog een lopende uitdaging", "Je hebt nog een lopende uitdaging");
        }
        
        
    }
}
