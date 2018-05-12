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
     * @see controleerMoeilijkheidsGraad
     * @author Ferre
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
     * @author Thomas
     * @param spelbord spelbord van een spel
     * @param speler speler van een spel
     */
    public Spel(Spelbord spelbord,Speler speler){
        this.speler = speler;
        this.spelbord = spelbord;
    }
    
    /**
     * getAantalPogingen geeft het aantalPogingen van een spel object.
     * 
     * @author Thomas
     * @return int
     */
    public int getAantalPogingen(){
        return this.aantalPogingen;
    }
    
    /**
     * stelUitdagingIDIn stelt het id van de uitdaging in
     * 
     * @author Thomas
     * @param id
     */
    public void stelUitdagingIDIn(int id){
        this.uitdagingID = id;
    }
    
    
    /**
     * controleerMoeilijkheidsGraad controleert of de speler al genoeg
     * in de vorige moeilijkheidsgraad heeft gewonnen
     * om de volgende te mogen gebruiken
     * 
     * @see getAantalGewonnenMakkelijk
     * @see getAantalGewonnenGemiddeld
     * @throws NietGenoegGewonnenException
     * @author Ferre
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
     * @author Thomas
     * @return Spelbord
     */
    public Spelbord getSpelBord(){
        return this.spelbord;
    }
    
    /**
     * isGewonnen bepaalt of de speler gewonnen is.
     * 
     * @author Thomas
     * @see getCodeGeraden
     * @return boolean
     */
    public boolean isGewonnen(){
        return this.spelbord.getCodeGeraden();
    }
    
    /**
     * bepaalEindeSpel bepaalt of het spel ten einde is.
     * 
     * @see getAantalPogingen
     * @author Ferre
     * @return boolean
     */
    public boolean bepaalEindeSpel(){
        if((getAantalPogingen() >= 12)||isGewonnen())
            return true;
        
        return false;
    }
    
    /**
     * doePoging voegt een poging toe aan het spelbord.
     * 
     * @author Ferre
     * @see getAantalPogingen
     * @see voegPogingToe
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
     * @author Ferre
     * @see geefMoeilijkheidsGraad
     * @return int 
     */
    public int geefMoeilijkheidsGraad(){
        return this.spelbord.geefMoeilijkheidsGraad();
    }
    
    /**
     * getSpeler geeft de speler van een spel object.
     * 
     * @author Thomas
     * @return Speler 
     */
    public Speler getSpeler(){
        return this.speler;
    }
    
    /**
     * geefAantalSterrenenAantalTotVolgende geeft het aantal sterren
     * dat een speler reeds heeft behaald en hoeveel sterren deze speler
     * nog nodig heeft om de volgende moeilijkheidsgraad te ontgrendelen.
     * 
     * @see geefMoeilijkheidsGraad
     * @see geefAantalSterrenEnAantalTotVolgende
     * @author Ferre
     * @return int[]
     */
    public int[] geefAantalSterrenEnAantalTotVolgende(){
        
        int moeilijkheidsGraad = this.spelbord.geefMoeilijkheidsGraad();
        return this.speler.geefAantalSterrenEnAantalTotVolgende(moeilijkheidsGraad);

    }
    
    /**
     * getNaam geeft de naam van een spelObject.
     * 
     * @author Thomas
     * @return String 
     */
    public String getNaam(){
        return this.naam;
    }
    
    /**
     * stelNaamIn verandert de naam van een spel object.
     * 
     * @author Thomas
     * @param naam de naam van het spel
     */
    public void stelNaamIn(String naam){
        this.naam = naam;
    }
    

    /**
     * getUitdagingID geeft de ID van de huidige uitdaging terug
     * 
     * @author Ferre
     * @return int 
     */
    public int getUitdagingID(){
        return this.uitdagingID;
    }
    
}
