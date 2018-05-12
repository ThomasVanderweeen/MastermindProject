package domein;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Groep 77
 */
public class Moeilijk extends MoeilijkheidsGraad{
    /**
     * constructor voor een moeilijk object.
     */
    public Moeilijk(){
        super(5,3);
    }
    
    /**
     * genereerCode geneert een code waarvan de kleuren willekeurig gekozen worden.
     * 
     * @see getKleuren
     * @see getAantalPosities
     * @see getAantalPinnen
     * @author Thomas
     * @return List CodePin
     */
    @Override
    public List<CodePin> genereerCode(){
        
        String[] kleuren = MoeilijkheidsGraad.getKleuren();
        List<CodePin> code = new ArrayList<>();
        int rand,leeg=0;
       
        do{
            if(leeg<(super.getAantalPosities()-super.getAantalPinnen())){
                rand = (int)(Math.random()*kleuren.length);
                leeg++;
            }else
                rand = (int)(Math.random()*(kleuren.length-1));
            
            if(rand==kleuren.length){
                code.add(null);
            }else{
                code.add(new CodePin(kleuren[rand]));
            }
            
            
        }while(code.size()<super.getAantalPosities());
        
        return code;
    }
}
