package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

public class ScorebordController implements Initializable
{

    private Dialog dg = null;
    private int ID = 0;
    private String naam = "";
    private int mg = 0;

    private ResourceBundle r;
    @FXML
    private Button engels;
    @FXML
    private Button frans;
    @FXML
    private Button nederlands;
    @FXML
    private Button makkelijk;
    @FXML
    private Button gemiddeld;
    @FXML
    private Button moeilijk;
    @FXML
    private ListView<String> scorebord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toonMoeilijkheidsgraad(1);
    }

    public void toonScorebord() {

    }



    public void toonMoeilijkheidsgraad(int graad) {
        List<String[]> spelers = new ArrayList<String[]>();
        spelers = WelkomController.dc.geefKlassement(graad);
        dg = new Dialog();
        dg.setTitle("MoeilijkheidsGraad");
        
        int i = 1;
        
        scorebord.setCellFactory(TextFieldListCell.forListView());
        scorebord.setEditable(false);
        scorebord.getItems().add(0,"Player  Won");
        for(String[] speler: spelers){
            String naam = speler[0];
            int gewonnen = Integer.parseInt(speler[1]);
            scorebord.getItems().add(i,naam+gewonnen);
            scorebord.edit(i);
            i++;
        }
        
        dg.getDialogPane().setContent(scorebord);
        Stage sg = (Stage) dg.getDialogPane().getScene().getWindow();
        sg.setAlwaysOnTop(true);
        sg.toFront();
        
        dg.show();
    }

}
