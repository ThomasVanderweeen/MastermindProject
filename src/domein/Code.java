/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author ThomasV
 */
public class Code {
    CodePin[] code;
    MoeilijkheidsGraad moeilijkheidsGraad;
    
    public Code(MoeilijkheidsGraad moeilijkheidsGraad){
        this.moeilijkheidsGraad = moeilijkheidsGraad;
    }

    public CodePin[] getCode() {
        return code;
    }

    public MoeilijkheidsGraad getMoeilijkheidsGraad() {
        return moeilijkheidsGraad;
    }
}
