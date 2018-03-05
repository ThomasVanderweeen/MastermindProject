package domein;

import java.util.List;
import java.util.ArrayList;
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
    public List<String> genereerCode(){
        List<String> code = new ArrayList<>();
        String[] kleuren =  MoeilijkheidsGraad.getKleuren();
        int rand;
        
        do{
            rand = (int)(Math.random()*(kleuren.length-1));
            if(code.indexOf(kleuren[rand])==-1)
                code.add(kleuren[rand]);
        }while(code.size()<super.getAantalPosities());
        
        return code;
    }
}
