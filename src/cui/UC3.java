/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.InputMismatchException;
import exceptions.OngeldigePogingException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Groep 77
 */
public class UC3 {
    
    private ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    
    public UC3(DomeinController dc, ResourceBundle r){
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
    }
    
    public void start(){
        while(!(this.dc.isEindeSpel())){
            geefKleurenWeer();
            speelBeurt();
        }
        if(this.dc.isGewonnen()){
            gewonnen();
        }
    }
    
    private void gewonnen(){
        String res = String.format("%s%n%s",r.getString("gewonnen"),
                r.getString("code"));
        
        String[][] eindsituatie = this.dc.geefEindSituatie();
        
        /*Code kleuren worden weergegeven*/
        for(String kleur:eindsituatie[2]){
            res+=String.format("%10s",kleur);
        }
        
        res+= String.format("%n%s%s%n%s%n%s",r.getString("aantalPogingen")
                ,eindsituatie[1][0],"Je hebt nu "+eindsituatie[0][0]+
                        "aantal sterren in de gekozen moeiljkheidsgraad",
                "Je hebt nog "+eindsituatie[0][1]+" aantal sterren nodig tot de volgende ster");
        
        System.out.println(res);
        
    }

    private void speelBeurt(){
       int keuze = 0;
        
        System.out.printf("%s%n%d%s%n%d%s%n%s",r.getString("keuzes"),
                1,")"+r.getString("slaOp"),2,")"+r.getString("speelBeurt")
                ,r.getString("keuzeInvoer"));
        
        try{
            keuze = geefKeuze(1,2);
            
        }catch(Exception e){
            speelBeurt();
        }
        
        switch(keuze){
            case 1:
                System.err.println(r.getString("nogNietGeimplementeerd"));
                speelBeurt();
                break;
            case 2:
                doePoging();
                break;
            default:
                speelBeurt();
        }
    }
    
    private int geefKeuze(int min,int max){
        int i = 0;
        
        try{
            
            i = this.sc.nextInt();
            if(i>max||i<min){
                System.err.println(r.getString("fouteKeuze"));
                throw new IllegalArgumentException("");
            }
        }catch(InputMismatchException ime){
            
            System.err.println(r.getString("foutGeheelGetal"));
            this.sc.next();
            throw ime;
        }
        
        return i;
    }
    
    private void doePoging(){
        int i=0;
        
        System.out.println("Geef je poging in (getallen gescheiden door -)");
        String pog = this.sc.next();
        List<Integer> cijferPoging = new ArrayList<>();

        try{
            
            for(String str:pog.split("-")){
                cijferPoging.add(controleerPin(str));
                
            }
            
            int[] poging = new int[cijferPoging.size()];
            for(int j:cijferPoging){
                poging[i] = j;
                i++;
            }
            
            this.dc.doePoging(poging);
        }catch(OngeldigePogingException ope){
            System.err.println(r.getString("pogingOngeldig"));
            speelBeurt();
        }catch(IllegalArgumentException iae){
            System.err.println(r.getString("pinKeuzeOngeldig"));
            speelBeurt();
        }
        
        if(!(this.dc.isEindeSpel())){
            geefSpelbordWeer();
        }
    }   
    
    private int controleerPin(String i){
        try{
            if(i.length()>1)
                throw new IllegalArgumentException();
            
            int ret = Integer.parseInt(i);
            if(ret<1||ret>this.dc.geefKleuren().length)
                throw new IllegalArgumentException();
            
            return ret-1;
        }catch(NumberFormatException nfe){
            throw new OngeldigePogingException();
        }
    }
    
    private void geefKleurenWeer(){
        String[] kleuren = this.dc.geefKleuren();
        String res = r.getString("mogelijkheden")+"\n";
        for(int i=1;i<= kleuren.length;i++){
                res+=String.format("%d)%s ",i,r.getString(kleuren[i-1]));
        }
        
        System.out.println(res);
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
