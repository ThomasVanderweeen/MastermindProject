/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.SpelerMapper;
import  exceptions.AanmeldException;

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
    public boolean bestaatSpeler(String naam){
        return this.mapper.spelerBestaat(naam);
    }
    
    public Speler  geefSpeler(String naam, String wachtwoord){
        Speler sp = this.mapper.geefSpeler(naam,wachtwoord);
        if(sp==null)
            throw new AanmeldException();
        else
            return sp;
    }
    
    public void voegSpelerToe(Speler sp){
        mapper.voegToe(sp);
    }
    
    public void updateScore(Speler sp, int moeilijkheidsGraad){
        mapper.updateScore(sp, moeilijkheidsGraad);
    }
    
}
