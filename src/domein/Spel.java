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
     * @param moeilijkheidsGraad Integer
     * @param speler Speler
     */
    public Spel(int moeilijkheidsGraad,Speler speler){
        this.speler = speler;
        controleerMoeilijkheidsGraad(moeilijkheidsGraad);
        this.spelbord = new Spelbord(moeilijkheidsGraad);
    }
    
    /**
     * Constructor voor een spel object.
     * 
     * @author Michiel S.
     * @param spelbord Spelbord
     * @param speler Speler
     */
    public Spel(Spelbord spelbord,Speler speler){
        this.speler = speler;
        this.spelbord = spelbord;
    }
    
    /**
     * getAantalPogingen geeft het aantalPogingen van een spel object.
     * 
     * @author Michiel S.
     * @return int
     */
    public int getAantalPogingen(){
        return this.aantalPogingen;
    }
    
    /**
     * stelUitdagingIDIn stelt het id van de uitdaging in
     * 
     * @author Michiel S.
     * @param id Integer
     */
    public void stelUitdagingIDIn(int id){
        this.uitdagingID = id;
    }
    
    
    /**
     * controleerMoeilijkheidsGraad controleert of de speler al genoeg
     * in de vorige moeilijkheidsgraad heeft gewonnen
     * om de volgende te mogen gebruiken
     * 
     * @see Speler#getAantalGewonnenMakkelijk() getAantalGewonnenMakkelijk
     * @see Speler#getAantalGewonnenGemiddeld() getAantalGewonnenGemiddeld
     * @throws NietGenoegGewonnenException
     * @author Ferre
     * @param moeilijkheidsGraad Integer
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
     * @author Michiel S.
     * @see Spelbord#getCodeGeraden() getCodeGeraden
     * @return boolean
     */
    public boolean isGewonnen(){
        return this.spelbord.getCodeGeraden();
    }
    
    /**
     * bepaalEindeSpel bepaalt of het spel ten einde is.
     * 
     * @see #getAantalPogingen() getAantalPogingen
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
     * @author Michiel S.
     * @see #getAantalPogingen() getAantalPogingen
     * @see Spelbord#voegPogingToe(int[], int) voegPogingToe
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
     * @see Spelbord#geefMoeilijkheidsGraad() geefMoeilijkheidsGraad
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
     * @see Spelbord#geefMoeilijkheidsGraad() geefMoeilijkheidsGraad
     * @see Speler#geefAantalSterrenEnAantalTotVolgende(int) geefAantalSterrenEnAantalTotVolgende
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
     * @author Michiel S.
     * @param naam String
     */
    public void stelNaamIn(String naam){
        this.naam = naam;
    }
    

    /**
     * getUitdagingID geeft de ID van de huidige uitdaging terug
     * 
     * @author Michiel S.
     * @return Integer
     */
    public int getUitdagingID(){
        return this.uitdagingID;
    }
    
}
