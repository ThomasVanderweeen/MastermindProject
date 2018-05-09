/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import exceptions.AanmeldException;
import exceptions.GeenOpenstaandeUitdagingException;
import exceptions.SpelerBestaatAlException;
import exceptions.NiemandBeschikbaarVoorUitdagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Groep 77
 */
public class DomeinController {
    private static UitdagingRepository uitdagingRepository;
    private static SpelerRepository spelerRepository;
    private Speler speler;
    private Spel spel;
    private Uitdaging uitdaging;
    private static SpelRepository spelRepository;

    /**
     * Constructor voor een DomeinController object. Binnen de constructor wordt een speler- en spelrepository object aangemaakt.
     */
    public DomeinController() {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
        uitdagingRepository = new UitdagingRepository();
    }
    
    /*
    Er kan nog een exceptie optreden deze functie aanroepen voor speler bestaat.
    */
     /**
     * geefSpelerNaam geeft de naam van een speler terug.
     * 
     * @return String
     */
    public String geefSpelerNaam(){
        return this.speler.getNaam();
    }
    
    /*
        opgebast geef leeg object door indien speler niet bestaat geen exception moet nog gefix worden
    */
     /**
     * meldAan meldt een bestaande speler aan bij de mastermind applicatie. 
     * 
     * @param naam naam van de speler die wordt aangemeld.
     * @param wachtwoord wachtwoord van de speler die wordt aangemeld.
     */
    public void meldAan(String naam, String wachtwoord){
        
        boolean bestaat = spelerRepository.bestaatSpeler(naam);
        if(bestaat){
           this.speler = spelerRepository.geefSpeler(naam, wachtwoord);
        }else{
            throw new AanmeldException();
        }
        
        
    }
    
    /*
    Ook nog geen exception geworpen indien speler wel al bestaat nog implementeren HOE?!
    */
    /**
     * registreer Registreert een nieuwe speler en slaat deze op in de databank, indien de spelernaam nog niet in gebruik is. 
     * 
     * @param naam naam van de speler.
     * @param wachtwoord wachtwoord van de speler. 
     * @param wachtwoordBevestiging bevestiging van het wachtwoord, moet hetzelfde zijn als het wachtwoord.
     */
    public void registreer(String naam, String wachtwoord, String wachtwoordBevestiging){
        boolean bestaat = spelerRepository.bestaatSpeler(naam);

            if(!bestaat){
                this.speler = new Speler(naam,wachtwoord,wachtwoordBevestiging);
                spelerRepository.voegSpelerToe(speler);
            }   
            else{
                throw new SpelerBestaatAlException();
            }
    }   
    
     /**
     * registreerSpel maakt een spel Object aan met een bepaalde moeilijkheidsgraad en een speler object.
     * 
     * @param moeilijkheidsGraad de moeilijkheidsgraad van het spel dat wordt aangemaakt.
     */
    public void registreerSpel(int moeilijkheidsGraad){
        this.spel = new Spel(moeilijkheidsGraad,this.speler);
        
    }
    
    /**
     * startNieuwSpel haalt het aantal gewonnen spellen per moeilijkheidsgraad op van een bepaalde speler.
     * 
     * @return List Integer
     */
    public List<Integer> startNieuwSpel(){
        List<Integer> gewonnenLijst = new ArrayList<>();
        
        gewonnenLijst.add(this.speler.getAantalGewonnenMakkelijk());
        gewonnenLijst.add(this.speler.getAantalGewonnenGemiddeld());
        gewonnenLijst.add(this.speler.getAantalGewonnenMoeilijk());
        
        return gewonnenLijst;
    }

    /**
     * geefSpelbord geeft het spelbord weer.
     * 
     * @return String[][]
     */
    public String [][] geefSpelBord(){
        String[][] spelbord = new String[12][];
        int rijnr = 0,lengte,inner=0;
        
        Spelbord sp = this.spel.getSpelBord();
        Rij[] rijen = sp.getRijen();
        
        for(Rij r:rijen){
            lengte = r.getPoging().size();
            if(r.heeftEvaluatie())
                lengte += r.getEvaluatie().size();
            spelbord[rijnr] = new String[lengte];
            
            for(CodePin cp : r.getPoging()){
                if(cp ==null)
                    spelbord[rijnr][inner] = "leeg";
                else
                    spelbord[rijnr][inner] = cp.getKleur();
                inner++;
            }
            
            if(r.heeftEvaluatie()){
                for(EvaluatiePin ep: r.getEvaluatie()){
                    spelbord[rijnr][inner] = ep.getKleur();
                     inner++;
                }
            }
            
            inner = 0;
            rijnr++;
        }
     
        return spelbord;
    }
    
    /**
     * geefCode zet de code om naar een String voor output naar de ui.
     * 
     * @return String[]
     */
    public String[] geefCode(){
        
        Spelbord sp = this.spel.getSpelBord();
        List<CodePin> code =  sp.getCode();
        
        String[] res = new String[code.size()];
        int teller = 0;
        
        for(CodePin cp: code){
            res[teller] = cp.getKleur();
            teller++;
        }
        
        return res;
    }

    
    /**
     * geefKleuren haalt een array met alle geldige kleuren voor de codepins op.
     * 
     * @return String[]
     */
    public String[] geefKleuren(){
        return CodePin.getGeldigeKleuren();
    }
    
