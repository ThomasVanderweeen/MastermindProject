/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    /* OKE?!?! hoe exceptions afhandelen???!?*/
    private void setWachtwoord(String wachtwoord) {
        try{
            if(!wachtwoord.matches("\\d{3}[a-zA-z]{6}\\d{3}"))
                throw new IllegalArgumentException();
            else
                this.wachtwoord = wachtwoord;
        }
        catch(IllegalArgumentException e){
            System.err.println("Wachtwoord fout");
        }
    }
    
    public String getNaam() {
        return this.naam;
    }

    public String getWachtwoord() {
        return this.wachtwoord;
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
}
