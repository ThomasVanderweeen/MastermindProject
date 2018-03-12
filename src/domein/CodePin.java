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
public class CodePin {
    private String kleur;
    private int positie; 

    public CodePin(String kleur, int positie) {
        this.kleur = kleur;
        this.positie = positie;
    }

    public String getKleur() {
        return kleur;
    }

    public int getPositie() {
        return positie;
    }
    
    
}
