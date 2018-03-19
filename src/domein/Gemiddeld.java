package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Groep 77
 */
public class Gemiddeld extends MoeilijkheidsGraad {
    public Gemiddeld(){
        super(4,4);
    }
    
    
    @Override
    public List<CodePin> genereerCode(){
        List<CodePin> code = new ArrayList<>();
        String[] kleuren =  MoeilijkheidsGraad.getKleuren();
        int rand;
        
        do{
            rand = (int)(Math.random()*(kleuren.length-1));
            code.add(new CodePin(kleuren[rand]));
        }while(code.size()<super.getAantalPosities());
        
        return code;
    }
}
