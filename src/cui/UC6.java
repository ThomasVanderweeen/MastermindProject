/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import exceptions.GeenOpenstaandeUitdagingException;
import exceptions.HeeftLopendeUitdagingException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author ThomasV
 */
public class UC6 {
    private final ResourceBundle r;
    private final DomeinController dc;
    private Scanner sc;
    protected static UC_Algemeen ua;
    private UC1 uc1;
    private UC5 uc5;
    private UC3 uc3;

    public UC6(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
        this.uc1 = new UC1(dc,r);
        this.uc5 = new UC5(dc,r);
        this.uc3 = new UC3(dc,r);
    }
    
    public void main(){
        toonUitdagingen();
    }
    
    private void toonUitdagingen(){
        try{
            String[][] uitdagingen = dc.geefLijstUitdagingen();    
            String res = String.format("%5s%30s%25s%n","",r.getString("naamTegenstander"),
                    r.getString("moeilijkheidsGraad"));
            String[] moeilijkheidsgraden = {r.getString("makkelijk"),r.getString("gemiddeld"),r.getString("moeilijk")};
            
            for(int i = 0; i < uitdagingen.length; i++){
                
                res += String.format("%4d)%30s%25s%n", i+1,uitdagingen[i][1],
                        moeilijkheidsgraden[Integer.valueOf(uitdagingen[i][2])-1]);
            }
            
            System.out.println(res);
            
            int keuze=0;
            System.out.printf("%s", r.getString("UitdagingKeuze"));

            try{
                keuze = UC1.ua.geefKeuze(1, uitdagingen.length);
            }catch(Exception e){
                toonUitdagingen();
            }

            int ID = Integer.valueOf(uitdagingen[keuze-1][0]);
            String tegenstander = uitdagingen[keuze-1][1];
            toonOpties(ID,tegenstander);

        }catch(GeenOpenstaandeUitdagingException goue){
            System.err.println(r.getString("geenBeschikbareUitdaging"));
            uc1.toonMogelijkheden();
        }catch(HeeftLopendeUitdagingException hlue){
            uc5.heeftLopendeUitdaging();
        }
         
    }  


    private void toonOpties(int ID,String tegenstander) {
        int keuze =0 ;
        
        System.out.printf("%s%n1) %s%n2) %s%n3) %s%n%s",r.getString("uitdagingIntro"),
                r.getString("startUitdaging"),r.getString("verwijderUitdaging"),
                r.getString("TerugNaarMenu"),r.getString("keuzeInvoer"));
        
        try{
            keuze = UC1.ua.geefKeuze(1, 3);
        }catch(Exception e){
            toonOpties(ID,tegenstander);
        }
        
        switch(keuze){
            case 3:
                uc1.toonMogelijkheden();
                break;
            case 2:
                this.dc.verwijderUitdaging(ID);
                System.out.println(r.getString("uitdagingVerwijderd"));
                this.uc1.toonMogelijkheden();
                break;
            case 1:
                this.dc.laadUitdaging(ID, tegenstander);
                UC1.ua.geefSpelbordWeer();
                uc3.start();
                break;
            default:
                System.out.println("nog niet geimp");
                break;
        }
    }
}
