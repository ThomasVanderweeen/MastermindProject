package domein;

/**
 *
 * @author Groep 77
 */
public class Speler
{

    /*beide kunnen private*/

    private String naam;
    private String wachtwoord = null;
    private int aantalGewonnenMakkelijk = 0;
    private int aantalGewonnenGemiddeld = 0;
    private int aantalGewonnenMoeilijk = 0;
    private static final int[] aantal = {10, 20, 50, 100, 250, Integer.MAX_VALUE};
    int scoreMakkelijk = 0;
    int scoreGemiddeld = 0;
    int scoreMoeilijk = 0;

    /**
     * de constructor creert een speler
     *
     *
     * @author Ferre
     * @param gebruikersnaam String
     * @param wachtwoord String
     */
    public Speler(String gebruikersnaam, String wachtwoord) {
        this(gebruikersnaam, wachtwoord, 0, 0, 0);

    }

    /**
     * de constructor creert een speler
     *
     * @see setWachtwoord
     * @author Ferre
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @param aantalGewonnenMakkelijk Integer
     * @param aantalGewonnenGemiddeld Integer
     * @param aantalGewonnenMoeilijk Integer
     */
    public Speler(String gebruikersnaam, String wachtwoord, int aantalGewonnenMakkelijk, int aantalGewonnenGemiddeld, int aantalGewonnenMoeilijk) {
        this.naam = gebruikersnaam;
        setWachtwoord(wachtwoord);
        this.aantalGewonnenMakkelijk = aantalGewonnenMakkelijk;
        this.aantalGewonnenGemiddeld = aantalGewonnenGemiddeld;
        this.aantalGewonnenMoeilijk = aantalGewonnenMoeilijk;
    }

    /**
     * de constructor creert een speler
     *
     *
     * @author Ferre
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @param wachtwoordBevestiging String
     */
    public Speler(String gebruikersnaam, String wachtwoord, String wachtwoordBevestiging) {
        this(gebruikersnaam, wachtwoord, 0, 0, 0);
        controleerWachtwoord(wachtwoord, wachtwoordBevestiging);
    }

    /*wachtwoord bevat minstens 12 karakters eerste drie of laatste drie geen cijfer --> parse to int error --> exception
     */
    
    /**
     * setNaam stelt de naam in
     *
     *
     * @author Ferre
     * @param naam String
     */
    public void setNaam(String naam) {
        this.naam = naam;

    }
    /**
     * getWachtwoord geeft het wachtwoord terug
     *
     *
     * @author Ferre
     * @return String wachtwoord
     */
    public String getWachtwoord() {
        return this.wachtwoord;
    }

    /* OKE?!?! hoe exceptions afhandelen???!?*/
    
    /**
     * setWachtwoord stelt het wachtwoord in
     *
     * @throws IllegalArgumentException
     * @author Ferre
     * @param wachtwoord
     */
    private void setWachtwoord(String wachtwoord) {

        if (!wachtwoord.matches("\\d{1}[a-zA-z]{6,}\\d{1}")) {
            throw new IllegalArgumentException();
        } else {
            this.wachtwoord = wachtwoord;
        }

    }
    /**
     * getNaam geeft de naam terug
     *
     * 
     * @author Ferre
     * @return String
     */
    public String getNaam() {
        return this.naam;
    }
    /**
     * getAantalGewonnenMakkelijk geeft het aantal gewonnen makkelijke spellen terug
     *
     * @author Ferre
     * @return int 
     */
    public int getAantalGewonnenMakkelijk() {
        return this.aantalGewonnenMakkelijk;
    }
    /**
     * getAantalGewonnenGemiddeld geeft het aantal gewonnen gemiddelde spellen terug
     *
     * @author Ferre
     * @return int 
     */
    public int getAantalGewonnenGemiddeld() {
        return this.aantalGewonnenGemiddeld;
    }
    /**
     * getAantalGewonnenMoeilijk geeft het aantal gewonnen moeilijke spellen terug
     *
     * @author Ferre
     * @return int 
     */
    public int getAantalGewonnenMoeilijk() {
        return this.aantalGewonnenMoeilijk;
    }

    /*zie bovenstaande commentaar*/
    /**
     * controleerWachtwoord controleerd of het wachtwoord overeenkomt
     * met de wachtwoordbevestiging
     *
     * @throws IllegalArgumentException
     * @author Ferre
     * @param wachtwoord
     * @param wachtwoordBevestiging
     */
    private void controleerWachtwoord(String wachtwoord, String wachtwoordBevestiging) {
        if (!(wachtwoord.equals(wachtwoordBevestiging))) {
            throw new IllegalArgumentException("Wachtwoord en wachtwoordBevestiging komen niet overeen");
        }

    }
    /**
     * verhoogJuisteMoeilijkheidsGraad zorgt dat bij het winnen van een spel
     * de juiste moeilijkheidsgraad word gekozen om een gewonnen spel bij op te tellen
     *
     * 
     * @author Ferre
     * @param moeilijkheidsGraad Integer
     */
    public void verhoogJuisteMoeilijkheidsGraad(int moeilijkheidsGraad) {
        if (moeilijkheidsGraad == 1) {
            this.aantalGewonnenMakkelijk++;
        } else {
            if (moeilijkheidsGraad == 2) {
                this.aantalGewonnenGemiddeld++;
            } else {
                this.aantalGewonnenMoeilijk++;
            }
        }
    }
    /**
     * geefAantalSterrenEnAantalTotVolgende geeft terug hoeveel sterren
     * de speler heeft op basis van zijn aantal gewonnen spellen,
     * en ook hoeveel keer hij nog moet winnen om zijn volgende ster te behalen
     *
     * @see getAantalGewonnenMakkelijk
     * @see getAantalGewonnenGemiddeld
     * @see getAantalGewonnenMoeilijk
     * 
     * @author Ferre
     * @param moeilijkheidsGraad int
     * @return int[]
     */
    public int[] geefAantalSterrenEnAantalTotVolgende(int moeilijkheidsGraad) {
        int[] sterren = new int[2];
        int gewonnen = 0, indx = 0;

        switch (moeilijkheidsGraad) {
            case 1:
                gewonnen = this.getAantalGewonnenMakkelijk();
                break;
            case 2:
                gewonnen = this.getAantalGewonnenGemiddeld();
                break;
            case 3:
                gewonnen = this.getAantalGewonnenMoeilijk();
                break;
        }

        for (int aant : Speler.aantal) {
            if (gewonnen < aant) {
                sterren[1] = aant - gewonnen;
                break;
            }
            indx++;
        }

        sterren[0] = indx;

        return sterren;
    }

    public int getScoreMakkelijk() {
        return scoreMakkelijk;
    }

    public int getScoreGemiddeld() {
        return scoreGemiddeld;
    }

    public int getScoreMoeilijk() {
        return scoreMoeilijk;
    }

    
    public void voegScoreMakkelijkToe(int aantal){
        this.scoreMakkelijk += aantal;
    }
    
    public void voegScoreGemiddeldToe(int aantal){
        this.scoreGemiddeld += aantal;
    }
    
    public void voegScoreMoeilijkToe(int aantal){
        this.scoreMoeilijk += aantal;
    }
}
