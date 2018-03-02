/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cui;
import domein.DomeinController;
import java.util.Scanner;
import java.util.ResourceBundle;
import exceptions.ServerOnbereikbaarException;
import exceptions.AanmeldException;
/**
 *
 * @author ThomasV
 */
public class UC1 {
    private final DomeinController dc;
    private final Scanner sc = new Scanner(System.in);
    private ResourceBundle r;
     /* Hier gaat de applicatie komen veronderstel ik*/
    public UC1(){
        this.dc = new DomeinController();
    }
    
    public void Start(){
        taalSelectie();
        toonMenu();
    }
    
    private void taalSelectie(){
        boolean taalGeselecteerd = false;
        do {
            try {
                System.out.print("Welke taal wilt u?, Quelle langue vous préférez?, Which language \ndo you prefer?");
                int invoerTaal = geefKeuzeIn("(1:Nederlands, 2:English, 3:Français): ");

                if (invoerTaal < 1 || invoerTaal > 3) {
                    throw new IllegalArgumentException();
                }

                switch (invoerTaal) {
                    case 1:
                        r = ResourceBundle.getBundle("resources/Nederlands_ne");

                        break;
                    case 2:
                        r = ResourceBundle.getBundle("resources/English_en");

                        break;
                    case 3:
                        r = ResourceBundle.getBundle("resources/Français_fr");

                        break;
                }

                taalGeselecteerd = true;
                System.out.print(r.getString("taalKeuze"));
                System.out.println();

            } catch (IllegalArgumentException e) {
                System.err.printf("Fout nummer, wrong number, numéro incorrect%n");
            }
        } while (!taalGeselecteerd);
    }
    
    private void toonMenu(){
        int keuze = 0;
        
   
            do{
                try{
                System.out.printf("%s%n%s%n%s%n%s%n",r.getString("welkom"),r.getString("meldAan") ,r.getString("registreer"),r.getString("sluitAf"));
//                keuze = geefKeuzeIn(r.getString("keuzeInvoer"));
                System.out.print(r.getString("keuzeInvoer"));
                keuze = sc.nextInt();
                if(keuze < 1 || keuze > 3)
                    throw new IllegalArgumentException();
                }
                catch(IllegalArgumentException e){
                    System.err.println(r.getString("foute Keuze probeer opnieuw"));
                }
            }while(keuze<1||keuze>3);
       
        
            switch(keuze){
                case 1:
                    try{
                        meldAan();
                        System.out.println(r.getString("meldAanWelkom")+ " " + dc.geefSpelerNaam());
                    }
                    catch(ServerOnbereikbaarException e){
                        System.out.println("Sorry de server is onbereikbaar bent u wel online?");
                        toonMenu();
                    }catch(AanmeldException e){
                        System.out.println("Controleer de gegevens en probeer opnieuw.");
                    }
                    break;
                case 2:
                    try{
                    registreer();
                    System.out.println(r.getString("registreerWelkom")+ " " +dc.geefSpelerNaam());
                    }
                    catch(ServerOnbereikbaarException e){
                        System.out.println("Sorry de server is onbereikbaar bent u wel online?");
                        toonMenu();
                    }

                    break;
                case 3:
                    System.out.println("Bedankt voor het spel uit te proberen! Fijne dag verder!");
                    break;
            }
    }
    
    private int geefKeuzeIn(String boodschap){
        System.out.print(boodschap);
        return this.sc.nextInt();
    }
    
    private void meldAan(){
        System.out.print(r.getString("naamInvoer"));
        String naam = this.sc.next();
        
        System.out.print(r.getString("wachtwoordInvoer"));
        String wachtwoord = this.sc.next();
        
        dc.meldAan(naam, wachtwoord);
   }
    
        
    private void registreer(){
        System.out.print(r.getString("registreerNaam"));
        String naam = this.sc.next();
        
        System.out.print(r.getString("registreerWachtwoord"));
        String wachtwoord = this.sc.next();
        
        System.out.print(r.getString("bevestigWachtwoord"));
        String wachtwoordBevestiging = this.sc.next();
        
        dc.registreer(naam, wachtwoord,wachtwoordBevestiging);
   }
}
