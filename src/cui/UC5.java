package cui;

import java.util.ResourceBundle;
import domein.DomeinController;
import exceptions.NiemandBeschikbaarVoorUitdagingException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Michiel S.
 */
public class UC5 {
    private final ResourceBundle r;
    private final DomeinController dc;
    private final UC1 uc1;
    private final Scanner sc;
    
    public UC5(DomeinController dc, ResourceBundle r){
        this.r = r;
        this.dc = dc;
        this.uc1 = new UC1(dc,r);
        this.sc = new Scanner(System.in);
    }
    
    public void main(){
        geefBeschikbareMoeilijkheidsGraden();
    }
    
    private void geefBeschikbareMoeilijkheidsGraden(){
        List<String[]> mogelijkheden = this.dc.geefMoeilijkheidsGraden();
        int i = 0;
        
        String res =String.format("%s%n%20s%25s%n", this.r.getString("uitdagingMogelijkhedenTekst")
                ,this.r.getString("moeilijkheidsGraad"),
                this.r.getString("aantalGewonnen"));
        
        for(String[] moeilijkheid: mogelijkheden){
            res += String.format((i+1)+")%18s%25s%n", r.getString(moeilijkheid[0]),
                    moeilijkheid[1]);
            i++;
        }
        
        res += this.r.getString("keuzeInvoer");
        System.out.print(res);
        
        try{
            int keuze = UC1.ua.geefKeuze(1,mogelijkheden.size());
            kiesSpelerOmUitTeDagen(keuze);
        }catch(Exception e){
            geefBeschikbareMoeilijkheidsGraden();
        }

    }
    
    private void kiesSpelerOmUitTeDagen(int moeilijkheidsGraad){
        int spelernummer = 1;
                
        String res = String.format("%s%n%50s%25s%n",r.getString("uitdagersIntro"),
                r.getString("gebruikersNaam"),
                this.r.getString("aantalGewonnen"));
         
        try{
            
            List<String[]> spelers = this.dc.selecteerMoeilijkheidsGraadUitdaging(moeilijkheidsGraad);
            
            for(String[] speler:spelers){
                res += String.format(spelernummer+")%"+((50-spelernummer/10)-2)+"s%20s%n", speler[0],
                        (speler[1]==null?"0":speler[1]));
                spelernummer++;
            }
            
            res+= r.getString("keuzeInvoer");
            
            System.out.print(res);
 
            try{
               int keuze = UC1.ua.geefKeuze(1, spelers.size());
                System.out.println(spelers.get(keuze-1)[0]);
            }catch(Exception e){
                kiesSpelerOmUitTeDagen(moeilijkheidsGraad);
            }
        }catch(NiemandBeschikbaarVoorUitdagingException nbvue){
            System.err.println(r.getString("geenUitdagersException"));
            this.uc1.toonMogelijkheden();
        }

    }
    
}
