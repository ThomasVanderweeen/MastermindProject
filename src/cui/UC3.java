/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.CodePin;
import domein.DomeinController;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author ThomasV
 */
public class UC3 {
    
    private ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc = new Scanner(System.in);
    
    public UC3(){
        this.dc = new DomeinController();
    }
    
    public void start(){
        doePoging();
    }
    
    
    public void speelBeurt(){
        
    }
    
    private CodePin[] doePoging(){
        CodePin[] poging = new CodePin[4];
        String kleur;
        System.out.println();
        for(int i = 0; i < 4; i++){
            System.out.printf("Geef de kleur van de %de code pin: ", i+1);
            kleur = sc.next();
            poging[i] = new CodePin(kleur);
        }
        return poging;
    }   
}
