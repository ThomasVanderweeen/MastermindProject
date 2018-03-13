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
    private String kleur;
    private final String[] geldigeKleuren;

    public Pin(String kleur) {
        this.kleur = kleur;
        this.geldigeKleuren = new String[0];
    }

    public String getKleur() {
        return kleur;
    }
    
    public abstract void geldigePin(Pin pin);
}
