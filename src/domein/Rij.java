/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Groep 77
 */
public class Rij {
    private final List<CodePin> poging;
    private List<EvaluatiePin> evaluatie;
    
    /**
     * Constructor voor een rij object. 
     * 
     * @author Thomas
     * @param aantalPosities aantal positities in de rij. 
     */
    public Rij(int aantalPosities){
        poging = new ArrayList<>();
            for(int i=0;i<=aantalPosities-1;i++){
                poging.add(null);
            }
    }
    
    /**
     * doePoging zet de int[] array poging om in een CodePin[] array.
     * 
     * @author Thomas
     * @param poging int array die wordt omgezet in een codepin array.
     */
    public void doePoging(int[] poging){
        this.poging.clear(); 
        
        for(int i:poging){
            this.poging.add(new CodePin(i));
        }
    }

    /**
     * getEvaluatie returned een evaluatie van een rij object. 
     * 
     * @author Thomas
     * @return evaluatie. 
     */
    public List<EvaluatiePin> getEvaluatie() {
        return evaluatie;
    }
    
    /**
     * getPoging returned een poging van een rij object.
     * 
     * @author Thomas
     * @return List
     */
    public List<CodePin> getPoging(){
        return this.poging;
    }
    
    /**
     * heeftEvaluatie gaat na of een rij object al een evaluatie heeft.
     * 
     * @author Thomas
     * @return boolean
     */
    public boolean heeftEvaluatie(){
        return evaluatie != null;
    }
    
    /**
     * stelEvaluatieIn voegt een evaluatie toe aan een rij object. 
     * 
     * @author Thomas
     * @param evaluatie evaluatie die aan de rij wordt toegevoegd.
     */
    public void stelEvaluatieIn(int[] evaluatie){
        this.evaluatie = new ArrayList<>();
        for(int kleur : evaluatie){
            this.evaluatie.add(new EvaluatiePin(kleur));
        }
    }
    
}
