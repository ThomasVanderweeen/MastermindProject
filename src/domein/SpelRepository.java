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
     * @see persistentie.SpelMapper#voegSpelToe(domein.Spel) voegSpelToe
     * @author Ferre
     * @param spel Spel
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
     * @see persistentie.SpelMapper#spelBestaat(java.lang.String) spelBestaat
     * @throws SpelNaamNietUniekException SpelNaamNietUniekException
     * @author Michiel S.
     * @param naam String
     * @return spelnaamuniek boolean
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
     * @see persistentie.SpelMapper#heeftOpgeslagenSpellen(java.lang.String) heeftOpgeslagenSpellen
     * @see persistentie.SpelMapper#toonSpellen(java.lang.String) toonSpellen
     * @throws SpelerHeeftGeenOpgeslagenSpellenException SpelerHeeftGeenOpgeslagenSpellenException
     * @author Michiel S.
     * @param spelernaam String
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
     * @see persistentie.SpelMapper#maakSpel(java.lang.String, domein.Speler) maakSpel
     * @author Ferre
     * @param spelnaam String
     * @param speler Speler
     * @return Spel
     */
    public Spel laadSpel(String spelnaam, Speler speler) {
        return this.spm.maakSpel(spelnaam, speler);
    }

    /**
     * verwijderSpel verwijdert een spel uit de databank
     *
     * @see persistentie.SpelMapper#verwijderSpel(java.lang.String) verwijderSpel
     * @author Michiel S.
     * @param spelnaam naam van het spel dat uit de databank verwijdert wordt.
     */
    public void verwijderSpel(String spelnaam) {
        this.spm.verwijderSpel(spelnaam);
    }

    /**
     * voegSpelTegenstanderToe creert een niew spel
     * en voegt daar de tegenstander aan toe
     *
     * @see persistentie.SpelMapper#voegSpelTegenstanderToe(domein.Spel, java.lang.String)  voegSpelTegenstanderToe
     * @author Michiel
     * @param spel Spel
     * @param tegenstander String
     */
    public void voegSpelTegenstanderToe(Spel spel, String tegenstander) {
        spelletjes.add(spel);
        this.spm.voegSpelTegenstanderToe(spel, tegenstander);
    }

    /**
     * laadSpelUitdaging geeft een spel van een uitdaging terug
     *
     * @see persistentie.SpelMapper#geefSpelnaam(java.lang.String, int) geefSpelnaam
     * @see Speler#getNaam() getNaam
     * @see persistentie.SpelMapper#maakSpel(java.lang.String, domein.Speler) maakSpel
     * @author Ferre
     * @param speler Speler
     * @param ID Integer
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
     * @see persistentie.SpelMapper#geefNamenSpelUitdaging(int) geefNamenSpelUitdaging
     * @see persistentie.SpelMapper#verwijderSpel(java.lang.String) verwijderSpel
     * @author Ferre
     * @param ID Integer
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
     * @see persistentie.SpelMapper#geefLopendeUitdagingInfo(java.lang.String[][]) geefLopendeUitdagingInfo
     * @author Michiel
     * @param idsenNaam String[][]
     * @return String[][]
     */
    public String[][] geefLopendeUitdagingInfo(String[][] idsenNaam) {
        return this.spm.geefLopendeUitdagingInfo(idsenNaam);
    }
}
