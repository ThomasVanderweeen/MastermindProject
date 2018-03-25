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
    private final UC1 uc1;

    public UC4(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
        this.uc1 = new UC1(dc, r);
    }

    private void laadSpellen() {
        String[][] spellen = this.dc.geefOpgeslagenSpellen();
        String res = String.format("%10s %10s", r.getString("spelNaam"),r.getString("moeilijkheidsGraad"));
        for (String[] rij : spellen) {
            res +=String.format("%n%10s", rij[0]);
            switch(rij[1]){
                case 1: res+=String.format("%10s",r.getString("makkelijk"));
                    break;
                case 2: res+=String.format("%10s",r.getString("gemiddeld"));
                    break;
                case 3: res+=String.format("%10s",r.getString("moeilijk"));
                    break;
            }
            
        }
        System.out.printf(res);
    }
    
}
