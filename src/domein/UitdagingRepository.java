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
     * @see Uitdaging#getSpel() getSpel
     * @see Spel#getSpeler() getSpeler
     * @see UitdagingMapper#geefUitdagingID(domein.Speler, java.lang.String) geefUitdagingID
     * @see Uitdaging#getTegenstander() getTegenstander
     * @see Uitdaging#stelIDIn(int) stelIDIn
     * @author Michiel S.
     * @param uitdaging Uitdaging
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
     * @see UitdagingMapper#geefHuidigeUitdagingID(domein.Speler) geefHuidigeUitdagingID
     * @author Michiel S.
     * @param speler Speler
     * @return int
     */
    public int geefLopendeUitdagingId(Speler speler){
        return this.um.geefHuidigeUitdagingID(speler);
    }
    /**
     * verwijderUitdaging verwijderd de uitdaging met meegegeven id
     *
     * @see UitdagingMapper#verwijderUitdaging(int) verwijderUitdaging
     * @author Michiel S.
     * @param ID Integer
     */
    public void verwijderUitdaging(int ID){
        this.um.verwijderUitdaging(ID);
    }
    /**
     * controleerGeldigeUitdaging controleerd of de speler de tegenstander
     * al heeft uitgedaagd
     *
     * @throws SpelerAlUitgedaagdException SpelerAlUitgedaagdException
     * @see UitdagingMapper#spelerIsAlUitgedaag(domein.Speler, java.lang.String) spelerIsAlUitgedaag
     * @author Michiel S.
     * @param speler Speler
     * @param tegenstander String
     */
    public void controleerGeldigeUitdaging(Speler speler,String tegenstander){
        if(this.um.spelerIsAlUitgedaag(speler, tegenstander))
            throw new SpelerAlUitgedaagdException();
    }
    /**
     * controleerGeenLopendeUitdaging controleerd of de speler
     * bezig is met een uitdaging
     *
     * @throws HeeftLopendeUitdagingException HeeftLopendeUitdagingException
     * @see UitdagingMapper#heeftLopendeUitdaging(domein.Speler) heeftLopendeUitdaging
     * @author Michiel S.
     * @param speler Speler
     */
    public void controleerGeenLopendeUitdaging(Speler speler){
        if(this.um.heeftLopendeUitdaging(speler))
            throw new HeeftLopendeUitdagingException();
    }
    /**
     * geefNaamLopendeUitdagingTegenspeler geeft de naam van de tegenstander
     * van de lopende uitdaging van de speler 
     *
     * @see UitdagingMapper#geefHuidigeUitdagingID(domein.Speler) geefHuidigeUitdagingID
     * @see UitdagingMapper#geefNaamTegenstanderID(domein.Speler, int) geefNaamTegenstanderID
     * @author Michiel S.
     * @param speler Speler
     * @return String
     */
    public String geefNaamLopendeUitdagingTegenspeler(Speler speler){
        int ID = um.geefHuidigeUitdagingID(speler);
        return um.geefNaamTegenstanderID(speler, ID);
    }
    /**
     * updateUitdaging update de uitdaging wanneer er een zet is gedaan.
     *
     * @see UitdagingMapper#updateUitdaging(domein.Uitdaging) updateUitdaging
     * @author Ferre
     * @param uitdaging Uitdaging
     */
    public void updateUitdaging(Uitdaging uitdaging){
        this.um.updateUitdaging(uitdaging);
    }
    /**
     * geefLijstUitdagingen geeft een lijst van alle uitdagingen
     * van de speler door andere spelers
     *
     * @see UitdagingMapper#geefUitdagingenSpeler(domein.Speler) geefUitdagingenSpeler
     * @author Michiel S.
     * @param speler Speler
     * @return String[][]
     */
    public String[][] geefLijstUitdagingen(Speler speler){
        return this.um.geefUitdagingenSpeler(speler);
    }
    /**
     * accepteerUitdaging accepteerd de uitdaging met de id
     *
     * 
     * @see UitdagingMapper#accepteerUitdaging(int, domein.Speler) accepteerUitdaging
     * @author Michiel S.
     * @param ID int
     * @param speler Speler
     */
    public void accepteerUitdaging(int ID,Speler speler){
        this.um.accepteerUitdaging(ID,speler);
    }
}
