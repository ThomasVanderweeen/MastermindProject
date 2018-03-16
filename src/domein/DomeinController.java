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

    public String [][] geefSpelBord(){
        String[][] spelbord = new String[12][];
        int rijnr = 0,lengte,inner=0;
        
        Spelbord sp = this.spel.getSpelBord();
        Rij[] rijen = sp.getRijen();
        
        for(Rij r:rijen){
            lengte = r.getPoging().size();
            if(r.heeftEvaluatie())
                lengte += r.getEvaluatie().size();
            spelbord[rijnr] = new String[lengte];
            
            for(CodePin cp : r.getPoging()){
                if(cp ==null)
                    spelbord[rijnr][inner] = "leeg";
                else
                    spelbord[rijnr][inner] = cp.getKleur();
                inner++;
            }
            
            if(r.heeftEvaluatie()){
                for(EvaluatiePin ep: r.getEvaluatie()){
                    spelbord[rijnr][inner] = ep.getKleur();
                     inner++;
                }
            }
            
            inner = 0;
            rijnr++;
        }
     
        return spelbord;
    }
    
    public String[] geefCode(){
        
        Spelbord sp = this.spel.getSpelBord();
        List<CodePin> code =  sp.getCode();
        
        String[] res = new String[code.size()];
        int teller = 0;
        
        for(CodePin cp: code){
            res[teller] = cp.getKleur();
            teller++;
        }
        
        return res;
    }

    
    
    public String[] geefKleuren(){
        return this.spel.geefGeldigeKleuren();
    }
    
    public void doePoging(int[] poging){
        this.spel.doePoging(poging);
    }
    
    public boolean isEindeSpel(){
        return this.spel.bepaalEindeSpel();
    }
}
