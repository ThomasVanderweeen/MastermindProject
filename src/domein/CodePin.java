package domein;

import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *bevat de interne logica van codepin
 * @author Groep 77
 */
public class CodePin extends Pin{
    private String kleur;
    private static final String[] GELDIGE_KLEUREN = new String[] {"groen","geel","paars","blauw","oranje","bruin","roze","cyaan"};

    /**
     * Constructor voor een codepin object die kleur als een String meekrijgt. 
     * De constructor gebruikt bepaalGeldigeKleur om na te gaan of de kleur gebruikt kan worden.
     *
     * @see bepaalGeldigeKleur
     * @author Thomas
     * @param kleur String
     */
    public CodePin(String kleur) {
        super(kleur);
        bepaalGeldigeKleur(kleur);
    }
    
    /**
     * Constuctor voor een codepin object die de kleur als een integer meekrijgt.
     * 
     * @author Thomas
     * @param kleur Integer
     */
    public CodePin(int kleur){
        super(GELDIGE_KLEUREN[kleur]);
    }
    
     /**
     * getKleur geeft de kleur van een codepin object.
     * 
     * @see Pin#getKleur() getKleur
     * @author Michiel S.
     * @return String 
     */
    @Override
    public String getKleur() {
        return super.getKleur();
    }
    
    /**
     * bepaaldGeldigeKleur gaat na of 
     * de ingegeven kleur voorkomt in de array met geldige kleuren.
     * Zoniet, werpt hij een inputmismatch exception.
     * 
     * @author Michiel S.
     * @throws InputMismatchException InputMismatchException
     * @param kleur String
     */
    @Override
    protected void bepaalGeldigeKleur(String kleur) {
        if(!Arrays.asList(GELDIGE_KLEUREN).contains(kleur))
            throw new InputMismatchException();    
    }
    
     /**
     * getGeldigeKleuren geeft een array met daarin alle geldige kleuren voor een codepin.
     * 
     * @author Michiel S.
     * @return String[]
     */
    public static String[] getGeldigeKleuren(){
        return GELDIGE_KLEUREN;
    }
}
