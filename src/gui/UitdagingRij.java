package gui;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;

/**
 *
 * @author Michiel S.
 */
public class UitdagingRij {
    private int ID;
    private String naam;
    private String moeilijkheidsGraad;
    private Button accepteer;
    private static String[] mg = {"makkelijk","gemiddeld","moeilijk"};
    
    public UitdagingRij(int ID, String naam, int moeilijkheidsGraad){
        setID(ID);
        setNaam(naam);
        setMoeilijkheidsGraad(moeilijkheidsGraad);
    }
    
    private void setID(int ID){
        this.ID = ID;
        this.accepteer = new Button("selecteer");
        this.accepteer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                UitdagingenController uc = UitdagingScherm.geefController();
                uc.closeDialog();
                uc.stelGegevensIn(UitdagingRij.this.ID,UitdagingRij.this.naam,
                        Arrays.asList(mg).indexOf(UitdagingRij.this.moeilijkheidsGraad)+1);
                uc.kiesUitdaging();
            }
        });
    }
    
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    private void setMoeilijkheidsGraad(int mog){
        this.moeilijkheidsGraad = mg[mog-1];
    }
    
    public Button getAccepteer(){
        return this.accepteer;
    }
    
    public String getNaam(){
        return this.naam;
    }
    
    public String getMoeilijkheidsGraad(){
        return this.moeilijkheidsGraad;
    }
}
