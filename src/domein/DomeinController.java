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
    

     /**
     * geefSpelerNaam geeft de naam van een speler terug.
     * @see Speler#getNaam() getNaam
     * @return String
     * @author Ferre
     */
    public String geefSpelerNaam(){
        return this.speler.getNaam();
    }

     /**
     * meldAan meldt een bestaande speler aan bij de mastermind applicatie. 
     * @see SpelerRepository#bestaatSpeler(java.lang.String) bestaatSpeler
     * @see SpelerRepository#geefSpeler(java.lang.String, java.lang.String) geefSpeler
     * @throws AanmeldException AanmeldException
     * @param naam String
     * @param wachtwoord String
     * @author Michiel S.
     */
    public void meldAan(String naam, String wachtwoord){
        
        boolean bestaat = spelerRepository.bestaatSpeler(naam);
        if(bestaat){
           this.speler = spelerRepository.geefSpeler(naam, wachtwoord);
        }else{
            throw new AanmeldException();
        }
        
        
    }
    

    /**
     * registreer Registreert een nieuwe speler en slaat deze op in de databank, indien de spelernaam nog niet in gebruik is. 
     * 
     * @see SpelerRepository#bestaatSpeler(java.lang.String) bestaatSpeler
     * @see SpelerRepository#voegSpelerToe(domein.Speler) voegSpelerToe
     * @throws SpelerBestaatAlException SpelerBestaatAlException
     * @author Ferre
     * @param naam String
     * @param wachtwoord String
     * @param wachtwoordBevestiging String
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
     * @param moeilijkheidsGraad Integer
     */
    public void registreerSpel(int moeilijkheidsGraad){
        this.spel = new Spel(moeilijkheidsGraad,this.speler);
        
    }
    
    /**
     * startNieuwSpel haalt het aantal gewonnen spellen per moeilijkheidsgraad op van een bepaalde speler.
     * 
     * @see Speler#getAantalGewonnenMakkelijk() getAantalGewonnenMakkelijk
     * @see Speler#getAantalGewonnenGemiddeld() getAantalGewonnenGemiddeld
     * @see Speler#getAantalGewonnenMoeilijk() getAantalGewonnenMoeilijk
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
     * @see Spel#getSpelBord() getSpelBord
     * @see Spelbord#getRijen() getRijen
     * @see Rij#getPoging() getPoging
     * @see Rij#heeftEvaluatie() heeftEvaluatie
     * @see CodePin#getKleur() getKleur
     * @see Rij#getEvaluatie() getEvaluatie
     * 
     * @author Michiel S.
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
     * @see Spel#getSpelBord() getSpelBord
     * @see Spelbord#getCode() getCode
     * @see CodePin#getKleur() getKleur
     * @author Michiel S.
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
     * @see CodePin#getGeldigeKleuren() getGeldigeKleuren
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
     * @see Spel#doePoging(int[]) doePoging
     * @see Spel#isGewonnen() isGewonnen
     * @see Spel#geefMoeilijkheidsGraad() geefMoeilijkheidsGraad
     * @see Speler#verhoogJuisteMoeilijkheidsGraad(int) verhoogJuisteMoeilijkheidsGraad
     * @see SpelerRepository#updateScore(domein.Speler, int) updateScore
     * @see Spel#getUitdagingID() getUitdagingID
     * @see UitdagingRepository#updateUitdaging(domein.Uitdaging) updateUitdaging
     * @author Ferre
     * @param poging Integer[]
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
     * @see Spel#bepaalEindeSpel() bepaalEindeSpel
     * @author Ferre
     * @return boolean 
     */
    public boolean isEindeSpel(){
        return this.spel.bepaalEindeSpel();
    }
    /**
     * isGewonnen bepaalt of de speler is gewonnen 
     * 
     * @see Spel#isGewonnen() isGewonnen
     * @author Michiel S.
     * @return boolean 
     */
    public boolean isGewonnen(){
        return this.spel.isGewonnen();
    }
    
    /**
     * slaOp slaat het spel op om later verder te spelen.
     * 
     * @see SpelRepository#controleerNaam(java.lang.String) controleerNaam
     * @see Spel#stelNaamIn(java.lang.String) stelNaamIn
     * @see SpelRepository#voegSpelToe(domein.Spel) voegSpelToe
     * 
     * @author Michiel S.
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
     * @see Spel#isGewonnen() isGewonnen
     * @see Spel#geefAantalSterrenEnAantalTotVolgende() geefAantalSterrenEnAantalTotVolgende
     * @see Spel#getAantalPogingen() getAantalPogingen
     * @see #geefCode() geefCode
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
     * @see SpelRepository#toonSpellen(java.lang.String) toonSpellen
     * @see Speler#getNaam() getNaam
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
     * @see SpelRepository#laadSpel(java.lang.String, domein.Speler) laadSpel
     * @see SpelRepository#verwijderSpel(java.lang.String) verwijderSpel
     * @author Ferre
     * @param naam  String  
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
     * @see UitdagingRepository#controleerGeenLopendeUitdaging(domein.Speler) controleerGeenLopendeUitdaging 
     * @see Speler#getAantalGewonnenMakkelijk() getAantalGewonnenMakkelijk
     * @see Speler#getAantalGewonnenGemiddeld() getAantalGewonnenGemiddeld
     * @see Speler#getAantalGewonnenMakkelijk() getAantalGewonnenMoeilijk
     * @author Michiel S.
     * @return List String[] 
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
     * @see SpelerRepository#geefBeschikbareSpelers(int, java.lang.String) geefBeschikbareSpelers
     * @see Speler#getNaam() getNaam
     * 
     * @throws NiemandBeschikbaarVoorUitdagingException NiemandBeschikbaarVoorUitdagingException
     * @author Ferre
     * @return List String[]
     * @param moeilijkheidsGraad Integer
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
     * @see UitdagingRepository#controleerGeldigeUitdaging(domein.Speler, java.lang.String) controleerGeldigeUitdaging
     * @see UitdagingRepository#voegUitdagingToe(domein.Uitdaging) voegUitdagingToe
     * @see Uitdaging#getSpel() getSpel
     * @see SpelRepository#voegSpelTegenstanderToe(domein.Spel, java.lang.String) voegSpelTegenstanderToe
     * @see SpelRepository#voegSpelToe(domein.Spel) voegSpelToe
     * @author Michiel S.
     * @param tegenstander String
     * @param moeilijkheidsGraad Integer
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
     * @see UitdagingRepository#geefNaamLopendeUitdagingTegenspeler(domein.Speler) geefNaamLopendeUitdagingTegenspeler
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
     * @see UitdagingRepository#geefLopendeUitdagingId(domein.Speler) geefLopendeUitdagingId
     * @see #geefNaamLopendeUitdagingTegenspeler() geefNaamLopendeUitdagingTegenspeler
     * @see #laadSpelLopendeUitdaging() laadSpelUitdaging
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
     * @see UitdagingRepository#accepteerUitdaging(int, domein.Speler) accepteerUitdaging
     * @see #laadSpelLopendeUitdaging() laadSpelUitdaging
     * @author Ferre
     * @param ID Integer
     * @param tegenstander String
     */
    public void laadUitdaging(int ID,String tegenstander){
        uitdagingRepository.accepteerUitdaging(ID, this.speler);
        this.laadSpelUitdaging(ID,tegenstander);
    }
    /**
     * laadSpelUitdaging creert een nieuwe uitdaging 
     * en zorgt dat die uitdaging het huidige spel wordt
     * 
     * @see SpelRepository#laadSpelUitdaging(domein.Speler, int) laadSpelUitdaging
     * @see Uitdaging#stelIDIn(int) stelIDIn
     * @see Uitdaging#getSpel() getSpel
     * @author Ferre
     * @param ID Integer
     * @param tegenstander String
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
     * @see UitdagingRepository#geefLopendeUitdagingId(domein.Speler) geefLopendeUitdagingId
     * @see #verwijderUitdaging(int) verwijderUitdaging
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
     * @see UitdagingRepository#verwijderUitdaging(int) verwijderUitdaging
     * @see SpelRepository#verwijderSpellenUitdaging(int) verwijderSpellenUitdaging
     * @author Ferre
     * @param ID Integer
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
     * @see UitdagingRepository#controleerGeenLopendeUitdaging(domein.Speler) controleerGeenLopendeUitdaging
     * @see UitdagingRepository#geefLijstUitdagingen(domein.Speler) geefLijstUitdagingen
     * @see SpelRepository#geefLopendeUitdagingInfo(java.lang.String[][]) geefLopendeUitdagingInfo
     * @throws GeenOpenstaandeUitdagingException GeenOpenstaandeUitdagingException
     * @author Michiel S.
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
     * geefKlassement geeft een lijst van stringarrays terug met daarin het de spelers in het klassement
     * 
     * @see SpelerRepository#geefKlassement(int) geefKlassement
     * @author Michiel S.
     * @param graad Integer
     * @return List(String[])
     */
    public List<String[]> geefKlassement(int graad) {
        return spelerRepository.geefKlassement(graad);
    }
    
    public void berekenScoreKlassement(){
        int id = spel.getUitdagingID();
        StringBuilder spel1 = new StringBuilder();
        spel1.append("uitdaging");
        spel1.append(id);
        spel1.append(uitdaging.getUitdager());
        String spel1Naam = spel1.toString();
        
        StringBuilder spel2 = new StringBuilder();
        spel2.append("uitdaging");
        spel2.append(id);
        spel2.append(uitdaging.getTegenstander());
        String spel2Naam = spel2.toString();
        
        Spel spelSpeler1 = spelRepository.laadSpel(spel1Naam, uitdaging.getUitdager());
        Spel spelSpeler2 = spelRepository.laadSpel(spel2Naam, spelerRepository.geefSpelerNaam(uitdaging.getTegenstander()));
        
        
        Speler speler1 = uitdaging.getUitdager();
        Speler speler2 = spelerRepository.geefSpelerNaam(uitdaging.getTegenstander());
        
        int moeilijkheidsgraad = spelSpeler1.geefMoeilijkheidsGraad();
        
        if(spelSpeler1.getAantalPogingen() > spelSpeler2.getAantalPogingen()){
            if(moeilijkheidsgraad == 1){
                speler1.voegScoreMakkelijkToe(-1);
                speler2.voegScoreMakkelijkToe(3);
            
                spelerRepository.slaScoreMakkelijkOp(speler1.getScoreMakkelijk());
                spelerRepository.slaScoreMakkelijkOp(speler2.getScoreMakkelijk());
            }
            else if(moeilijkheidsgraad == 2){
                speler1.voegScoreGemiddeldToe(-1);
                speler2.voegScoreGemiddeldToe(3);
            
                spelerRepository.slaScoreGemiddeldOp(speler1.getScoreMakkelijk());
                spelerRepository.slaScoreGemiddeldOp(speler2.getScoreMakkelijk());
            }
            else if(moeilijkheidsgraad == 3){
                speler1.voegScoreMoeilijkToe(-1);
                speler2.voegScoreMoeilijkToe(3);
            
                spelerRepository.slaScoreMoeilijkOp(speler1.getScoreMakkelijk());
                spelerRepository.slaScoreMoeilijkOp(speler2.getScoreMakkelijk());
            }
        }
        else{
            if(moeilijkheidsgraad == 1){
                speler1.voegScoreMakkelijkToe(3);
                speler2.voegScoreMakkelijkToe(-1);
            
                spelerRepository.slaScoreMakkelijkOp(speler1.getScoreMakkelijk());
                spelerRepository.slaScoreMakkelijkOp(speler2.getScoreMakkelijk());
            }
            else if(moeilijkheidsgraad == 2){
                speler1.voegScoreGemiddeldToe(3);
                speler2.voegScoreGemiddeldToe(-1);
            
                spelerRepository.slaScoreGemiddeldOp(speler1.getScoreMakkelijk());
                spelerRepository.slaScoreGemiddeldOp(speler2.getScoreMakkelijk());
            }
            else if(moeilijkheidsgraad == 3){
                speler1.voegScoreMoeilijkToe(3);
                speler2.voegScoreMoeilijkToe(-1);
            
                spelerRepository.slaScoreMoeilijkOp(speler1.getScoreMakkelijk());
                spelerRepository.slaScoreMoeilijkOp(speler2.getScoreMakkelijk());
            }
        }
    }

}
