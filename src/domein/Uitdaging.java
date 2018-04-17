package domein;

/**
 *
 * @author Michiel S.
 */
public class Uitdaging {
    private final String tegenstander;
    private Spel spel;
    
    public Uitdaging(Speler uitdager, String spelernaam,int moeilijkheidsGraad){
        this.tegenstander = spelernaam;
        this.spel = new Spel(moeilijkheidsGraad,uitdager);
    }
    
    public String getTegenstander(){
        return this.tegenstander;
    }
    
    public Spel getSpel(){
        return this.spel;
    }
    
    public void stelIDIn(int ID){
        this.spel.stelUitdagingIDIn(ID);
    }
}
