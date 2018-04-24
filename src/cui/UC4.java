
package cui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import exceptions.SpelerHeeftGeenOpgeslagenSpellenException;

/**
 *
 * @author Gebruiker
 */
public class UC4
{

    private final ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    private final UC3 uc3;
    private String[][] spellen;

    public UC4(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
        this.uc3 = new UC3(dc, r);
    }

    private int geefNaamIn() {
        Scanner scan = new Scanner(System.in);
        int keuze=0;
        try {
            System.out.printf("%n%s",r.getString("keuzeInTeLadenSpel"));
            keuze = scan.nextInt();
            
        } catch (InputMismatchException i) {
            System.err.print(r.getString("foutGeheelGetal"));
            scan.nextLine();
        }
        return keuze;
    }

    /*start methode: -laadSpellen geef ze weer, geef naam in, laadSpelIn, als 
    er een spel ingeladen in + uc3 start methode aan roepen*/
    public void start(){
        int keuze; 
        try{
                laadSpellen();
                keuze = geefNaamIn();
                if(keuze>this.spellen.length){
                    throw new IllegalArgumentException("");
                }
                laadSpelIn(keuze);
        }catch(SpelerHeeftGeenOpgeslagenSpellenException she){
            throw she;
        }catch(IllegalArgumentException iae){
            System.err.println(r.getString("probeerOpnieuw"));
            start();
        }
    }
    private void laadSpellen() {
        this.spellen = this.dc.geefOpgeslagenSpellen();
        int index = 0;
        String res = String.format("%25s %20s", r.getString("spelNaam"), r.getString("moeilijkheidsGraad"));
        for (String[] rij : spellen) {
                index++;
                res += String.format("%n%d) %20s", index, rij[0]);
                switch (rij[1]) {
                    case "1":
                        res += String.format("%20s", r.getString("makkelijk"));
                        break;
                    case "2":
                        res += String.format("%20s", r.getString("gemiddeld"));
                        break;
                    case "3":
                        res += String.format("%20s", r.getString("moeilijk"));
                        break;
                }
            
        }
        System.out.printf(res);
    }

    public void laadSpelIn(int keuze){
        String naam = spellen[keuze-1][0];
        this.dc.selecteerSpel(naam);
        UC1.ua.geefSpelbordWeer();
        this.uc3.start();
    }
}
