/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author Groep 77
 */
public class EvaluatiePin extends Pin{
    private String kleur;
    private static final String[] GELDIGE_KLEUREN = new String[] {"zwart","wit","leeg"};

    /**
     * Constructor voor een evaluatiepin object. Gebruikt bepaalGeldigeKleur om na te gaan of de meegegeven kleur geldig is.
     * 
     * @param kleur kleur van de evaluatiepin
     */
    public EvaluatiePin(String kleur) {
        super(kleur);
        bepaalGeldigeKleur(kleur);
    }

    /**
     * Constructor voor een evaluatiepin object. Krijgt kleur mee als een int.
     * 
     * @param kleur kleur van het object.
     */
    public EvaluatiePin(int kleur){
        super(GELDIGE_KLEUREN[kleur]);
    }
    
    /**
     * getKleur geeft de kleur van een pin object. 
     * 
     * @return String
     */
    @Override
    public String getKleur() {
        return super.getKleur();
    }
    
    /**
     * bepaalGeldigeKleur gaat na of de meegegeven kleur voorkomt in de array met alle geldige kleuren. Zoniet werpt het een inputmismatch exception.
     * 
     * @param kleur kleur die gecontrolleerd wordt.
     */
    @Override
    protected void bepaalGeldigeKleur(String kleur) {
        if(!Arrays.asList(GELDIGE_KLEUREN).contains(kleur))
            throw new InputMismatchException();
    }
}
