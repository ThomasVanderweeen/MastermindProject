package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Michiel S.
 */
public class UitdagersRij {
    private String naam;
    private int gewonnen;
    private Button btn;
    
    public UitdagersRij(String naam,int Gewonnen){
        setNaam(naam);
        setGewonnen(Gewonnen);
    }
    
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    private void setGewonnen(int Gewonnen){
        this.gewonnen = Gewonnen;
        this.btn = new Button();
        this.btn.setText("Daag uit");
        
        this.btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                UitdagingStartenController usc = (UitdagingStartenController) WelkomController.geefController();
                usc.setGegevens(naam);
                usc.sluitDialog();
                usc.startUitdaging();
            }
            
        });
    }
    
    public String getNaam(){
        return this.naam;
    }
    
    public Button getBtn(){
        return this.btn;
    }
    
    public final int getGewonnen(){
        return this.gewonnen;
    }
    
}
