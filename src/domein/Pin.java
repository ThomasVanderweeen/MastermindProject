/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author ThomasV
 */
public abstract class Pin {
    private final String kleur;

    public Pin(String kleur) {
        this.kleur = kleur;
    }

    public String getKleur() {
        return kleur;
    }
    
    public abstract void geldigeKleur(String kleur);
}
