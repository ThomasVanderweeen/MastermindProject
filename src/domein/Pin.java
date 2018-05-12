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
public abstract class Pin {
    private final String kleur;

    /**
     * Constructor voor een pin object.
     * 
     * @author Thomas
     * @param kleur kleur van de pin.
     */
    public Pin(String kleur) {
        this.kleur = kleur;
    }
    
    /**
     * Geeft de kleur van een pin object als returnwaarde. 
     * 
     * @author Thomas
     * @return String
     */
    public String getKleur() {
        return kleur;
    }
    
    /**
     * Abstracte methode die wordt overschreven in de subclasses.
     * 
     * @author Thomas
     * @param kleur kleur die gecontrolleerd wordt.
     */
    protected abstract void bepaalGeldigeKleur(String kleur);
}
