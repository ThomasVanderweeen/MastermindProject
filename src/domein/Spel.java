package domein;
import exceptions.NietGenoegGewonnenException;
/**
 *
 * @author Groep 77
 */
public class Spel {
    private final Speler speler;
    private final Spelbord spelbord;
    private int aantalPogingen,uitdagingID=-1;
    private String naam;
    
    
     /**
     * Constructor voor een Spel object. Gebruikt controleerMoeilijkheidsGraad om de geldigheid van de moeilijkheidsgraad te verifiëren. 
     * 
     * @param moeilijkheidsGraad moeilijkheidsgraad van een spel
     * @param speler speler van een spel
     */
    public Spel(int moeilijkheidsGraad,Speler speler){
        this.speler = speler;
        controleerMoeilijkheidsGraad(moeilijkheidsGraad);
        this.spelbord = new Spelbord(moeilijkheidsGraad);
    }
    
    /**
     * Constructor voor een spel object.
     * 
     * @param spelbord spelbord van een spel
     * @param speler speler van een spel
     */
    public Spel(Spelbord spelbord,Speler speler){
        this.speler = speler;
        this.spelbord = spelbord;
    }
    
    /**
     * getAantalPogingen geeft het aanralPogingen van een spel object.
     * 
     * @return int
     */
    public int getAantalPogingen(){
        return this.aantalPogingen;
    }
    
    public void stelUitdagingIDIn(int id){
        this.uitdagingID = id;
    }
    
    
    /**
     * controleerMoeilijkheidsGraad controleert 
     * 
     * @param moeilijkheidsGraad de moeilijkheidsgraad die gecontroleerd wordt. 
     */
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
    
    /**
     * getSpelBord geeft het spelbord van een spel object.
     * 
     * @return Spelbord
     */
    public Spelbord getSpelBord(){
        return this.spelbord;
    }
    
    /**
     * isGewonnen bepaalt of de speler gewonnen is.
     * 
     * @return boolean
     */
    public boolean isGewonnen(){
        return this.spelbord.getCodeGeraden();
    }
    
    /**
     * bepaalEindeSpel bepaalt of het spel ten einde is.
     * 
     * @return bolean. 
     */
    public boolean bepaalEindeSpel(){
        if ((getAantalPogingen() >= 12)||isGewonnen())
            return true;
        
        return false;
    }
    
    /**
     * doePoging voegt een poging toe aan het spelbord.
     * 
     * @param poging poging die wordt toegevoegd
     */
    public void doePoging(int[] poging){
        int rij = getAantalPogingen();
        this.spelbord.voegPogingToe(poging, rij);
        this.aantalPogingen ++;
    }
    
    /**
     * geefMoeilijkheidsGraad geeft de moeilijkheidsgraad van een spel object.
     * 
     * @return int 
     */
    public int geefMoeilijkheidsGraad(){
        return this.spelbord.geefMoeilijkheidsGraad();
    }
    
    /**
     * getSpeler geeft de speler van een spel object.
     * 
     * @return Speler 
     */
    public Speler getSpeler(){
        return this.speler;
    }
    
    /**
     * geefAantalSterrenenAantalTotVolgende geeft het aantal sterren dat een speler reeds heeft behaald en hoeveel sterren deze speler nog nodig heeft om de volgende moeilijkheidsgraad te ontgrendelen.
     * 
     * @return int[]
     */
    public int[] geefAantalSterrenEnAantalTotVolgende(){
        
        int moeilijkheidsGraad = this.spelbord.geefMoeilijkheidsGraad();
        return this.speler.geefAantalSterrenEnAantalTotVolgende(moeilijkheidsGraad);

    }
    
    /**
     * getNaam geeft de naam van een spelObject.
     * 
     * @return String 
     */
    public String getNaam(){
        return this.naam;
    }
    
    /**
     * stelNaamIn verandert de naam van een spel object.
     * 
     * @param naam de naam van het spel
     */
    public void stelNaamIn(String naam){
        this.naam = naam;
    }
    

    
    public int getUitdagingID(){
        return this.uitdagingID;
    }
    
}
