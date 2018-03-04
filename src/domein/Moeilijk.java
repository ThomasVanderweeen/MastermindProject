package domein;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Michiel S.
 */
public class Moeilijk extends MoeilijkheidsGraad{
    public Moeilijk(){
        super(5,3);
    }
    
    @Override
    public List<String> genereerCode(){
        
        String[] kleuren = MoeilijkheidsGraad.getKleuren();
        List<String> code = new ArrayList<>();
        int rand,leeg=0;
       
        do{
            if(leeg<(super.getAantalPosities()-super.getAantalPinnen())){
                rand = (int)(Math.random()*kleuren.length);
                leeg++;
            }else
                rand = (int)(Math.random()*(kleuren.length-1));
            
            if(rand==kleuren.length){
                code.add("leeg");
            }else{
                code.add(kleuren[rand]);
            }
            
            
        }while(code.size()<super.getAantalPosities());
        
        return code;
    }
}
