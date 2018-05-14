/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.SpelerMapper;
import  exceptions.AanmeldException;
import java.util.List;

/**
 *
 * @author Groep 77
 */
public class SpelerRepository {
    
    private final SpelerMapper mapper;
    
    /**
     * deze constructor maakt een nieuwe SpelerRepository aan
     * en maakt ook een instantie van SpelerMapper aan 
     *
     * @author Ferre
     */
    public SpelerRepository() {
        this.mapper = new SpelerMapper();
    }
    
    /*Vrij robuust?!?!*/
    /**
     * bestaatSpeler controleerd of de speler met die naam al bestaat
     * 
     * @see spelerBestaat
     * @author Ferre
     * @param naam
     * @return boolean
     */
    public boolean bestaatSpeler(String naam){
        return this.mapper.spelerBestaat(naam);
    }
    /**
     * geefSpeler geeft een speler object terug met de naam en het wachtwoord
     * dat werd meegegeven
     *
     * @see geefSpeler
     * @throws AanmeldException
     * @author Ferre
     * @param wachtwoord
     * @param naam
     * @return Speler
     */
    public Speler geefSpeler(String naam, String wachtwoord){
        Speler sp = this.mapper.geefSpeler(naam,wachtwoord);
        if(sp==null)
            throw new AanmeldException();
        else
            return sp;
    }
    /**
     * voegSpelerToe voegt het speler object toe aan de database
     *
     * @see voegToe
     * @author Ferre
     * @param sp
     */
    public void voegSpelerToe(Speler sp){
        mapper.voegToe(sp);
    }
    /**
     * updateScore geeft de meegegeven speler een win 
     * in de meegegeven moeilijkheidsgraad
     *
     * @see updateScore
     * @throws IllegalArgumentException
     * @author Ferre
     * @param sp
     * @param moeilijkheidsGraad
     */
    public void updateScore(Speler sp, int moeilijkheidsGraad){
        mapper.updateScore(sp, moeilijkheidsGraad);
    }
    /**
     * geefBeschikbareSpelers geeft een lijst van spelers terug 
     * waarvoor de geselecteerde moeilijkheidsgraad beschikbaar is
     *
     * @see geefBeschikbareSpelersUitdaging
     * @author Ferre
     * @param moeilijkheidsGraad
     * @param spelerNaam
     * @return List
     */
    public List<String[]> geefBeschikbareSpelers(int moeilijkheidsGraad,String spelerNaam){
        return mapper.geefBeschikbareSpelersUitdaging(moeilijkheidsGraad,spelerNaam);
    }
    
    
    /**
     * geefKlassement geeft het een lijst van spelers
     * terug volgens de moeilijkheidsgraad
     *
     * @see geefKlassement
     * @throws IllegalArgumentException
     * @author Ferre
     * @param graad
     * @return List
     */
    public List<String[]> geefKlassement(int graad) {
        return mapper.geefKlassement(graad);
    }
    
    public Speler geefSpelerNaam(String gebruikersnaam){
        return mapper.geefSpelerNaam(gebruikersnaam);
    }
    
    public void slaScoreMakkelijkOp(int score, Speler speler){
        mapper.SlaScoreMakkelijkOp(score, speler);
    }
    
    public void slaScoreGemiddeldOp(int score, Speler speler){
        mapper.SlaScoreMakkelijkOp(score, speler);
    }
    
    public void slaScoreMoeilijkOp(int score, Speler speler){
        mapper.SlaScoreMakkelijkOp(score, speler);
    }
}
