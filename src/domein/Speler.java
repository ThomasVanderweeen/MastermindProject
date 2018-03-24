
package domein;

/**
 *
 * @author Groep 77
 */
public class Speler {
    /*beide kunnen private*/
    
    private String naam;
    private String wachtwoord=null;
    private int aantalGewonnenMakkelijk=0;
    private int aantalGewonnenGemiddeld=0;
    private int aantalGewonnenMoeilijk=0;
    private static final int[] aantal = {10,20,50,100,250,Integer.MAX_VALUE};

    public Speler(String gebruikersnaam, String wachtwoord) {
        this(gebruikersnaam,wachtwoord,0,0,0);
        
    }
    
    public Speler(String gebruikersnaam,String wachtwoord,int aantalGewonnenMakkelijk,int aantalGewonnenGemiddeld,int aantalGewonnenMoeilijk){
        this.naam = gebruikersnaam;
        setWachtwoord(wachtwoord);
        this.aantalGewonnenMakkelijk = aantalGewonnenMakkelijk;
        this.aantalGewonnenGemiddeld = aantalGewonnenGemiddeld;
        this.aantalGewonnenMoeilijk = aantalGewonnenMoeilijk;
    }

    public Speler(String gebruikersnaam, String wachtwoord,String wachtwoordBevestiging) {
        this(gebruikersnaam,wachtwoord,0,0,0);
        controleerWachtwoord(wachtwoord,wachtwoordBevestiging);
    }
    
    /*wachtwoord bevat minstens 12 karakters eerste drie of laatste drie geen cijfer --> parse to int error --> exception
    */
    public void setNaam(String naam){    
        this.naam = naam;

    }
    
    public String getWachtwoord(){
        return this.wachtwoord;
    }

    /* OKE?!?! hoe exceptions afhandelen???!?*/
    private void setWachtwoord(String wachtwoord) {

            if(!wachtwoord.matches("\\d{1}[a-zA-z]{6,}\\d{1}"))
                throw new IllegalArgumentException();
            else
                this.wachtwoord = wachtwoord;
        

    }
    
    public String getNaam() {
        return this.naam;
    }


    
    public int getAantalGewonnenMakkelijk(){
        return this.aantalGewonnenMakkelijk;
    }
    
    public int getAantalGewonnenGemiddeld(){
        return this.aantalGewonnenGemiddeld;
    }
    
    public int getAantalGewonnenMoeilijk(){
        return this.aantalGewonnenMoeilijk;
    }
    /*zie bovenstaande commentaar*/
    
    private void controleerWachtwoord(String wachtwoord,String wachtwoordBevestiging){
        if(!(wachtwoord.equals(wachtwoordBevestiging)))
            throw new IllegalArgumentException("Wachtwoord en wachtwoordBevestiging komen niet overeen");
        
    }
    
    public void verhoogJuisteMoeilijkheidsGraad(int moeilijkheidsGraad){
        if(moeilijkheidsGraad==1)
            this.aantalGewonnenMakkelijk++;
        else{
            if(moeilijkheidsGraad==2)
                this.aantalGewonnenGemiddeld++;
            else
                this.aantalGewonnenMoeilijk++;
        }
    }
    public int[] geefAantalSterrenEnAantalTotVolgende(int moeilijkheidsGraad){
        int[] sterren = new int[2];
        int gewonnen=0,indx = 0;
     
        switch(moeilijkheidsGraad){
            case 1: gewonnen = this.getAantalGewonnenMakkelijk(); 
            break;
            case 2: gewonnen = this.getAantalGewonnenGemiddeld();
            break;
            case 3: gewonnen = this.getAantalGewonnenMoeilijk(); 
            break;
        }

        for(int aant : Speler.aantal){
            if(gewonnen<aant){
                sterren[1] = aant-gewonnen;
                break;
            }
            indx++;
        }

        sterren[0] = indx;
        
        return sterren;
    }
}
