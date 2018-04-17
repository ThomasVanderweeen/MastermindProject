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
public class SpelRepository {
    private List<Spel> spelletjes;
    private final SpelMapper spm;
    
    /**
     * constructor voor een spelRepository object.
     */
    public SpelRepository(){
        spelletjes = new ArrayList<>();
        this.spm = new SpelMapper(); 
    }
    
    /**
     * voegSpelToe voegt een spel toe aan de databank via de spelmapper.
     * 
     * @param spel spel dat aan de databank wordt toegevoegd.
     */
    public void voegSpelToe(Spel spel){
        spelletjes.add(spel);
        try{
            this.spm.voegSpelToe(spel);
        }catch(Exception e){
            throw(e);
        }
    }
   
    /**
     * controleerNaam controleert of de naam van een spel uniek is.
     * 
     * @param naam naam die geconrtoleerd wordt.
     * @return 
     */
    public boolean controleerNaam(String naam){
        if(this.spm.spelBestaat(naam))
            throw new SpelNaamNietUniekException();
        else
            return false;
    }
    /**
     * toonSpellen haalt een lijst van spellen uit de databank van een bepaalde speler.
     * 
     * @param spelernaam speler waarvan de spellen uit de databank wordt gehaald.
     * @return String[][]
     */
    public String[][] toonSpellen(String spelernaam){
        if(this.spm.heeftOpgeslagenSpellen(spelernaam))
            return this.spm.toonSpellen(spelernaam);
         else
            throw new SpelerHeeftGeenOpgeslagenSpellenException();
    }
    
    /**
     * laadSpel laadt een spel uit de databank.
     * 
     * @param spelnaam naam van het spel dat wordt geladen.
     * @param speler naam van de speler van wie het spel is.
     * @return Spel
     */
    public Spel laadSpel(String spelnaam,Speler speler){
        return this.spm.maakSpel(spelnaam, speler);
    }
    
    /**
     * verwijderSpel verwijdert een spel uit de databank
     * 
     * @param spelnaam naam van het spel dat uit de databank verwijdert wordt.
     */
    public void verwijderSpel(String spelnaam){
        this.spm.verwijderSpel(spelnaam);
    }
    
    public void voegSpelTegenstanderToe(Spel spel,String tegenstander) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        spelletjes.add(spel);
        this.spm.voegSpelTegenstanderToe(spel,tegenstander);
        
    }

}
