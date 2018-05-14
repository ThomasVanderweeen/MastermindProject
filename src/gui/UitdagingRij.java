package gui;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *Verantwoordelijk voor tableview rij bij uitdagingStarten table
 * @author Groep 77
 */
public class UitdagingRij {
    private int ID;
    private String naam;
    private String moeilijkheidsGraad;
    private Button accepteer;
    private static String[] mg = {"makkelijk","gemiddeld","moeilijk"};
    
    /**
     * Constructor en roept de setters aan van de overeenkomstige attributen
     * @see setID
     * @see setNaam
     * @see setMoeilijkheidsGraad
     * @param ID Integer
     * @param naam String
     * @param moeilijkheidsGraad Integer
     * @author Ferre
     */
    public UitdagingRij(int ID, String naam, int moeilijkheidsGraad){
        setID(ID);
        setNaam(naam);
        setMoeilijkheidsGraad(moeilijkheidsGraad);
    }
    
    /**
     * stelt het ID in van de UitdagingRij en maakt  ook een aanvaard knop aan met
     * de juiste eventhandler
     * 
     * @param ID 
     */
    private void setID(int ID){
        this.ID = ID;
        this.accepteer = new Button("selecteer");
        this.accepteer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                UitdagingenController uc = (UitdagingenController) WelkomController.geefController();
                uc.closeDialog();
                uc.stelGegevensIn(UitdagingRij.this.ID,UitdagingRij.this.naam,
                        Arrays.asList(mg).indexOf(UitdagingRij.this.moeilijkheidsGraad)+1);
                uc.kiesUitdaging();
            }
        });
    }
    
    /**
     * Stelt de naam in van de uitdagingRij
     * @param naam 
     * @author Ferre
     */
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    /**
     * stelt de moeilijkheidsGraad in van de uitdagingRij
     * @param mog 
     * @author Ferre
     */
    private void setMoeilijkheidsGraad(int mog){
        this.moeilijkheidsGraad = mg[mog-1];
    }
    
    /**
     * geeft de Accepteer knop terug
     * @return Button
     * @author Ferre
     */
    public Button getAccepteer(){
        return this.accepteer;
    }
    
    /**
     * Geeft de naam terug van de Uitdager
     * @return String
     * @author Ferre
     */
    public String getNaam(){
        return this.naam;
    }
    
    /**
     * Geeft de moeilijkheidsGraad terug van de Uitdaging
     * @return String
     * @author Ferre
     */
    public String getMoeilijkheidsGraad(){
        return this.moeilijkheidsGraad;
    }
}
