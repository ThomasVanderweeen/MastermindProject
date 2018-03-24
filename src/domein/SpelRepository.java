package domein;
import java.util.List;
import java.util.ArrayList;
import persistentie.SpelMapper;
import exceptions.SpelNaamNietUniekException;
/**
 *
 * @author Groep 77
 */
public class SpelRepository {
    private List<Spel> spelletjes;
    private final SpelMapper spm;
    
    public SpelRepository(){
        spelletjes = new ArrayList<>();
        this.spm = new SpelMapper(); 
    }
    
    public void voegSpelToe(Spel spel){
        spelletjes.add(spel);
        try{
            this.spm.voegSpelToe(spel);
        }catch(Exception e){
            throw(e);
        }
    }
   
    public boolean controleerNaam(String naam){
        if(this.spm.spelBestaat(naam))
            throw new SpelNaamNietUniekException();
        else
            return false;
    }

}
