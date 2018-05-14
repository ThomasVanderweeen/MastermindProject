package gui;

/**
 *
 * @author Michiel S.
 */
public class ScoreBordRij {
    private String naam;
    private int score;
    
    public ScoreBordRij(String naam,int score){
        setNaam(naam);
        setScore(score);
    }
    
    private void setNaam(String naam){
        this.naam = naam;
    }
    
    private void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public String getNaam(){
        return this.naam;
    }
}
