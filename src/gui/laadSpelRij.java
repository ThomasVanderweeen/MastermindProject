package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Groep 77
 */
public class laadSpelRij {
    private String naam;
    private int moeilijkheidsGraad;
    private String mg;
    private final String[] moeilijkheidsGraden = {WelkomController.r.getString("makkelijk"),
    WelkomController.r.getString("gemiddeld"),WelkomController.r.getString("moeilijk")};
    private Button btn;
    
    public laadSpelRij(String naam,int moeilijkheidsGraad){
        setNaam(naam);
        setMoeilijkheidsGraad(moeilijkheidsGraad);
    }
    
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    private void setMoeilijkheidsGraad(int moeilijkheidsGraad){
        this.moeilijkheidsGraad = moeilijkheidsGraad;
        this.btn = new Button();
        this.btn.setText("laad");
        this.mg = moeilijkheidsGraden[this.moeilijkheidsGraad-1];
        
        this.btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                SpelLadenController slc = (SpelLadenController) WelkomController.geefController();
                slc.stelGegevensIn(naam, moeilijkheidsGraad);
                slc.sluitDialog();
                slc.laadSpel();
            }
            
        });
    }
    
    protected String getNaam(){
        return this.naam;
    }
    
    protected Button getBtn(){
        return this.btn;
    }
    
    protected String getMg(){
        return this.mg;
    }
}
