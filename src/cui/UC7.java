/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author ThomasV
 */
public class UC7
{

    private final ResourceBundle r;
    private final DomeinController dc;
    private Scanner sc;
    protected static UC_Algemeen ua;
    private UC1 uc1;
    private UC5 uc5;
    private UC6 uc6;
    private UC3 uc3;

    /**
     * constructor waarbij 2 parameters al zijn meegegeven
     * DomeinController en ResourceBundle worden ingesteld, er wordt ook een scanner-, een UC1-, een UC3-, en een UC5 object aangemaakt
     * @param dc DomeinController
     * @param r ResourceBundle
     */
    public UC7(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
        this.uc1 = new UC1(dc, r);
        this.uc5 = new UC5(dc, r);
        this.uc3 = new UC3(dc, r);
    }

    /**
     * bevat het verloop van UC7
     */
    public void main() {
        geefOpties();
    }

    /**
     * Toont het scorebord voor een bepaalde moeilijkheidsgraad.
     * 
     * @see #dc.toonKlassement(graad)
     * @param graad 
     */
    public void toonScorebord(int graad) {

        List<String[]> spelers = new ArrayList<String[]>();
        spelers = dc.geefKlassement(graad);

        int i = 1;
        String res = "";
        switch(graad){
            case 1: res+= String.format("%s%n",r.getString("makkelijkGewonnen"));
                    break;
            case 2: res+= String.format("%s%n",r.getString("gemiddeldGewonnen"));
                    break; 
            case 3: res+= String.format("%s%n",r.getString("moeilijkGewonnen"));
                    break;
        }
        res+= String.format("%-10s%-40s%-20s%n",r.getString("rang"),r.getString("naam"),r.getString("aantalGewonnen"));
        for (String[] speler : spelers) {
            String naam = speler[0];
            int gewonnen;
            if(speler[1] == null){
                gewonnen = 0;
            }
            else{
                gewonnen = Integer.parseInt(speler[1]);
            }
            res+= String.format("%-10s%-40s%-20s%n",i, naam, gewonnen);
            i++;

            
            
        }
        System.out.println(res);
        geefOpties();
    }

    /**
     * Laat de speler kiezen voor welke moeilijkheidsgraad ze een het scorebord willen zien
     */
    public void geefOpties() {
        int keuze = 0;
        Scanner sc = new Scanner(System.in);
        System.out.printf("%s%n", r.getString("scoreMakkelijk"));
        System.out.printf("%s%n", r.getString("scoreGemiddeld"));
        System.out.printf("%s%n", r.getString("scoreMoeilijk"));
        System.out.printf("%s", r.getString("keuzeInvoer"));
        keuze = sc.nextInt();

        toonScorebord(keuze);
            
        
    }

}
