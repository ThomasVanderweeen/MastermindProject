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
     * @see getNaam
     * @return String
     * @author Ferre
     */
    public String geefSpelerNaam(){
        return this.speler.getNaam();
    }
    
    /*
        opgebast geef leeg object door indien speler niet bestaat geen exception moet nog gefix worden
    */
     /**
     * meldAan meldt een bestaande speler aan bij de mastermind applicatie. 
     * @see bestaatSpeler
     * @see geefSpeler
     * @throws AanmeldException
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
     * @see bestaatSpeler
     * @see voegSpelerToe
     * @throws SpelerBestaatAlException
     * @author Ferre
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
     * @author Ferre
     * @param moeilijkheidsGraad de moeilijkheidsgraad van het spel dat wordt aangemaakt.
     */
    public void registreerSpel(int moeilijkheidsGraad){
        this.spel = new Spel(moeilijkheidsGraad,this.speler);
        
    }
    
    /**
     * startNieuwSpel haalt het aantal gewonnen spellen per moeilijkheidsgraad op van een bepaalde speler.
     * 
     * @see getAantalGewonnenMakkelijk
     * @see getAantalGewonnenGemiddeld
     * @see getAantalGewonnenMoeilijk
     * @see add
     * @author Ferre
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
     * @see getSpelBord
     * @see getRijen
     * @see getPoging
     * @see heeftEvaluatie
     * @see getKleur
     * @see getEvaluatie
     * 
     * @author Ferre
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
     * 
     * @see getSpelBord
     * @see getCode
     * @see getKleur
     * @author Ferre
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
     * @see getGeldigeKleuren
     * @author Ferre
     * @return String[]
     */
    public String[] geefKleuren(){
        return CodePin.getGeldigeKleuren();
    }
    
    /**
     * doePoging zorgt dat als de speler een zet/poging doet, 
     * deze wordt geregistreerd, en als de speler met die zet wint
     * wordt het aantal wins voor betreffende moeilijkheidsgraad verhoogd met 1.
     * 
     * @see doePoging
     * @see isGewonnen
     * @see geefMoeilijkheidsGraad
     * @see verhoogJuisteMoeilijkheidsGraad
     * @see updateScore
     * @see getUitdagingID
     * @see updateUitdaging
     * 
     * 
     * @author Ferre
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
    /**
     * isEindeSpel bepaalt of het einde van het spel is bereikt 
     * 
     * @see bepaalEindeSpel
     * @author Ferre
     * @return boolean 
     */
    public boolean isEindeSpel(){
        return this.spel.bepaalEindeSpel();
    }
    /**
     * isGewonnen bepaalt of de speler is gewonnen 
     * 
     * @see isGewonnen
     * @author Ferre
     * @return boolean 
     */
    public boolean isGewonnen(){
        return this.spel.isGewonnen();
    }
    
    /**
     * slaOp slaat het spel op om later verder te spelen.
     * 
     * @see controleerNaam
     * @see stelNaamIn
     * @see voegSpelToe
     * 
     * @author Ferre
     * @param naam dit is de naam van het spel 
     */
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
    /**
     * geefEindSituatie geeft de gegevens van het spel door, na het spel in een 
     * dubbele array te zetten 
     * wanneer het spel is gewonnen
     * 
     * @see isGewonnen
     * @see geefAantalSterrenEnAantalTotVolgende
     * @see toString
     * @see getAantalPogingen
     * @see geefCode
     * @author Ferre
     * @return String[][]  
     */
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
    /**
     * geefOpgeslagenSpellen geeft een 2dimensionale array terug waarin alle 
     * opgeslagen spellen van de huidige speler staan
     * 
     * @see toonSpellen
     * @see getNaam
     * @author Ferre
     * @return String[][]  
     */
    public String[][] geefOpgeslagenSpellen(){
        return spelRepository.toonSpellen(this.speler.getNaam());
    }
    /**
     * selecteerSpel stelt het huidige spel in op het via de parameter 
     * geselecteerde opgeslagen spel, daarna wordt het geselecteerde spel
     * uit de database verwijderd
     * 
     * @see laadSpel
     * @see verwijderSpel
     * @author Ferre
     * @param naam   
     */
    public void selecteerSpel(String naam){
        this.spel = spelRepository.laadSpel(naam, speler);
        spelRepository.verwijderSpel(naam);
    } 
    /**
     * geefMoeilijkheidsGraden geeft een lijst van string arrays terug die
     * de beschikbare moeilijkheidsgraden en het aantal gewonnen spellen voor 
     * die moeilijkheidsgraad bevatten indien de speler momenteel
     * geen lopende uitdaging heeft
     * 
     * @see controleerGeenLopendeUitdaging
     * 
     * @see getAantalGewonnenMakkelijk
     * 
     * @see getAantalGewonnenGemiddeld
     * @see getAantalGewonnenMoeilijk
     * @author Ferre
     * @return List<String[]> 
     */
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
    /**
     * selecteerMoeilijkheidsGraadUitdaging geeft een lijst van spelers-arrays terug
     * die beschikbaar zijn om uit te dagen in de geselecteerde moeilijkheidsgraad
     * 
     * @see geefBeschikbareSpelers
     * @see getNaam
     * 
     * @throws NiemandBeschikbaarVoorUitdagingException
     * @author Ferre
     * @return List<String[]> 
     * @param moeilijkheidsGraad
     */
    public List<String[]> selecteerMoeilijkheidsGraadUitdaging(int moeilijkheidsGraad){
        
        List<String[]> spelers = spelerRepository.geefBeschikbareSpelers(moeilijkheidsGraad,this.speler.getNaam());
        if(spelers.size()>0)
            return spelers;
        else
            throw new NiemandBeschikbaarVoorUitdagingException();
    }
    /**
     * startUitdaging creëerd een nieuwe uitdaging waarbij de tegenstander en
     * de moeilijkheidsgraad worden meegegeven
     *  
     * @see controleerGeldigeUitdaging
     * @see voegUitdagingToe
     * @see getSpel
     * @see voegSpelTegenstanderToe
     * @see voegSpelToe
     * @author Ferre
     * @param tegenstander 
     * @param moeilijkheidsGraad
     */
    public void startUitdaging(String tegenstander, int moeilijkheidsGraad){
        uitdagingRepository.controleerGeldigeUitdaging(this.speler,tegenstander);
        
        this.uitdaging = new Uitdaging(this.speler,tegenstander,moeilijkheidsGraad);
        uitdagingRepository.voegUitdagingToe(this.uitdaging);
        
        this.spel = this.uitdaging.getSpel();
        spelRepository.voegSpelTegenstanderToe(spel, tegenstander);
        
        /*indien de speler de build stopt moet de uitdaging nog steeds geladen kunnen worden*/
        spelRepository.voegSpelToe(this.spel);
        
    }
    /**
     * geefNaamLopendeUitdagingTegenspeler geeft de naam van de tegenstander
     * in de huidige uitdaging terug
     *  
     * @see geefNaamLopendeUitdagingTegenspeler
     * @author Ferre
     * @return String
     */
    public String geefNaamLopendeUitdagingTegenspeler(){
        return uitdagingRepository.geefNaamLopendeUitdagingTegenspeler(this.speler);
    }
    /**
     * laadSpelLopendeUitdaging haalt het id en de naam van de tegenstander 
     * van de lopende uitdaging van de 
     * gebruiker op en geeft deze door naar laadUitdaging
     *  
     * @see geefLopendeUitdagingId
     * @see geefNaamLopendeUitdagingTegenspeler
     * @see laadSpelUitdaging
     * @author Ferre
     */
    public void laadSpelLopendeUitdaging(){
        int ID = uitdagingRepository.geefLopendeUitdagingId(this.speler);
        String tegenstander = geefNaamLopendeUitdagingTegenspeler();
        laadSpelUitdaging(ID,tegenstander);
    }
    /**
     * laadUitdaging krijgt de nodige gegevens om een uitdaging te onderscheiden,
     * zorgt dat in de database de uitdaging wordt geaccepteerd
     * en roept dan laadSpelUitdaging op
     * 
     * @see accepteerUitdaging
     * @see laadSpelUitdaging
     * @author Ferre
     * @param ID
     * @param tegenstander
     */
    public void laadUitdaging(int ID,String tegenstander){
        uitdagingRepository.accepteerUitdaging(ID, this.speler);
        this.laadSpelUitdaging(ID,tegenstander);
    }
    /**
     * laadSpelUitdaging creert een nieuwe uitdaging 
     * en zorgt dat die uitdaging het huidige spel wordt
     * 
     * @see laadSpelUitdaging
     * @see stelIDIn
     * @see getSpel
     * @author Ferre
     * @param ID
     * @param tegenstander
     */
    private void laadSpelUitdaging(int ID,String tegenstander){
        Spel sp = spelRepository.laadSpelUitdaging(speler, ID);
        
        this.uitdaging = new Uitdaging(tegenstander,sp);
        this.uitdaging.stelIDIn(ID);

        this.spel = this.uitdaging.getSpel();
    }
    /**
     * verwijderLopendeUitdaging haalt het id van de huidige uitdaging op
     * en roept verwijderUitdaging op
     * 
     * @see geefLopendeUitdagingId
     * @see verwijderUitdaging
     * @author Ferre
     */
    public void verwijderLopendeUitdaging(){
        int ID = uitdagingRepository.geefLopendeUitdagingId(speler);
        verwijderUitdaging(ID);
    }
    /**
     * verwijderUitdaging verwijderd de uitdaging uit de database, 
     * en ook het spel dat voor die uitdaging was gemaakt
     * 
     * @see verwijderUitdaging
     * @see verwijderSpellenUitdaging
     * @author Ferre
     * @param ID
     */
    public void verwijderUitdaging(int ID){
        uitdagingRepository.verwijderUitdaging(ID);
        spelRepository.verwijderSpellenUitdaging(ID);
    }
    /**
     * geefLijstUitdagingen controleerd of de gebruiker een uitdaging heeft
     * open staan, als dit niet het geval is wordt een 
     * 2dimensionale array met daarin de lijst van uitdagingen
     * 
     * @see controleerGeenLopendeUitdaging
     * @see geefLijstUitdagingen
     * @see geefLopendeUitdagingInfo
     * @throws GeenOpenstaandeUitdagingException
     * @author Ferre
     * @return String[][]
     */
    public String[][] geefLijstUitdagingen(){
        uitdagingRepository.controleerGeenLopendeUitdaging(this.speler);
        String[][] uitdagersEnID =  uitdagingRepository.geefLijstUitdagingen(this.speler);
        
        if(uitdagersEnID.length>0)
            return spelRepository.geefLopendeUitdagingInfo(uitdagersEnID);
        else
            throw new GeenOpenstaandeUitdagingException();
    }
    /**
     * geefKlassement geeft een lijst van stringarrays terug met daarin de ....
     * 
     * @see geefKlassement
     * @author Ferre
     * @param graad
     * @return List<String[]>
     */
    public List<String[]> geefKlassement(int graad) {
        return spelerRepository.geefKlassement(graad);
    }

}
