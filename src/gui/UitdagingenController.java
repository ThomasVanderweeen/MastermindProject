/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ThomasV
 */
public class UitdagingenController implements Initializable {

    private DomeinController dc;
    
    private ResourceBundle r;
    @FXML
    private Button engels;
    @FXML
    private Button frans;
    @FXML
    private Button nederlands;
    @FXML
    private Label uitdagingenLabel;
    @FXML
    private Label labelNaam;
    @FXML
    private Label labelMoeilijk;
    @FXML
    private Button naam1;
    @FXML
    private Button moeilijk1;
    @FXML
    private Button naam2;
    @FXML
    private Button moeilijk2;
    @FXML
    private Button naam3;
    @FXML
    private Button moeilijk3;
    @FXML
    private Button naam4;
    @FXML
    private Button moeilijk4;
    @FXML
    private Button naam5;
    @FXML
    private Button moeilijk5;
    @FXML
    private Button naam6;
    @FXML
    private Button moeilijk6;
    @FXML
    private Button naam7;
    @FXML
    private Button moeilijk7;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void updateResourceBundle(String taal){
        
        switch(taal){
            case "frans":
                this.r = ResourceBundle.getBundle("resources/Français_fr");
                break;
            case "nederlands":
                this.r = ResourceBundle.getBundle("resources/Nederlands_ne");
                break;
            case "engels":
                this.r = ResourceBundle.getBundle("resources/English_en");
                break;
            default:
                System.err.println("foute keuze");
                break;
        }
        
        updateLabels();
    }
    
    private void updateLabels(){
        uitdagingenLabel.setText(this.r.getString("uitdagingen"));
        labelNaam.setText(this.r.getString("naamUitdager"));
        labelMoeilijk.setText(this.r.getString("moeilijkheidsGraad"));
        
        
    }
    
    public void toonUitdagingen(){
        
        List<Button> namen = new ArrayList<>();
        namen.add(naam1);
        namen.add(naam2);
        namen.add(naam3);
        namen.add(naam4);
        namen.add(naam5);
        namen.add(naam6);
        namen.add(naam7);
        List<Button> niveau = new ArrayList<>();
        niveau.add(moeilijk1);
        niveau.add(moeilijk2);
        niveau.add(moeilijk3);
        niveau.add(moeilijk4);
        niveau.add(moeilijk5);
        niveau.add(moeilijk6);
        niveau.add(moeilijk7);

        
        String[][] uitdagingen = dc.geefLijstUitdagingen();
        String[] moeilijkheidsgraden = {r.getString("makkelijk"),r.getString("gemiddeld"),r.getString("moeilijk")};
        
        for(int i = 0; i < namen.size(); i++){
            namen.get(i).setText(uitdagingen[i][1]);
            niveau.get(i).setText(moeilijkheidsgraden[Integer.valueOf(uitdagingen[i][2])-1]);
        }
        
        
    }
    
    public void kiesUitdaging(){
        String[][] uitdagingen = dc.geefLijstUitdagingen();
        
        List<Button> namen = new ArrayList<>();
        namen.add(naam1);
        namen.add(naam2);
        namen.add(naam3);
        namen.add(naam4);
        namen.add(naam5);
        namen.add(naam6);
        namen.add(naam7);
        
        List<Button> niveau = new ArrayList<>();
        niveau.add(moeilijk1);
        niveau.add(moeilijk2);
        niveau.add(moeilijk3);
        niveau.add(moeilijk4);
        niveau.add(moeilijk5);
        niveau.add(moeilijk6);
        niveau.add(moeilijk7);
        
        naam1.setOnAction(e-> );        
        int ID = Integer.valueOf(uitdagingen[keuze-1][0]);
        String tegenstander = uitdagingen[keuze-1][1];
    }
    
    
    
    @FXML
    public void engelsGeklikt(){
        updateResourceBundle("engels");
    }
    
    @FXML
    public void fransGeklikt(){
        updateResourceBundle("frans");
    }
    
    @FXML
    public void nederlandsGeklikt(){
        updateResourceBundle("nederlands");
    }
}
