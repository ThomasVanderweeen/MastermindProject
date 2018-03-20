package cui;

import java.util.List;
import java.util.ResourceBundle;
import domein.DomeinController;
import java.util.Scanner;
import java.util.InputMismatchException;
import exceptions.NietGenoegGewonnenException;
/**
 *
 * @author Groep 77
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
        UC3 uc3 = new UC3(this.dc,this.r);
        uc3.start();
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
        String res = String.format("%S",
                r.getString("code"));
       
        for(String codepin:code){
            res+= String.format("%10s",r.getString(codepin));
        }
        
        
        res += String.format("%n%S%n",r.getString("spelbord"));
        for (String[] rij:spelbord){
            res+=String.format("%S%s",r.getString("rij"),"[");
            if(rij.length<6){
                
                for(String pin:rij){
                    res+=String.format("%10s",r.getString(pin));
                }

            }else{
                int lengte = rij.length;
                
                for(int i=0;i<(lengte/2);i++){
                    res+=String.format("%10s",r.getString(rij[i]));
                }
                
                res+=String.format("%s%n%S%s","]",r.getString("evaluatie"),"[");
                for(int i=lengte/2;i<lengte;i++){
                    res+=String.format("%10s", r.getString(rij[i]));
                }
                
            }
           res+=String.format("%s%n","]");
        }
        
        System.out.println(res);
    }
    
    
}
