/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author Gebruiker
 */
public class UC4
{

    private ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    private final UC3 uc3;

    public UC4(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
        this.uc3 = new UC3(dc,r);
    }
    
    /*private int geefNaamIn(){
    }*/
    
    /*start methode: -laadSpellen geef ze weer, geef naam in, laadSpelIn, als 
    er een spel ingeladen in + uc3 start methode aan roepen*/

    private void laadSpellen() {
        String[][] spellen = this.dc.geefOpgeslagenSpellen();
        String res = String.format("%10s %10s", r.getString("spelNaam"),r.getString("moeilijkheidsGraad"));
        for (String[] rij : spellen) {
            res +=String.format("%n%10s", rij[0]);
            switch(rij[1]){
                case "1": res+=String.format("%10s",r.getString("makkelijk"));
                    break;
                case "2": res+=String.format("%10s",r.getString("gemiddeld"));
                    break;
                case "3": res+=String.format("%10s",r.getString("moeilijk"));
                    break;
            }
            
        }
        System.out.printf(res);
    }
    
    /*laadSpel In*/
}
