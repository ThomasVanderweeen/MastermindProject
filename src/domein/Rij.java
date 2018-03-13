/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;
import java.util.List;

/**
 *
 * @author ThomasV
 */
public class Rij {
    List<CodePin> poging;
    List<EvaluatiePin> evaluatie;
    
    public Rij(int aantalPosities){
        for(int i=0;i<aantalPosities;i++){
            poging.add(null);
        }
    }
    
    public void doePoging(String[] poging){
        
    }

    public List<EvaluatiePin> getEvaluatie() {
        return evaluatie;
    }
    
    
    public List<CodePin> getPoging(){
        return poging;
    }
    
    public boolean heeftEvaluatie(){
        return evaluatie != null;
    }
    
    public void stelEvaluatieIn(String[] evaluatie){
        
    }
    
}
