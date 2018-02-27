/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cui;
import domein.DomeinController;
import java.util.Scanner;
/**
 *
 * @author ThomasV
 */
public class StartUp {
    private final DomeinController dc;
    private final Scanner sc = new Scanner(System.in);
     /* Hier gaat de applicatie komen veronderstel ik*/
    public StartUp(){
        dc = new DomeinController();
        int keuze = 0;
        
        do{
            System.out.printf("%s%n%s%n%s%n","Welkom tot de mastermind applicatie!","1)Meld aan,2)Registreer");
            keuze = geefKeuzeIn("Geef u keuze in: ");
        }while(keuze<1||keuze>2);
       
        switch(keuze){
            case 1:
                meldAan();
                System.out.println("Welkom terug "+dc.geefSpelerNaam());
                break;
            case 2:
                registreer();
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
        
        System.out.print("Geef een wachtwoord in (bevat drie cijfers van voor, vervolgens zes karakters en drie cijfers achteraan): ");
        String wachtwoord = this.sc.next();
        
        System.out.print("Bevestig u wachtwoord: ");
        String wachtwoordBevestiging = this.sc.next();
        
        dc.registreer(naam, wachtwoord,wachtwoordBevestiging);
   }
}
