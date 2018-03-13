package cui;

import java.util.List;
import java.util.ResourceBundle;
import domein.DomeinController;
import java.util.Scanner;
import java.util.InputMismatchException;
import exceptions.NietGenoegGewonnenException;
import java.util.Locale;
/**
 *
 * @author Michiel S.
 */
public class UC2 {
    /*veel dubbel  eventueel static dc en scanner?*/
    private final ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    
    public UC2(ResourceBundle r,DomeinController dc){
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
    }
    
    public void start(){
        toonAantalGewonnen();
        kiesMoeilijkheidsGraad();
        /*dc.geefSpelbord();*/
    }
    
    private void toonAantalGewonnen(){
        List<Integer> lijst = dc.startNieuwSpel();
        System.out.printf("%s%n%s%d%n%s%d%n%s%d%n",r.getString("gewonnenIntro"),
                r.getString("makkelijkGewonnen"),lijst.get(0),
                r.getString("gemiddeldGewonnen"),lijst.get(1),
                r.getString("moeilijkGewonnen"),lijst.get(2));
    }
    
    /*dubbele code*/
    private int geefKeuzeIn(String boodschap){
        System.out.print(boodschap);
        return this.sc.nextInt();
    }
    
    private void kiesMoeilijkheidsGraad(){
        int keuze = 0;
        
        do{
            System.out.printf("%s%n%s%n%s%n%s%n",r.getString("kiesMoeilijkheidsGraad"),
            r.getString("kiesMakkelijk"),r.getString("kiesGemiddeld"),
            r.getString("kiesMoeilijk"));
            
            try{
                keuze = geefKeuzeIn(r.getString("keuzeInvoer"));
                if(keuze<1 || keuze>3)
                    throw new IllegalArgumentException();
            }catch(IllegalArgumentException e){
                System.err.println(r.getString("probeerOpnieuw"));
            }catch(InputMismatchException i){
                System.err.println(r.getString("foutGeheelGetal"));
                sc.next();
                keuze = 0;
            }
            
        }while(keuze<1 || keuze>3);

        try{
            dc.registreerSpel(keuze);
            geefSpelbordWeer();
        }catch(NietGenoegGewonnenException e){
            System.err.println(r.getString("nietGenoegGewonnen"));
            kiesMoeilijkheidsGraad();
        }
    }
    
    private void geefSpelbordWeer(){
        String[][] spelbord = this.dc.geefSpelBord();
        String[] code = this.dc.geefCode();
        String res = String.format("%S%n%S","spelbord:","code:");
       
        for(String codepin:code){
            res+= String.format("%10s",codepin);
        }
        
        res += String.format("%n%S%n","spelbord:");
        
        for (String[] rij:spelbord){
            res+="[";
            for(String pin:rij){
                res+=String.format("%10s",pin);
            }
            res+=String.format("%s%n","]");
        }
        
        System.out.println(res);
    }
    
}
