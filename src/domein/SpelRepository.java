package domein;

import java.util.List;
import java.util.ArrayList;
import persistentie.SpelMapper;
import exceptions.SpelNaamNietUniekException;
import exceptions.SpelerHeeftGeenOpgeslagenSpellenException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Groep 77
 */
public class SpelRepository
{

    private List<Spel> spelletjes;
    private final SpelMapper spm;

    /**
     * constructor voor een spelRepository object.
     */
    public SpelRepository() {
        spelletjes = new ArrayList<>();
        this.spm = new SpelMapper();
    }

    /**
     * voegSpelToe voegt een spel toe aan de databank via de spelmapper.
     *
     * @see voegSpelToe
     * @throws Exception(e)
     * @author Ferre
     * @param spel spel dat aan de databank wordt toegevoegd.
     */
    public void voegSpelToe(Spel spel) {
        spelletjes.add(spel);
        try {
            this.spm.voegSpelToe(spel);
        } catch (Exception e) {
            throw (e);
        }
    }

    /**
     * controleerNaam controleert of de naam van een spel uniek is.
     *
     * @see spelBestaat
     * @throws SpelNaamNietUniekException
     * @author Ferre
     * @param naam naam die geconrtoleerd wordt.
     * @return boolean
     */
    public boolean controleerNaam(String naam) {
        if (this.spm.spelBestaat(naam)) {
            throw new SpelNaamNietUniekException();
        } else {
            return false;
        }
    }

    /**
     * toonSpellen haalt een lijst van spellen uit de databank van een bepaalde
     * speler.
     *
     * @see heeftOpgeslagenSpellen
     * @see toonSpellen
     * @throws SpelerHeeftGeenOpgeslagenSpellenException
     * @author Ferre
     * @param spelernaam speler waarvan de spellen uit de databank wordt
     * gehaald.
     * @return String[][]
     */
    public String[][] toonSpellen(String spelernaam) {
        if (this.spm.heeftOpgeslagenSpellen(spelernaam)) {
            return this.spm.toonSpellen(spelernaam);
        } else {
            throw new SpelerHeeftGeenOpgeslagenSpellenException();
        }
    }

    /**
     * laadSpel laadt een spel uit de databank.
     *
     * @see maakSpel
     * @author Ferre
     * @param spelnaam naam van het spel dat wordt geladen.
     * @param speler naam van de speler van wie het spel is.
     * @return Spel
     */
    public Spel laadSpel(String spelnaam, Speler speler) {
        return this.spm.maakSpel(spelnaam, speler);
    }

    /**
     * verwijderSpel verwijdert een spel uit de databank
     *
     * @see verwijderSpel
     * @author Ferre
     * @param spelnaam naam van het spel dat uit de databank verwijdert wordt.
     */
    public void verwijderSpel(String spelnaam) {
        this.spm.verwijderSpel(spelnaam);
    }

    /**
     * voegSpelTegenstanderToe creert een niew spel
     * en voegt daar de tegenstander aan toe
     *
     * @see voegSpelTegenstanderToe
     * @author Ferre
     * @param spel
     * @param tegenstander
     */
    public void voegSpelTegenstanderToe(Spel spel, String tegenstander) {
        spelletjes.add(spel);
        this.spm.voegSpelTegenstanderToe(spel, tegenstander);
    }

    /**
     * laadSpelUitdaging geeft een spel van een uitdaging terug
     *
     * @see geefSpelnaam
     * @see getNaam
     * @see maakSpel
     * @author Ferre
     * @param speler
     * @param ID
     * @return Spel
     */
    public Spel laadSpelUitdaging(Speler speler, int ID) {
        String naam = this.spm.geefSpelnaam(speler.getNaam(), ID);
        return this.spm.maakSpel(naam, speler);
    }

    /**
     * verwijderSpellenUitdaging verwijderd bij beide spelers
     * van een uitdaging het spel
     * 
     *
     * @see geefNamenSpelUitdaging
     * @see verwijderSpel
     * @author Ferre
     * @param ID
     */
    public void verwijderSpellenUitdaging(int ID) {
        String[] spelnamen = this.spm.geefNamenSpelUitdaging(ID);
        for (String naam : spelnamen) {
            this.spm.verwijderSpel(naam);
        }
    }

    /**
     * geefLopendeUitdagingInfo
     *
     * @see geefLopendeUitdagingInfo
     * @author Ferre
     * @param idsenNaam
     * @return String[][]
     */
    public String[][] geefLopendeUitdagingInfo(String[][] idsenNaam) {
        return this.spm.geefLopendeUitdagingInfo(idsenNaam);
    }
}
