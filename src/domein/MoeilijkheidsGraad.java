package domein;
import java.util.List;
/**
 *
 * @author Michiel S.
 */
public abstract class MoeilijkheidsGraad {
    private final static String[] KLEUREN = {"rood","groen","geel","blauw",
        "roze","paars","bruin","oranje"};
    private final int aantalPosities,aantalPinnen;
    
    public MoeilijkheidsGraad(int aantalPosities, int aantalPinnen){
        this.aantalPinnen = aantalPinnen;
        this.aantalPosities = aantalPosities;
    }
    
    public abstract List<String> genereerCode(); 
    
    protected static String[] getKleuren(){
        return KLEUREN;
    }
    
    protected int getAantalPosities(){
        return this.aantalPosities;
    }
    
    protected int getAantalPinnen(){
        return this.aantalPinnen;
    }
}
