package domein;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Michiel S.
 */
public class Spelbord {
    
    private MoeilijkheidsGraad moeilijkheidsGraad;
    private List<String> code;
    private List<List<String>> pogingen;
    
    public Spelbord(int moeilijkheidsGraad){
        this.pogingen = new ArrayList<>();
        stelMoeilijkheidsGraadIn(moeilijkheidsGraad);
        setCode();
        maakSpelbord();
    }

    private void setCode(){
        this.code = this.moeilijkheidsGraad.genereerCode();
    }
    
    private void stelMoeilijkheidsGraadIn(int moeilijkheidsGraad){
        switch(moeilijkheidsGraad){
            case 1:
                this.moeilijkheidsGraad = new Gemakkelijk();
                break;
            case 2:
                this.moeilijkheidsGraad = new Gemiddeld();
                break;
            case 3:
                this.moeilijkheidsGraad = new Moeilijk();
                break;
        }
    }
    
    private void maakSpelbord(){
        int aantalRijen = 12;
        
        for(int i=0;i<aantalRijen;i++){
            List<String> rij = new ArrayList<>();
            
            for(int j=0;i<code.size();i++){
                rij.add("0");
           }
            
            this.pogingen.add(rij);
        }
    }
    
    public List<String> getCode(){
        System.out.println("code:"+code);
        return this.code;
    }
    
    public List<List<String>> getPogingen(){
        return this.pogingen;
    }
    
}
