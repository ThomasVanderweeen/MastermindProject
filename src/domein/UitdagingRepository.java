package domein;
import persistentie.UitdagingMapper;
import java.util.List;
import java.util.ArrayList;
import exceptions.HeeftLopendeUitdagingException;
import exceptions.SpelerAlUitgedaagdException;
/**
 *
 * @author Michiel S.
 */
public class UitdagingRepository {
    private final UitdagingMapper um;
    private final List<Uitdaging> uitdagingen;
    
    /**
     * de constructor maakt een object van UitdagingenRepository aan 
     *
     * @see UitdagingMapper
     * @author Ferre
     */
    public UitdagingRepository(){
        this.um = new UitdagingMapper();
        uitdagingen = new ArrayList<>();
    }
    /**
     * voegUitdagingToe voegt de meegegeven uitdaging toe aan de repository
     * en de mapper
     *
     * @see voegUitdagingToe
     * @see getSpel
     * @see getSpeler
     * @see geefUitdagingID
     * @see getTegenstander
     * @see stelIDIn
     * @author Ferre
     * @param uitdaging
     */
    public void voegUitdagingToe(Uitdaging uitdaging){
        this.uitdagingen.add(uitdaging);
        this.um.voegUitdagingToe(uitdaging);
        
        Spel sp = uitdaging.getSpel();
        Speler speler = sp.getSpeler();
        
        int id = this.um.geefUitdagingID(speler,uitdaging.getTegenstander());
        uitdaging.stelIDIn(id);
        
    }
    /**
     * geefLopendeUitdagingId geeft de id van de speler zijn lopende uitdaging terug
     *
     * @see geefHuidigeUitdagingID
     * @author Ferre
     * @param speler
     * @return int
     */
    public int geefLopendeUitdagingId(Speler speler){
        return this.um.geefHuidigeUitdagingID(speler);
    }
    /**
     * verwijderUitdaging verwijderd de uitdaging met meegegeven id
     *
     * @see verwijderUitdaging
     * @author Ferre
     * @param ID
     */
    public void verwijderUitdaging(int ID){
        this.um.verwijderUitdaging(ID);
    }
    /**
     * controleerGeldigeUitdaging controleerd of de speler de tegenstander
     * al heeft uitgedaagd
     *
     * @throws SpelerAlUitgedaagdException
     * @see spelerIsAlUitgedaag
     * @author Ferre
     * @param speler
     * @param tegenstander
     */
    public void controleerGeldigeUitdaging(Speler speler,String tegenstander){
        if(this.um.spelerIsAlUitgedaag(speler, tegenstander))
            throw new SpelerAlUitgedaagdException();
    }
    /**
     * controleerGeenLopendeUitdaging controleerd of de speler
     * bezig is met een uitdaging
     *
     * @throws HeeftLopendeUitdagingException
     * @see heeftLopendeUitdaging
     * @author Ferre
     * @param speler
     */
    public void controleerGeenLopendeUitdaging(Speler speler){
        if(this.um.heeftLopendeUitdaging(speler))
            throw new HeeftLopendeUitdagingException();
    }
    /**
     * geefNaamLopendeUitdagingTegenspeler geeft de naam van de tegenstander
     * van de lopende uitdaging van de speler 
     *
     * @see geefHuidigeUitdagingID
     * @see geefNaamTegenstanderID
     * @author Ferre
     * @param speler
     * @return String
     */
    public String geefNaamLopendeUitdagingTegenspeler(Speler speler){
        int ID = um.geefHuidigeUitdagingID(speler);
        return um.geefNaamTegenstanderID(speler, ID);
    }
    /**
     * updateUitdaging update de uitdaging wanneer er een zet is gedaan.
     *
     * @see updateUitdaging
     * @author Ferre
     * @param uitdaging
     */
    public void updateUitdaging(Uitdaging uitdaging){
        this.um.updateUitdaging(uitdaging);
    }
    /**
     * geefLijstUitdagingen geeft een lijst van alle uitdagingen
     * van de speler door andere spelers
     *
     * @see geefUitdagingenSpeler
     * @author Ferre
     * @param speler
     * @return String[][]
     */
    public String[][] geefLijstUitdagingen(Speler speler){
        return this.um.geefUitdagingenSpeler(speler);
    }
    /**
     * accepteerUitdaging accepteerd de uitdaging met de id
     *
     * 
     * @see accepteerUitdaging
     * @author Ferre
     * @param ID
     * @param speler
     */
    public void accepteerUitdaging(int ID,Speler speler){
        this.um.accepteerUitdaging(ID,speler);
    }
}
