package domein;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Michiel S.
 */
public class Gemakkelijk extends MoeilijkheidsGraad {
   
    public Gemakkelijk(){
        super(4,4);
    }
    
    /*lijst methode met kleuren verwijderen zou minder tijd kosten*/
    @Override
    public List<CodePin> genereerCode(){
        List<CodePin> code = new ArrayList<>();
        String[] kleuren =  MoeilijkheidsGraad.getKleuren();
        List<String> kleurtjes = Arrays.asList(kleuren);
        String kleur;
        int rand;
        
        do{
            rand = (int)(Math.random()*(kleurtjes.size()-1));
            kleur = kleurtjes.get(rand);
            code.add(new CodePin(kleur));
            kleurtjes.remove(kleur);
        }while(code.size()<super.getAantalPinnen());
    
        return code;
    }
}
