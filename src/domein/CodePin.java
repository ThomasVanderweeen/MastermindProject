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
public class CodePin extends Pin{
    private String kleur;
    private static final String[] GELDIGE_KLEUREN = new String[] {"groen","geel","paars","blauw","oranje","bruin","roze","cyaan"};

    public CodePin(String kleur) {
        super(kleur);
        bepaalGeldigeKleur(kleur);
    }
    
    public CodePin(int kleur){
        super(GELDIGE_KLEUREN[kleur]);
    }
    
    
    @Override
    public String getKleur() {
        return super.getKleur();
    }
    
    @Override
    protected void bepaalGeldigeKleur(String kleur) {
        if(!Arrays.asList(GELDIGE_KLEUREN).contains(kleur))
            throw new InputMismatchException();    
    }
    
    public static String[] getGeldigeKleuren(){
        return GELDIGE_KLEUREN;
    }
}
