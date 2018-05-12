package domein;

import java.util.List;
import java.util.ArrayList;
import exceptions.OngeldigePogingException;
/**
 *
 * @author Groep 77
 */
public class Spelbord {
    private final Code code;
    private final Rij[] rijen;
    private boolean codeGeraden = false;
    
    /**
     * constructor voor een Spelbord object.
     * 
     * @see vulRijenOp
     * @see getCode
     * @author Ferre
     * @param code code van het spel.
     */
    public Spelbord(Code code){
        this.code = code;
        int lengte = this.code.getCode().size();
        this.rijen = new Rij[12];
        vulRijenOp(this.code.getCode().size());
    }
    
    /**
     * constructor voor een Spelbord object.
     * 
     * @see vulRijenOp
     * @see getCode
     * @author Ferre
     * @param moeilijkheidsGraad moeilijkdsgraad van het spel.
     */
    public Spelbord(int moeilijkheidsGraad){
        this.code = new Code(moeilijkheidsGraad);
        int lengte = this.code.getCode().size();
        this.rijen = new Rij[12];
        vulRijenOp(this.code.getCode().size());
    }
    
    /**
     * getCode geeft de code van een spelbord object.
     * 
     * @see getCode
     * @author Ferre
     * @return List(CodePin) List(CodePin)
     */
    public List<CodePin> getCode(){
       return this.code.getCode();
    }

    /**
     * vulRijenOp vult een rij op met ints.
     * 
     * @author Ferre
     * @param lengte de lengte van de rij.
     */
    private void vulRijenOp(int lengte){
        for(int i=0;i<rijen.length;i++){
            this.rijen[i] = new Rij(lengte);
        }
    }
    
    /**
     * getRijen geeft de rijen van een spelbord. 
     * 
     * @author Ferre
     * @return Rij[]
     */
    public Rij[] getRijen(){
        return this.rijen;
    }

    /**
     * getCodeGeraden geeft true weer als de code geraden is, en anders false.
     * 
     * @author Ferre
     * @return boolean
     */
    public boolean getCodeGeraden(){
        return this.codeGeraden;
    }
    
    /**
     * voegPogingToe voegt een poging toe aan een rij.
     * 
     * @see geldigePoging
     * @see doePoging
     * @see evalueerPoging
     * @author Ferre
     * @param poging poging die wordt toegevoegd.
     * @param rij rij waaraan de poging wordt toegevoegd.
     */
    public void voegPogingToe(int[] poging , int rij){
        geldigePoging(poging);
        this.rijen[rij].doePoging(poging);
        evalueerPoging(rij);
    }
    
    /**
     * geldigePoging controleert of de poging geldig is door de lengte te controleren.
     * 
     * @see geefAantalPosities
     * @throws OngeldigePogingException
     * @author Ferre
     * @param poging poging die gecontroleerd wordt. 
     */
    private void geldigePoging(int[] poging){
        if(poging.length!= this.code.geefAantalPosities())
            throw new OngeldigePogingException("Het aantal posities van de "
                    + "poging is ontoereikend.");
    }
    
    /**
     * evalueerPoging evalueert de poging op basis van de moeilijkheidsgraad. 
     * Makkelijk: juiste locatie en kleur = zwart, juiste kleur = wit, niets = leeg
     * Normaal/Moeilijk: zelfde als makkelijk maar de evaluatiepinnen worden niet meer op de juiste plaats gezet.
     * 
     * @see getPoging
     * @see getKleur
     * @see getCode
     * @see getMoeilijkheidsGraad
     * @see stelEvaluatieIn
     * @author Ferre
     * @param rij rij die wordt geëvalueerd
     */
    public void evalueerPoging(int rij){
        /*krijg de kleuren terug van de poging*/
        List<CodePin> pogingPinnen = this.rijen[rij].getPoging();
        String[] kleurenPoging = new String[pogingPinnen.size()];
    
        for(int i=0;i<pogingPinnen.size();i++){
            kleurenPoging[i] = pogingPinnen.get(i).getKleur();
        }

        /*krijg de kleuren van de code terug en steek ze in een array*/
        List<CodePin> codePinnen = this.code.getCode();
        List<String> codeKleuren = new ArrayList<>();
        
        for(CodePin cp: codePinnen){
            codeKleuren.add(cp.getKleur());
        }
        
        int[] evaluatie = new int[codeKleuren.size()];
        /*geef moeilijkheidsgraad evaluatie is anders per moeilijkheidsgraad*/
        MoeilijkheidsGraad graad = this.code.getMoeilijkheidsGraad();
        
        /*gemakelijke evaluatie*/
            int i =0,zwart=0,rood,wit=0;
            
            for(String kleur:kleurenPoging){
                if(kleur.equals(codeKleuren.get(i))){
                    evaluatie[i] = 0;
                    zwart++;
                }
                else{
                    if(codeKleuren.indexOf(kleur)!=-1){
                        evaluatie[i] = 1;
                        wit++;
                    }
                    else
                        evaluatie[i] = 2;
                }
                i++;
            }
            
            rood = evaluatie.length -zwart - wit;
            
        
       /*sorteert de gemakelijke evaluatie*/
       if(graad instanceof Gemiddeld||graad instanceof Moeilijk){
           for(int j=0;j<zwart;j++){
               evaluatie[j] = 0;
           }
           for(int x=zwart;x<evaluatie.length-rood;x++){
               evaluatie[x] = 1;
           }
           for(int y=evaluatie.length-rood;y<evaluatie.length;y++){
               evaluatie[y]=2;
           }
       }
       
       if(zwart == evaluatie.length){
           codeGeraden = true;
       }
        
        this.rijen[rij].stelEvaluatieIn(evaluatie);
    }
    
    /**
     * geefMoeilijkheidsGraad geeft de moeilijkheidsgraad van een spelbord object.
     * 
     * @see getMoeilijkheidsGraad
     * @author Ferre
     * @return int
     */
    public int geefMoeilijkheidsGraad(){
        MoeilijkheidsGraad mg = this.code.getMoeilijkheidsGraad();
        
        if(mg instanceof Gemakkelijk)
            return 1;
        else{
            if(mg instanceof Gemiddeld)
                return 2;
            else
                return 3;
        }
    }
}
