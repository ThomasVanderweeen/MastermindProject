package domein;
import java.util.List;
/**
 *
 * @author Groep 77
 */
public abstract class MoeilijkheidsGraad {
    private final int aantalPosities,aantalPinnen;
    
    public MoeilijkheidsGraad(int aantalPosities, int aantalPinnen){
        this.aantalPinnen = aantalPinnen;
        this.aantalPosities = aantalPosities;
    }
    
    public abstract List<CodePin> genereerCode(); 
    
    protected static String[] getKleuren(){
        return CodePin.getGeldigeKleuren();
    }
    
    protected int getAantalPosities(){
        return this.aantalPosities;
    }
    
    protected int getAantalPinnen(){
        return this.aantalPinnen;
    }

}
