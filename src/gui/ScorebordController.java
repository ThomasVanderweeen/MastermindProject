package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScorebordController implements Initializable
{
    private Dialog dg = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    private void openDialog(){
       Stage stg = (Stage)this.dg.getDialogPane().getScene().getWindow();
       stg.setAlwaysOnTop(true);
       stg.getIcons().add(new Image("/gui/images/pika.png"));
       
       stg.setOnCloseRequest(evt->closeDialog());
       
       this.dg.show();
    }
    
    private void closeDialog(){
        Stage stg = (Stage) this.dg.getDialogPane().getScene().getWindow();
        stg.close();
    }
    
    public void geefKeuze(){
        this.dg = new Dialog();
       String s = WelkomController.r.getString("ScoreKeuze");
       int i =1;
       
       Button mak = new Button(WelkomController.r.getString("makkelijk"));
       Button gem = new Button(WelkomController.r.getString("gemiddeld"));
       Button moei = new Button(WelkomController.r.getString("moeilijk"));
       Button[] buttons = {mak,gem,moei};
       
       for(Button bt:buttons){
           bt.setId(String.valueOf(i));
           i++;
           bt.setOnAction(new EventHandler<ActionEvent>(){
               @Override
               public void handle(ActionEvent event)
               {
                  closeDialog();
                  geefUitdagingMenu(Integer.valueOf(bt.getId()));
               }
            });

       }
       
       HBox hb = new HBox();
       hb.getChildren().addAll(mak,gem,moei);
       
       this.dg.getDialogPane().setHeaderText(s);
       this.dg.getDialogPane().setContent(hb);
       
       openDialog();

    }
    
    private void geefUitdagingMenu(int mg){
        List<String[]> geefKlassement = WelkomController.dc.geefKlassement(mg);
        ObservableList<ScoreBordRij> lijst = FXCollections.observableArrayList();
        
        for(String[] st:geefKlassement){
            lijst.add(new ScoreBordRij(st[0],(st[1]==null?0:Integer.valueOf(st[1]))));
        }
        
        TableView<ScoreBordRij> tv = new TableView<>();
        
        TableColumn<ScoreBordRij,String> naam = new TableColumn<>();
        naam.setText(WelkomController.r.getString("naam"));
        naam.setMinWidth(150);
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
            
        TableColumn<ScoreBordRij,Integer> score = new TableColumn<>();
        score.setText("score");
        score.setMinWidth(150);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        
        tv.getColumns().addAll(naam,score);
        tv.setItems(lijst);
        
        this.dg = new Dialog();
        this.dg.getDialogPane().setContent(tv);
        this.openDialog();
    }
    
}
