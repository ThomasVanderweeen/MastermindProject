package domein;

/**
 *
 * @author Michiel S.
 */
public class Uitdaging {
    private final String tegenstander;
    private Spel spel;
    private Speler uitdager;
    
    /**
     * de constructor maakt een object van de uitdaging aan met meegegeven parameters
     *
     * @see Spel
     * @author Michiel S.
     * @param uitdager Speler
     * @param spelernaam String
     * @param moeilijkheidsGraad Integer
     */
    public Uitdaging(Speler uitdager, String spelernaam,int moeilijkheidsGraad){
        this.tegenstander = spelernaam;
        this.spel = new Spel(moeilijkheidsGraad,uitdager);
        this.uitdager = uitdager;
    }
    /**
     * de constructor maakt een object van de uitdaging aan met meegegeven parameters
     *
     * @author Michiel S.
     * @param sp String
     * @param spelernaam Spel
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
     * @author Michiel S.
     * @return Spel
     */
    public Spel getSpel(){
        return this.spel;
    }
    /**
     * stelIDIn stelt het id van het spel van de uitdaging in
     *
     * @see Spel#stelUitdagingIDIn(int) stelUitdagingIDIn
     * @author Ferre
     * @param ID Integer
     */
    public void stelIDIn(int ID){
        this.spel.stelUitdagingIDIn(ID);
    }

    public Speler getUitdager() {
        return uitdager;
    }
}
