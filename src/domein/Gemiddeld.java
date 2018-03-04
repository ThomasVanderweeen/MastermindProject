package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michiel S.
 */
public class Gemiddeld extends MoeilijkheidsGraad {
    public Gemiddeld(){
        super(4,4);
    }
    
    
    @Override
    public List<String> genereerCode(){
        List<String> code = new ArrayList<>();
        String[] kleuren =  MoeilijkheidsGraad.getKleuren();
        int rand;
        
        do{
            rand = (int)(Math.random()*(kleuren.length-1));
            code.add(kleuren[rand]);
        }while(code.size()<super.getAantalPosities());
        
        return code;
    }
}
