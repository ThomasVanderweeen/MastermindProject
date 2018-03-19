package domein;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Groep 77
 */
public class SpelRepository {
    private List<Spel> spelletjes;
    
    public SpelRepository(){
        spelletjes = new ArrayList<>();
    }
    
    public void voegSpelToe(Spel spel){
        spelletjes.add(spel);
    }
   
    /*nog niet geimplementeerd 
    public boolean controleerNaam(String naam){
        
    }
    */
}
