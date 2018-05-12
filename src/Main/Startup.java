package Main;

import cui.StartCui;
import gui.ScreenController;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Start oftewel de cui of gui op afhankelijk van de keuze
 * @author Michiel S.
 */
public class Startup {
    private Scanner sc;
    /**
     * Start oftewel de gui of cui op en geeft een keuze
     * @param args String[]
     * @see #maakKeuze()  maakKeuze
     * @see #start(int, java.lang.String[])  Start
     * @author Michiel S.
     */
    public static void main(String[] args) {
        Startup app = new Startup();
        int keuze = app.maakKeuze();
        app.start(keuze,args);
    }
    
    /**
     * maak een keuze tussen twee opties (cui/gui) vangt ook InputMismatchException 
     * en IllegalArgumentException 
     * @author Michiel S.
     * @return Integer
     */
    private int maakKeuze(){
        sc = new Scanner(System.in);
        int keuze = 0;
        
        do{
            System.out.print("Geef 1 in voor gui, geef 2 in voor cui\nEnter 1"
                    + " for gui, enter 2 for cui\n"+"Entrez 1 pour gui, entrez 2 pour cui: ");
            
            try{
                keuze = sc.nextInt();
                if(keuze>2||keuze<1)
                    throw new IllegalArgumentException();
            }catch(InputMismatchException ime){
                this.sc.nextLine();
                System.err.println(("Je moet een geheel getal in geven,\nYou have to enter a whole number,\n"
                        + "votre choix doit être un nombre entier\n"));
                keuze = 0;
            }catch(IllegalArgumentException iae){
                System.err.println("Fout nummer, wrong number, numéro incorrect");
            }
            
        }while(keuze<1||keuze>2);
    
        return keuze;
    }
    
    /**
     * Start oftewel Cui of Gui op
     * @param keuze Integer
     * @param args String[]
     * @see cui.StartCui#main(java.lang.String[])  StartCui
     * @see gui.ScreenController#main(java.lang.String[])  Main
     * @author Michiel S.
     */
    private void start(int keuze,String[] args){
        switch(keuze){
            case 1:
                ScreenController.main(args);
                break;
            case 2:
                StartCui.main(args);
                break;
        }
    }

}
