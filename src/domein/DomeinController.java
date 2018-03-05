/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import exceptions.AanmeldException;
import exceptions.SpelerBestaatAlException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Groep 77
 */
public class DomeinController {
    
    private static SpelerRepository spelerRepository;
    private Speler speler;
    private Spel spel;
    private static SpelRepository spelRepository;

    public DomeinController() {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
    }
    
    /*
    Er kan nog een exceptie optreden deze functie aanroepen voor speler bestaat.
    */
    public String geefSpelerNaam(){
        return this.speler.getNaam();
    }
    
    /*
        opgebast geef leeg object door indien speler niet bestaat geen exception moet nog gefix worden
    */
    public void meldAan(String naam, String wachtwoord){
        
        boolean bestaat = spelerRepository.bestaatSpeler(naam);
        if(bestaat){
           this.speler = spelerRepository.geefSpeler(naam, wachtwoord);
        }else{
            throw new AanmeldException();
        }
        
        
    }
    
    /*
    Ook nog geen exception geworpen indien speler wel al bestaat nog implementeren HOE?!
    */
    public void registreer(String naam, String wachtwoord, String wachtwoordBevestiging){
        boolean bestaat = spelerRepository.bestaatSpeler(naam);
        try{
            if(!bestaat){
                this.speler = new Speler(naam,wachtwoord,wachtwoordBevestiging);
                spelerRepository.voegSpelerToe(speler);
            }   
            else{
                throw new SpelerBestaatAlException();
            }
        }
        catch(SpelerBestaatAlException e){
            System.err.println("Speler al bekend in systeem.");
        }
    }   
    
    public void registreerSpel(int moeilijkheidsGraad){
        this.spel = new Spel(moeilijkheidsGraad,this.speler);
        spelRepository.voegSpelToe(this.spel);
    }
    
    public List<Integer> startNieuwSpel(){
        List<Integer> gewonnenLijst = new ArrayList<>();
        
        gewonnenLijst.add(this.speler.getAantalGewonnenMakkelijk());
        gewonnenLijst.add(this.speler.getAantalGewonnenGemiddeld());
        gewonnenLijst.add(this.speler.getAantalGewonnenMoeilijk());
        
        return gewonnenLijst;
    }
    
    public String[][][] geefSpelbord(){
       String[][][] spelbordlijst= new String[2][][];
       
       Spelbord spelbord = this.spel.getSpelBord();
       
       List<String> code = spelbord.getCode();
       String[] codear = new String[code.size()];
  
       for(int i=0;i<code.size();i++){
           codear[i]=code.get(i);
       }
      
       List<List<String>>pogingen = spelbord.getPogingen();
       String[][] pogingar = new String[pogingen.size()][pogingen.get(0).size()];
       for(int i=0;i<pogingar.length;i++){
           for(int j=0;j<pogingar[0].length;j++){
               pogingar[i][j] = pogingen.get(i).get(j);
           }
       }
       
       
      spelbordlijst[0][0]=codear;
      return spelbordlijst;
    }
    
}
