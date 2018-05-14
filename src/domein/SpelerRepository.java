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
     * @see SpelerMapper#spelerBestaat(java.lang.String) spelerBestaat
     * @author Michiel S.
     * @param naam String
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
     * @throws AanmeldException AanmeldException
     * @author Michiel S.
     * @param wachtwoord String
     * @param naam String
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
     * @see SpelerMapper#voegToe(domein.Speler) voegToe
     * @author  Michiel S.
     * @param sp Speler
     */
    public void voegSpelerToe(Speler sp){
        mapper.voegToe(sp);
    }
    /**
     * updateScore geeft de meegegeven speler een win 
     * in de meegegeven moeilijkheidsgraad
     *
     * @see updateScore
     * @throws IllegalArgumentException IllegalArgumentException
     * @author  Michiel S.
     * @param sp Speler
     * @param moeilijkheidsGraad Integer
     */
    public void updateScore(Speler sp, int moeilijkheidsGraad){
        mapper.updateScore(sp, moeilijkheidsGraad);
    }
    /**
     * geefBeschikbareSpelers geeft een lijst van spelers terug 
     * waarvoor de geselecteerde moeilijkheidsgraad beschikbaar is
     *
     * @see SpelerMapper#geefBeschikbareSpelersUitdaging(int, java.lang.String) geefBeschikbareSpelersUitdaging
     * @author Michiel S.
     * @param moeilijkheidsGraad Integer
     * @param spelerNaam String
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
     * @throws IllegalArgumentException IllegalArgumentException
     * @author Ferre
     * @param graad Integer
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
