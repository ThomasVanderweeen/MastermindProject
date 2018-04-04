package cui;
import domein.DomeinController;
import java.util.ResourceBundle;
/**
 *
 * @author Michiel S.
 */
public class UC_Algemeen {
    private final DomeinController dc;
    private final ResourceBundle r;
    
    public UC_Algemeen(DomeinController dc, ResourceBundle r){
        this.dc =dc;
        this.r = r;
    }
        
    public void geefSpelbordWeer(){
        String[][] spelbord = this.dc.geefSpelBord();
        String[] code = this.dc.geefCode();
        String res = String.format("%S",
                r.getString("code"));
       
        for(String codepin:code){
            res+= String.format("%10s",r.getString(codepin));
        }
        
        
        res += String.format("%n%S%n",r.getString("spelbord"));
        for (String[] rij:spelbord){
            res+=String.format("%S%s",r.getString("rij"),"[");
            if(rij.length<6){
                
                for(String pin:rij){
                    res+=String.format("%10s",r.getString(pin));
                }

            }else{
                int lengte = rij.length;
                
                for(int i=0;i<(lengte/2);i++){
                    res+=String.format("%10s",r.getString(rij[i]));
                }
                
                res+=String.format("%s%n%S%s","]",r.getString("evaluatie"),"[");
                for(int i=lengte/2;i<lengte;i++){
                    res+=String.format("%10s", r.getString(rij[i]));
                }
                
            }
           res+=String.format("%s%n","]");
        }
        
        System.out.println(res);
    }
}
