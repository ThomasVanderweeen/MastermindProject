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
public class EvaluatiePin extends Pin{
    private String kleur;
    private static final String[] GELDIGE_KLEUREN = new String[] {"zwart","wit","rood"};

    public EvaluatiePin(String kleur) {
        super(kleur);
    }

    
    @Override
    public String getKleur() {
        return kleur;
    }
    
    @Override
    public void geldigePin(Pin pin) {
        if(!Arrays.asList(GELDIGE_KLEUREN).contains(pin.getKleur()))
            throw new InputMismatchException();
    }
}
