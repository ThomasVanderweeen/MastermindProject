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
 * @author Groep 77
 */
public class UC3 {
    
    private ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    
    public UC3(DomeinController dc, ResourceBundle r){
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
    }
    
    public void start(){
        doePoging();
        geefSpelbordWeer();
    }
    
    
    public void speelBeurt(){
        
    }
    
    private void doePoging(){
        int[] poging = new int[4];
        int kleur;
        System.out.println();
        for(int i = 0; i < 4; i++){
            System.out.printf("Geef de kleur van de %de code pin: ", i+1);
            kleur = sc.nextInt();
            poging[i] = kleur;
        }

       this.dc.doePoging(poging);
    }   
    
        
    private void geefSpelbordWeer(){
        String[][] spelbord = this.dc.geefSpelBord();
        String[] code = this.dc.geefCode();
        String res = String.format("%S%n%S","spelbord:","code:");
       
        for(String codepin:code){
            res+= String.format("%10s",r.getString(codepin));
        }
        
        res += String.format("%n%S%n","spelbord:");
        
        for (String[] rij:spelbord){
            res+="[";
            if(rij.length<6){
                for(String pin:rij){
                    res+=String.format("%10s",r.getString(pin));
                }

            }else{
                int lengte = rij.length;
                
                for(int i=0;i<(lengte/2);i++){
                    res+=String.format("%10s",r.getString(rij[i]));
                }
                
                res+=String.format("%s%n%S%n%s","]","evaluatie:","[");
                for(int i=lengte/2;i<lengte;i++){
                    res+=String.format("%10s", r.getString(rij[i]));
                }
                
            }
           res+=String.format("%s%n","]");
        }
        
        System.out.println(res);
    }
}
