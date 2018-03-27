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
    

     /**
     * setMoeilijkheidsGraad wijzigt de waarde van de moeilijkheidsgraad van een
     * code object.
     *
     * @param moeilijkheidsgraad de nieuwe waarde voor moeilijkheidsgraad.
     */
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
    
     /**
     * Constructor voor een code object. aan de hand van de meegegeven
     * moeilijkheisdgraad wordt een random code gegenereerd.
     *
     * @param moeilijkheidsGraad moeilijkheidsgraad waarvoor een code wirdt
     * gegenereerd.
     */
    public Code(int moeilijkheidsGraad){
        setMoeiljkheidsGraad(moeilijkheidsGraad);
        this.code = this.moeilijkheidsGraad.genereerCode();
    }
    
     /**
     * Constructor voor een code object waarbij de code als List<CodePin> wordt meegegeven.
     *
     * @param code de code
     * @param mg moeilijkheidsgraad van de code.
     */
    public Code(List<CodePin> code,MoeilijkheidsGraad mg){
        this.code = code;
        this.moeilijkheidsGraad = mg;
    }

    /**
     * getCode geeft de code van een code object.
     *
     * @return List<CodePin>
     */
    public List<CodePin> getCode() {
        return code;
    }

     /**
     * getMoeilijkheidsGraad geeft de moeilijkheidsgraad van een code object.
     *
     * @return MoeilijkheidsGraad
     */
    public MoeilijkheidsGraad getMoeilijkheidsGraad() {
        return moeilijkheidsGraad;
    }
    
    /**
     * geefGeldigeKleuren roept de geefGeldigeKleuren methode van klasse
     * moeilijkheidsgraad op om een array met alle geldige kleuren terug te
     * krijgen.
     *
     * @return String[]
     */
    public String[] geefGeldigeKleuren(){
        return this.moeilijkheidsGraad.geefGeldigeKleuren();
    }
    
    /**
     * geefAantalPositiest roept de geefAantalPosities methode van klasse
     * moeilijkheidsgraad op om het aantal posities in een rij terug te krijgen.
     *
     * @return integer
     */
    public int geefAantalPosities(){
        return this.moeilijkheidsGraad.getAantalPosities();
    }
    
}
