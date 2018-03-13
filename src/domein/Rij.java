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
 * @author ThomasV
 */
public class Rij {
    private List<CodePin> poging;
    private List<EvaluatiePin> evaluatie;
    
    public Rij(int aantalPosities){
        poging = new ArrayList<>();
            for(int i=0;i<=aantalPosities-1;i++){
                poging.add(null);
            }
    }
    
    public void doePoging(String[] poging){
        
    }

    public List<EvaluatiePin> getEvaluatie() {
        return evaluatie;
    }
    
    
    public List<CodePin> getPoging(){
        return this.poging;
    }
    
    public boolean heeftEvaluatie(){
        return evaluatie != null;
    }
    
    public void stelEvaluatieIn(String[] evaluatie){
        
    }
    
}
