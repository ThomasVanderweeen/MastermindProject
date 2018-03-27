package domein;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Groep 77
 */
public class Gemakkelijk extends MoeilijkheidsGraad {
   
    /**
     * Constructor voor een gemakkelijk object.
     */
    public Gemakkelijk(){
        super(4,4);
    }
    
    /*lijst methode met kleuren verwijderen zou minder tijd kosten*/
    /**
     * genereerCode geneert een code waarvan de kleuren willekeurig gekozen worden.
     * @return List<CodePin>
     */
    @Override
    public List<CodePin> genereerCode(){
        List<CodePin> code = new ArrayList<>();
        String[] kleuren =  MoeilijkheidsGraad.getKleuren();
        String kleur;
        int rand;

        /*AsList methode retoutneerd vaste grote lijst vandaar kan de remove niet gebruikt worden
        Vandaar de addAll methode.
        */
        List<String> kleurtjes = new ArrayList<>();
        kleurtjes.addAll(Arrays.asList(kleuren));

        
        do{
            rand = (int)(Math.random()*(kleurtjes.size()-1));
            kleur = kleurtjes.get(rand);
            code.add(new CodePin(kleur));
            kleurtjes.remove(kleur);
        }while(code.size()<super.getAantalPinnen());
    
        return code;
    }
}
