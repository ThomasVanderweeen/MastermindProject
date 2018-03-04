package domein;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Michiel S.
 */
public class SpelRepository {
    private List<Spel> spelletjes;
    
    public SpelRepository(){
        spelletjes = new ArrayList<>();
    }
    
    public void voegSpelToe(Spel spel){
        spelletjes.add(spel);
    }
}
