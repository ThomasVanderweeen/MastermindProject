package domein;
import exceptions.NietGenoegGewonnenException;
/**
 *
 * @author Groep 77
 */
public class Spel {
    private final Speler speler;
    private final Spelbord spelbord;
    private int aantalPogingen;
    private String naam;
    
    
    public Spel(int moeilijkheidsGraad,Speler speler){
        this.speler = speler;
        controleerMoeilijkheidsGraad(moeilijkheidsGraad);
        this.spelbord = new Spelbord(moeilijkheidsGraad);
    }
    
    public Spel(Spelbord spelbord,Speler speler){
        this.speler = speler;
        this.spelbord = spelbord;
    }
    
    public String[] geefGeldigeKleuren(){
        return this.spelbord.geefGeldigeKleuren();
    }
    
    public int getAantalPogingen(){
        return this.aantalPogingen;
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
    
    public boolean isGewonnen(){
        return this.spelbord.getCodeGeraden();
    }
    
    public boolean bepaalEindeSpel(){
        if ((getAantalPogingen() >= 12)||isGewonnen())
            return true;
        
        return false;
    }
    
    public void doePoging(int[] poging){
        int rij = getAantalPogingen();
        this.spelbord.voegPogingToe(poging, rij);
        this.aantalPogingen ++;
    }
    
    public int geefMoeilijkheidsGraad(){
        return this.spelbord.geefMoeilijkheidsGraad();
    }
    
    public Speler getSpeler(){
        return this.speler;
    }
    

    public int[] geefAantalSterrenEnAantalTotVolgende(){
        
        int moeilijkheidsGraad = this.spelbord.geefMoeilijkheidsGraad();
        return this.speler.geefAantalSterrenEnAantalTotVolgende(moeilijkheidsGraad);

    }
    
    public String getNaam(){
        return this.naam;
    }
    
    public void stelNaamIn(String naam){
        this.naam = naam;
    }
}
