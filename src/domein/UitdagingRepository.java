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
    
    public UitdagingRepository(){
        this.um = new UitdagingMapper();
        uitdagingen = new ArrayList<>();
    }
    
    public void voegUitdagingToe(Uitdaging uitdaging){
        this.uitdagingen.add(uitdaging);
        this.um.voegUitdagingToe(uitdaging);
        
        Spel sp = uitdaging.getSpel();
        Speler speler = sp.getSpeler();
        
        int id = this.um.geefUitdagingID(speler,uitdaging.getTegenstander());
        uitdaging.stelIDIn(id);
        
    }
    
    public void controleerGeldigeUitdaging(Speler speler,String tegenstander){
        if(this.um.spelerIsAlUitgedaag(speler, tegenstander))
            throw new SpelerAlUitgedaagdException();
    }
    
    public void controleerGeenLopendeUitdaging(Speler speler){
        if(this.um.heeftLopendeUitdaging(speler))
            throw new HeeftLopendeUitdagingException();
    }
    
    public String geefNaamLopendeUitdagingTegenspeler(Speler speler){
        int ID = um.geefHuidigeUitdagingID(speler);
        return um.geefNaamTegenstanderID(speler, ID);
    }
}
