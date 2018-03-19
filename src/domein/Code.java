/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;
import java.util.List;
/**
 *
 * @author Groep 77
 */
public class Code {
    private final List<CodePin> code;
    private MoeilijkheidsGraad moeilijkheidsGraad;
    

    
    private void setMoeiljkheidsGraad(int moeilijkheidsgraad){
        switch(moeilijkheidsgraad){
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
    
    public Code(int moeilijkheidsGraad){
        setMoeiljkheidsGraad(moeilijkheidsGraad);
        this.code = this.moeilijkheidsGraad.genereerCode();
    }

    public List<CodePin> getCode() {
        return code;
    }

    public MoeilijkheidsGraad getMoeilijkheidsGraad() {
        return moeilijkheidsGraad;
    }
    
    public String[] geefGeldigeKleuren(){
        return this.moeilijkheidsGraad.geefGeldigeKleuren();
    }
    
    public int geefAantalPosities(){
        return this.moeilijkheidsGraad.getAantalPosities();
    }
    
}