    /**
     * doePoging 
     * 
     * @param poging 
     */
    public void doePoging(int[] poging){
        this.spel.doePoging(poging);
        if(isGewonnen()){
            int moeilijkheidsGraad = this.spel.geefMoeilijkheidsGraad();
            this.speler.verhoogJuisteMoeilijkheidsGraad(moeilijkheidsGraad);
            spelerRepository.updateScore(this.speler, moeilijkheidsGraad);
            if(this.spel.getUitdagingID()!=-1)
                uitdagingRepository.updateUitdaging(this.uitdaging);
        }
    }
    
    public boolean isEindeSpel(){
        return this.spel.bepaalEindeSpel();
    }
    
    public boolean isGewonnen(){
        return this.spel.isGewonnen();
    }
    

    public void slaOp(String naam){
        try{
            if(!(spelRepository.controleerNaam(naam))){
                this.spel.stelNaamIn(naam);
                spelRepository.voegSpelToe(this.spel);
            }
        }catch(Exception e){
            throw(e);
        }
            
    }
    
    public String[][] geefEindSituatie(){
        String[][] eindSituatie = new String[3][4];
        
        int teller = 0;
        if(this.spel.isGewonnen() == true){
           for(int i: this.spel.geefAantalSterrenEnAantalTotVolgende()){
               eindSituatie[0][teller] = Integer.toString(i);
               teller++;
           }
           eindSituatie[1][0] = Integer.toString(this.spel.getAantalPogingen());
           eindSituatie[2] = geefCode();
        }
        return eindSituatie;
    }
    
    public String[][] geefOpgeslagenSpellen(){
        return spelRepository.toonSpellen(this.speler.getNaam());
    }
    
    public void selecteerSpel(String naam){
        this.spel = spelRepository.laadSpel(naam, speler);
        spelRepository.verwijderSpel(naam);
    } 
    
    public List<String[]> geefMoeilijkheidsGraden(){
        uitdagingRepository.controleerGeenLopendeUitdaging(this.speler);
        List<String[]> res = new ArrayList<>();
        
        String[] moeilijkheidsGraad = {"makkelijk",
            Integer.toString(this.speler.getAantalGewonnenMakkelijk())};
        res.add(moeilijkheidsGraad);
        
        if(this.speler.getAantalGewonnenMakkelijk()>=20){
           String[] x = {"gemiddeld", Integer.toString(this.speler.getAantalGewonnenGemiddeld())};
           res.add(x);
            
            if(this.speler.getAantalGewonnenGemiddeld()>=20){
                String[] y = {"moeilijk", Integer.toString(this.speler.getAantalGewonnenMoeilijk())};
                res.add(y);
            }
        }
        
        return res;
    }
    
    public List<String[]> selecteerMoeilijkheidsGraadUitdaging(int moeilijkheidsGraad){
        
        List<String[]> spelers = spelerRepository.geefBeschikbareSpelers(moeilijkheidsGraad,this.speler.getNaam());
        if(spelers.size()>0)
            return spelers;
        else
            throw new NiemandBeschikbaarVoorUitdagingException();
    }
    
    public void startUitdaging(String tegenstander, int moeilijkheidsGraad){
        uitdagingRepository.controleerGeldigeUitdaging(this.speler,tegenstander);
        
        this.uitdaging = new Uitdaging(this.speler,tegenstander,moeilijkheidsGraad);
        uitdagingRepository.voegUitdagingToe(this.uitdaging);
        
        this.spel = this.uitdaging.getSpel();
        spelRepository.voegSpelTegenstanderToe(spel, tegenstander);
        
        /*indien de speler de build stopt moet de uitdaging nog steeds geladen kunnen worden*/
        spelRepository.voegSpelToe(this.spel);
        
    }
    
    public String geefNaamLopendeUitdagingTegenspeler(){
        return uitdagingRepository.geefNaamLopendeUitdagingTegenspeler(this.speler);
    }
    
    public void laadSpelLopendeUitdaging(){
        int ID = uitdagingRepository.geefLopendeUitdagingId(this.speler);
        String tegenstander = geefNaamLopendeUitdagingTegenspeler();
        laadSpelUitdaging(ID,tegenstander);
    }
    
    public void laadUitdaging(int ID,String tegenstander){
        uitdagingRepository.accepteerUitdaging(ID, this.speler);
        this.laadSpelUitdaging(ID,tegenstander);
    }
    
    private void laadSpelUitdaging(int ID,String tegenstander){
        Spel sp = spelRepository.laadSpelUitdaging(speler, ID);
        
        this.uitdaging = new Uitdaging(tegenstander,sp);
        this.uitdaging.stelIDIn(ID);

        this.spel = this.uitdaging.getSpel();
    }
    
    public void verwijderLopendeUitdaging(){
        int ID = uitdagingRepository.geefLopendeUitdagingId(speler);
        verwijderUitdaging(ID);
    }
    
    public void verwijderUitdaging(int ID){
        uitdagingRepository.verwijderUitdaging(ID);
        spelRepository.verwijderSpellenUitdaging(ID);
    }
    
    public String[][] geefLijstUitdagingen(){
        uitdagingRepository.controleerGeenLopendeUitdaging(this.speler);
        String[][] uitdagersEnID =  uitdagingRepository.geefLijstUitdagingen(this.speler);
        
        if(uitdagersEnID.length>0)
            return spelRepository.geefLopendeUitdagingInfo(uitdagersEnID);
        else
            throw new GeenOpenstaandeUitdagingException();
    }
    

}
