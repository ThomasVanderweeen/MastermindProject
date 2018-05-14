package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * LaadSpelRij functionaliteit voor weergave laadSpel Tableview
 * @author Groep 77
 */
public class laadSpelRij {
    private String naam;
    private int moeilijkheidsGraad;
    private String mg;
    private final String[] moeilijkheidsGraden = {WelkomController.r.getString("makkelijk"),
    WelkomController.r.getString("gemiddeld"),WelkomController.r.getString("moeilijk")};
    private Button btn;
    
    /**
     * constructors en roept de setters aan voor meegegeven parameters
     * @param naam String
     * @param moeilijkheidsGraad Integer
     * @see setNaam
     * @see setMoeilijkheidsGraad
     * @author Ferre
     */
    public laadSpelRij(String naam,int moeilijkheidsGraad){
        setNaam(naam);
        setMoeilijkheidsGraad(moeilijkheidsGraad);
    }
    
    /**
     * Stelt de naam in volgens de aangeleverde parameter
     * @param naam 
     * @author Ferre
     */
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    /**
     * stelt de moelijkheidsgraad in, en maakt de knop laad spel aan,
     * stelt eveneens de actionevent handler in.
     * @param moeilijkheidsGraad 
     * @author Ferre
     */
    private void setMoeilijkheidsGraad(int moeilijkheidsGraad){
        this.moeilijkheidsGraad = moeilijkheidsGraad;
        this.btn = new Button();
        this.btn.setText(WelkomController.r.getString("laadKnop"));
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
    
    /**
     * geeft de naam terug van het opgeslagen spel
     * @return String
     * @author Ferre
     */
    public String getNaam(){
        return this.naam;
    }
    

    /**
     * geeft de laad knop terug 
     * @return Button 
     * @author Ferre
     */
    public Button getBtn(){
        return this.btn;
    }
    

    /**
     * geeft de moeilijkheidsGraad terug
     * @return String
     * @author Ferre
     */
    public String getMg(){
        return this.mg;
    }
}
