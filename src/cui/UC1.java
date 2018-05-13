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
 * Functionaliteit UC1 zit hier geimplementeerd
 * @author Groep 77
 */
public class UC1 {
    private final DomeinController dc;
    private final Scanner sc = new Scanner(System.in);
    private ResourceBundle r;
    protected static UC_Algemeen ua;
    /**
     * Constructor maakt ook DomeinController instantie aan
     * @see domein.DomeinController#DomeinController()  domeinController
     * @author Ferre
     */
    public UC1(){
        this.dc = new DomeinController();
    }
    
    /**
     * constructor waarbij beide parameters al zijn meegegeven
     * DomeinController en ResourceBundle worden ingesteld
     * @param dc DomeinController
     * @param r ResourceBundle
     * @author Ferre
     */
    public UC1(DomeinController dc, ResourceBundle r){
        this.dc = dc;
        this.r = r;
    }
    
    /**
     * hoofd verloop van de applicatie zit hierin vervat
     * @author Michiel S.
     * @see #taalSelectie() taalSelectie;
     * @see #toonMenu() toonMenu
     */
    public void Start(){
        taalSelectie();
        toonMenu();
    }
    
    /**
     * vraagt taal en stelt de resource bundle in vangt llegalArgumentException
     * en InputMismatchException op.
     * @author Ferre
     * 
     */
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
                System.out.print("Je moet een geheel getal in geven,You have to enter a whole number,\n"
                        + "votre choix doit être un nombre entier\n");
                sc.next();
            }
        } while (!taalGeselecteerd);

    }
    
    /**
     * dient voor het registreren en aanmalden. Vangt ook alle mogelijke exceptions op uit deze acties.
     * @author Michiel S.
     * @see #meldAan() meldAan
     * @see #toonMogelijkheden() toonMogelijkheden
     * @see #toonMenu() toonMenus
     */
    private void toonMenu(){
        int keuze = 0;
        
   
            do{
                System.out.printf("%s%n%s%n%s%n%s%n",r.getString("welkom"),"1)"+r.getString("meldAan") ,"2)"+r.getString("registreer"),"3)"+r.getString("sluitAf"));
//                keuze = geefKeuzeIn(r.getString("keuzeInvoer"));
                System.out.print(r.getString("keuzeInvoer"));
                try{
                    keuze = ua.geefKeuze(1, 3);
                }catch(Exception e){
                    toonMenu();
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
    
    /**
     * Vraagt naar de keuze
     * @author Thomas
     * @param boodschap String
     * @return Integer
     */
    private int geefKeuzeIn(String boodschap){
        System.out.print(boodschap);
        return this.sc.nextInt();
    }
    
    /**
     * meld de gebruiker aan doormiddel van de domeinController
     * @see domein.DomeinController#meldAan(java.lang.String, java.lang.String) meldAan
     * @author Ferre
     */
    private void meldAan(){
        System.out.print(r.getString("naamInvoer"));
        String naam = this.sc.next();
        
        System.out.print(r.getString("wachtwoordInvoer"));
        String wachtwoord = this.sc.next();
        
        dc.meldAan(naam, wachtwoord);
   }
    
   /**
    * registreert de gebruiker
    * @
    */
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
            System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n",r.getString("startEenSpel"),
               r.getString("laadSpel"),r.getString("daagIemandUit"),
               r.getString("wieDaagtJouUit"),r.getString("toonKlassement"),
               "0)"+r.getString("sluitAf"));
            System.out.print(r.getString("keuzeInvoer"));
            keuze = ua.geefKeuze(0, 5);
       }while(keuze<0 || keuze>6);
       
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
           case 3:
               UC5 uc5 = new UC5(dc,r);
               uc5.main();
               break;
           case 4:
               UC6 uc6 = new UC6(dc,r);
               uc6.main();
               break;
           case 5:
               UC7 uc7 = new UC7(dc,r);
               uc7.main();
               break;
            default:
               System.err.println(r.getString("nogNietGeimplementeerd"));
               toonMogelijkheden();
               break;
       }
       
   }
}
