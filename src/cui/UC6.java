/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author ThomasV
 */
public class UC6 {
    private final ResourceBundle r;
    private final DomeinController dc;
    private Scanner sc;
    protected static UC_Algemeen ua;

    public UC6(DomeinController dc, ResourceBundle r) {
        this.r = r;
        this.dc = dc;
    }
    
    public void main(){
        toonUitdagingen();
    }
    
    private void toonUitdagingen(){
        String[][] uitdagingen = dc.geefLijstUitdagingen();
        if(uitdagingen.length == 0){
            System.out.println("Je hebt momenteel geen uitdagingen");
            toonKeuzes();
        }
        else{
            for(int i = 0; i < uitdagingen.length; i++){
                System.out.printf("%d)", i+1);
                for(int j = 0; j < uitdagingen[i].length; j++){
                    System.out.printf("%s ", uitdagingen[i][j]);
                }
                System.out.println();
            }
            toonOpties();
        } 
    }  

    private void toonKeuzes() {
        int keuze;
        do{
            System.out.printf("%s%n%s%n","1)"+r.getString("TerugNaarMenu"),"2)"+r.getString("sluitAf"));
            System.out.print(r.getString("keuzeInvoer"));
            Scanner scanner = new Scanner(System.in);
            keuze = scanner.nextInt();
            
            switch(keuze){
                case 1:
                    UC1 uc1 = new UC1();
                    uc1.Start();
                    break;
                case 2:
                    System.out.println(r.getString("afsluiten"));
                    break;
            }
        }while(keuze<1||keuze>2);
 
    }

    private void toonOpties() {
        String[][] uitdagingen = dc.geefLijstUitdagingen();
        int keuze;
        do{
            System.out.printf("%s%n", r.getString("UitdagingKeuze"));
            Scanner scanner = new Scanner(System.in);
            keuze = scanner.nextInt();
            
            for(int i = 0; i < uitdagingen.length; i++){
                if(keuze == i + 1){
                    int ID = 0;
                }
            }
        }while (keuze <1|| keuze > uitdagingen.length);
        
        
    }
}
