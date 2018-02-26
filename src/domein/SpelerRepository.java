/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.SpelerMapper;

/**
 *
 * @author Groep 77
 */
public class SpelerRepository {
    
    private final SpelerMapper mapper;

    public SpelerRepository() {
        this.mapper = new SpelerMapper();
    }
    
    /*Vrij robuust?!?!*/
    public boolean spelerBestaat(String naam){
        return this.mapper.spelerBestaat(naam);
    }
    
    public Speler controleerAanmelden(String naam, String Wachtwoord){
        Speler sp = this.mapper.geefSpeler(naam);
        if(sp.getWachtwoord().equals(Wachtwoord))
            return sp;
        else
            throw new IllegalArgumentException("Wachtwoord komt niet overeen. :c");
    }
    
    
}
