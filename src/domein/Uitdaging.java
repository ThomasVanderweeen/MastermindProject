package domein;

/**
 *
 * @author Michiel S.
 */
public class Uitdaging {
    private final String tegenstander;
    private Spel spel;
    
    /**
     * de constructor maakt een object van de uitdaging aan met meegegeven parameters
     *
     * @see Spel
     * @author Ferre
     * @param uitdager
     * @param spelernaam
     * @param moeilijkheidsGraad
     */
    public Uitdaging(Speler uitdager, String spelernaam,int moeilijkheidsGraad){
        this.tegenstander = spelernaam;
        this.spel = new Spel(moeilijkheidsGraad,uitdager);
    }
    /**
     * de constructor maakt een object van de uitdaging aan met meegegeven parameters
     *
     * @author Ferre
     * @param sp
     * @param spelernaam
     */
    public Uitdaging(String spelernaam,Spel sp){
        this.tegenstander = spelernaam;
        this.spel = sp;
    }
    /**
     * getTegenstander geeft de tegenstander in de uitdaging terug
     *
     * @author Ferre
     * @return String
     */
    public String getTegenstander(){
        return this.tegenstander;
    }
    /**
     * getSpel geeft het spel van de uitdaging terug
     *
     * @author Ferre
     * @return Spel
     */
    public Spel getSpel(){
        return this.spel;
    }
    /**
     * stelIDIn stelt het id van het spel van de uitdaging in
     *
     * @see stelUitdagingIDIn
     * @author Ferre
     * @param ID
     */
    public void stelIDIn(int ID){
        this.spel.stelUitdagingIDIn(ID);
    }
}
