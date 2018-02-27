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

    public Speler(String gebruikersnaam, String wachtwoord) {
        this.naam = gebruikersnaam;
        setWachtwoord(wachtwoord);
    }

    public Speler(String gebruikersnaam, String wachtwoord,String wachtwoordBevestiging) {
        this(gebruikersnaam,wachtwoord);
        controleerWachtwoord(wachtwoord,wachtwoordBevestiging);
    }
    
    /*wachtwoord bevat minstens 12 karakters eerste drie of laatste drie geen cijfer --> parse to int error --> exception
    */
    
    /* OKE?!?! hoe exceptions afhandelen???!?*/
    private void setWachtwoord(String wachtwoord){
        int lengte = wachtwoord.length();
        if(lengte<12)
            throw new IllegalArgumentException("wachtwoord moet minstens zes karakters en drie cijfers  van voor en van achter bevatten");
        try{
            /*ik zie niets beter in? om dit te doen??  */
            Integer.parseInt(wachtwoord.substring(0, 3));
            Integer.parseInt(wachtwoord.substring(lengte-3,lengte));
            
        }catch(Exception e){
            throw new IllegalArgumentException("fout in cijfers wachtwoord");
        }
        
        this.wachtwoord = wachtwoord;
    }
    
    public String getNaam() {
        return this.naam;
    }

    public String getWachtwoord() {
        return this.wachtwoord;
    }
    

    /*zie bovenstaande commentaar*/
    
    private void controleerWachtwoord(String wachtwoord,String wachtwoordBevestiging){
        if(!(wachtwoord.equals(wachtwoordBevestiging)))
            throw new IllegalArgumentException("Wachtwoord en wachtwoordBevestiging komen niet overeen");
        
    }
}
