package domein;

import exceptions.NietGenoegGewonnenException;
/**
 *
 * @author Groep 77
 */
public class Spel {
    private final Speler speler;
    private final Spelbord spelbord;
    
    public Spel(int moeilijkheidsGraad,Speler speler){
        this.speler = speler;
        controleerMoeilijkheidsGraad(moeilijkheidsGraad);
        this.spelbord = new Spelbord(moeilijkheidsGraad);
    }
    
    private void controleerMoeilijkheidsGraad(int moeilijkheidsGraad){
       if(moeilijkheidsGraad==2){
           if(this.speler.getAantalGewonnenMakkelijk()<20)
               throw new NietGenoegGewonnenException();
       }else{
           if(moeilijkheidsGraad==3){
                if(this.speler.getAantalGewonnenGemiddeld()<20)
                    throw new NietGenoegGewonnenException();
            }
       }
    }
    
    public Spelbord getSpelBord(){
        return this.spelbord;
    }
}
