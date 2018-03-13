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
 * @author ThomasV
 */
public class CodePin extends Pin{
    private static final String[] GELDIGE_KLEUREN = new String[] {"groen","geel","paars","blauw","oranje","bruin","roze","cyaan"};

    public CodePin(String kleur) {
        super(kleur);
    }
    
    public CodePin(int kleur){
        this(GELDIGE_KLEUREN[kleur]);
    }
    
    @Override
    public String getKleur() {
        return super.getKleur();
    }
    
    @Override
    public void geldigePin(Pin pin) {
        if(!Arrays.asList(GELDIGE_KLEUREN).contains(pin.getKleur()))
            throw new InputMismatchException();    
    }
    
    public static String[] getGeldigeKleuren(){
        return GELDIGE_KLEUREN;
    }
}
