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
import exceptions.SpelerBestaatAlException;
import exceptions.SpelerHeeftGeenOpgeslagenSpellenException;
import java.util.InputMismatchException;
/**
 *
 * @author Groep 77
 */
public class UC1 {
    private final DomeinController dc;
    private final Scanner sc = new Scanner(System.in);
    private ResourceBundle r;
    protected static UC_Algemeen ua;
     /* Hier gaat de applicatie komen veronderstel ik*/
    public UC1(){
        this.dc = new DomeinController();
    }
    
    public UC1(DomeinController dc, ResourceBundle r){
        this.dc = dc;
        this.r = r;
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
                ua = new UC_Algemeen(this.dc,r);

            } catch (IllegalArgumentException e) {
                System.err.println("Fout nummer, wrong number, numéro incorrect");
            }catch (InputMismatchException i){
                System.out.printf("Je moet een geheel getal in geven,You have to enter a whole number,\n"
                        + "votre choix doit être un nombre entier\n");
                sc.next();
            }
        } while (!taalGeselecteerd);

    }
    
    private void toonMenu(){
        int keuze = 0;
        
   
            do{
                try{
                System.out.printf("%s%n%s%n%s%n%s%n",r.getString("welkom"),r.getString("meldAan") ,r.getString("registreer"),"3)"+r.getString("sluitAf"));
//                keuze = geefKeuzeIn(r.getString("keuzeInvoer"));
                System.out.print(r.getString("keuzeInvoer"));
                keuze = sc.nextInt();
                if(keuze < 1 || keuze > 3)
                    throw new IllegalArgumentException();
                }
                catch(IllegalArgumentException e){
                    System.err.println(r.getString("fouteKeuze"));
                }catch (InputMismatchException i){
                    System.err.println(r.getString("foutGeheelGetal"));
                    sc.next();
           }
            }while(keuze<1||keuze>3);
       
        
            switch(keuze){
                case 1:
                    try{
                        meldAan();
                        System.out.println(r.getString("meldAanWelkom")+ " " + dc.geefSpelerNaam());
                        toonMogelijkheden();
                    }/*exception word veel hergebruikt misschien aparte klasse? of verplaatsen naar geefKeuze in?*/
                    catch(ServerOnbereikbaarException e){
                        System.err.println(r.getString("serverFout"));
                        toonMenu();
                    }catch(AanmeldException e){
                        System.err.println(r.getString("probeerOpnieuw"));
                        /*Nodig voor correct weergeven van tekst*/
                        System.out.print("");
                        toonMenu();
                    }
                    break;
                case 2:
                    try{
                    registreer();
                    System.out.println(r.getString("registreerWelkom")+ " " +dc.geefSpelerNaam());
                    toonMogelijkheden();
                    }
                    catch(ServerOnbereikbaarException e){
                        System.err.println(r.getString("serverFout"));
                        toonMenu();
                    }catch(IllegalArgumentException iae){
                        System.err.println(r.getString("wachtwoordFout"));
                        toonMenu();
                    }catch(SpelerBestaatAlException spae){
                        System.err.println(r.getString("alBekend"));
                        toonMenu();
                    }

                    break;
                case 3:
                    System.out.println(r.getString("afsluiten"));
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
    
   public void toonMogelijkheden(){
       int keuze=0;
       
       do{
           try{ 
            System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n",r.getString("startEenSpel"),
               r.getString("laadSpel"),r.getString("daagIemandUit"),
               r.getString("wieDaagtJouUit"),r.getString("toonKlassement"),
               "0)"+r.getString("sluitAf"));
            System.out.print(r.getString("keuzeInvoer"));
            keuze = this.sc.nextInt();
            
            if(keuze<0 || keuze>5)
                throw new IllegalArgumentException();
           }catch(IllegalArgumentException e){
               System.err.println(r.getString("fouteKeuze"));
           }catch (InputMismatchException i){
               System.err.println(r.getString("foutGeheelGetal"));
               sc.next();
           }
       }while(keuze<0 || keuze>5);
       
       switch(keuze){
           case 0:
               System.out.println(r.getString("afsluiten"));
               System.exit(0);
               break;
           case 1:
               UC2 uc2 = new UC2(r,dc);
               uc2.start();
               break;
           case 2: 
               UC4 uc4 = new UC4(dc,r);
               try{
               uc4.start();
               }catch(SpelerHeeftGeenOpgeslagenSpellenException she){
                   System.err.println(r.getString("geenOpslag"));
                   toonMogelijkheden();
               }
               break;
           default:
               System.err.println(r.getString("nogNietGeimplementeerd"));
               toonMogelijkheden();
               break;
       }
       
   }
}
