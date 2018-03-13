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
    private String kleur;
    private final String[] geldigeKleuren;

    public CodePin(String kleur) {
        super(kleur);
        this.geldigeKleuren = new String[] {"groen","geel","paars","blauw","oranjge","bruin","roze","cyaan"};
    }
    
    @Override
    public String getKleur() {
        return kleur;
    }
    
    @Override
    public void geldigePin(Pin pin) {
        if(!Arrays.asList(geldigeKleuren).contains(pin.getKleur()))
            throw new InputMismatchException();    
    }
}
