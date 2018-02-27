package cui;

import domein.DomeinController;
import domein.Speler;
import java.util.Scanner;

/**
 *
 * @author Michiel S.
 */
public class NewMain {
        private final static DomeinController dc = new DomeinController();
        private final Scanner sc = new Scanner(System.in);
 /*main klasse voornamelijk gebruikt om te debuggen*/
 /*Hoe werkt Resources was leuk geweest als iemand die kon impelementeren na afspraak. ;) */
    public static void main(String[] args) {
        NewMain main = new NewMain();
        int keuze = 0;
        
        do{
            System.out.printf("%s%n%s%n%s%n","Welkom tot de mastermind applicatie!","1)Meld aan","2)Registreer");
            keuze = main.geefKeuzeIn("Geef u keuze in: ");
        }while(keuze<1||keuze>2);
       
        switch(keuze){
            case 1:
                main.meldAan();
                System.out.println("Welkom terug "+dc.geefSpelerNaam());
                break;
            case 2:
                main.registreer();
                System.out.println("Welkom "+dc.geefSpelerNaam());
                break;
        }
    }
    
    private int geefKeuzeIn(String boodschap){
        System.out.print(boodschap);
        return this.sc.nextInt();
    }
    
    private void meldAan(){
        System.out.print("Geef uw spelernaam in: ");
        String naam = this.sc.next();
        
        System.out.print("Geef uw wachtwoord in: ");
        String wachtwoord = this.sc.next();
        
        dc.meldAan(naam, wachtwoord);
   }
    
        
    private void registreer(){
        System.out.print("Geef een spelernaam in: ");
        String naam = this.sc.next();
        
        System.out.print("Geef een wachtwoord in (bevat drie cijfers van voor, \nvervolgens zes karakters en drie cijfers achteraan): ");
        String wachtwoord = this.sc.next();
        
        System.out.print("Bevestig u wachtwoord: ");
        String wachtwoordBevestiging = this.sc.next();
        
        dc.registreer(naam, wachtwoord,wachtwoordBevestiging);
   }
    

}



