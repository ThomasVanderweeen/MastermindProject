/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;
import exceptions.AanmeldException;
import exceptions.SpelerBestaatAlException;
/**
 *
 * @author Groep 77
 */
public class DomeinController {
    
    private final SpelerRepository spelerRepository;
    private Speler speler;

    public DomeinController() {
        this.spelerRepository = new SpelerRepository();
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
    public Speler meldAan(String naam, String wachtwoord){
        
        boolean bestaat = spelerRepository.spelerBestaat(naam);
        System.out.println(bestaat);
        if(bestaat){
           this.speler = spelerRepository.controleerAanmelden(naam, wachtwoord);
        }else{
            throw new AanmeldException();
        }
        
        return this.speler;
    }
    
    /*
    Ook nog geen exception geworpen indien speler wel al bestaat nog implementeren HOE?!
    */
    public void registreer(String naam, String wachtwoord, String wachtwoordBevestiging){
        boolean bestaat = spelerRepository.spelerBestaat(naam);
        try{
            if(!bestaat){
                this.speler = new Speler(naam,wachtwoord,wachtwoordBevestiging);
                this.spelerRepository.voegSpelerToe(speler);
            }   
            else{
                throw new SpelerBestaatAlException();
            }
        }
        catch(SpelerBestaatAlException e){
            System.err.println("Speler al bekend in systeem.");
        }
    }   
    
    
}
