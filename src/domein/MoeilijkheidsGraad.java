package domein;
import java.util.List;
/**
 *
 * @author Groep 77
 */
public abstract class MoeilijkheidsGraad {
    private final int aantalPosities,aantalPinnen;
    
    /**
     * constructor voor de moeilijkheidsgraad 
     * 
     * @author Ferre
     * @param aantalPosities Integer
     * @param aantalPinnen Integer
     */
    public MoeilijkheidsGraad(int aantalPosities, int aantalPinnen){
        this.aantalPinnen = aantalPinnen;
        this.aantalPosities = aantalPosities;
    }
    
    /**
     * abstracte methode die de code van elke pin teruggeeft
     * 
     * @return List
     */
    public abstract List<CodePin> genereerCode(); 
    
    /**
     * getKleuren geeft de kleuren die een pin kan hebben terug 
     * 
     * @see CodePin#getGeldigeKleuren() getGeldigeKleuren
     * @author Ferre
     * @return String[]
     */
    protected static String[] getKleuren(){
        return CodePin.getGeldigeKleuren();
    }
    /**
     * getAantalPosities geeft het aantal posities voor de moeilijkheidsgraad in kwestie
     * 
     * @author Ferre
     * @return Integer
     */
    protected int getAantalPosities(){
        return this.aantalPosities;
    }
    /**
     * getAantalPinnen geeft het aantal pinnen voor de moeilijkheidsgraad
     * 
     * @author Ferre
     * @return Integer
     */
    protected int getAantalPinnen(){
        return this.aantalPinnen;
    }

}
