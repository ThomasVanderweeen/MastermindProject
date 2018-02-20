/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Groep 77
 */
public class DomeinController {
    
    private final SpelerRepository spelerRepository;
    private Speler speler;

    public DomeinController() {
        this.spelerRepository = new SpelerRepository();
    }
    
    public void meldAan(String naam, String wachtwoord){
        
    }
    
    public void registreer(String naam, String wachtwoord, String wachtwoordBevestiging){
        Speler speler = null;
    }   
}
