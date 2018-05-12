package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Verantwoordelijk voor het aanmaken van een uitdagersRij voor in de tableview
 * @author Groep 77
 */
public class UitdagersRij {
    private String naam;
    private int gewonnen;
    private Button btn;
    
    /**
     * Constructor en roept de setters aan
     * @param naam
     * @param Gewonnen 
     * @see setNaam
     * @see setGewonnen
     * @author Michiel S.
     */
    public UitdagersRij(String naam,int Gewonnen){
        setNaam(naam);
        setGewonnen(Gewonnen);
    }
    
    /**
     * stelt de naam in van de uitdager 
     * @param naam 
     * @author Michiel S.
     */
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    /**
     * stelt het aantal gewonnen in en de knop daaguit voor in het tableview
     * stelt ook de eventhandler in voor deze knop
     * @param Gewonnen 
     * @author Michiel S.
     */
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
    
    /**
     * retourneerd de naam van uitdager.
     * @return String
     * @author Michiel S.
     */
    protected String getNaam(){
        return this.naam;
    }
    
    /**
     * retourneerd de knop van de uitdager
     * @return Button
     * @author Michiel S.
     */
    protected Button getBtn(){
        return this.btn;
    }
    
    /**
     * geeft terug hoe vaak de uitdager is gewonnen
     * @return Integer
     * @author Michiel S.
     */
    protected int getGewonnen(){
        return this.gewonnen;
    }
    
}
