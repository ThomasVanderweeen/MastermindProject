package domein;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author Michiel S.
 */
public class Spelbord {
    
    private MoeilijkheidsGraad moeilijkheidsGraad;
    private List<String> code;
    private List<List<String>> pogingen;
    private int aantalPogingen;
    
    public Spelbord(int moeilijkheidsGraad){
        this.pogingen = new ArrayList<>();
        setCode();
        maakSpelbord();
    }

    private void setCode(){
        this.code = this.moeilijkheidsGraad.genereerCode();
    }
    

    
    private void maakSpelbord(){
        int aantalRijen = 12;
        
        for(int i=0;i<aantalRijen;i++){
            List<String> rij = new ArrayList<>();
            
            for(int j=0;i<code.size();i++){
                rij.add("0");
           }
            
            this.pogingen.add(rij);
        }
    }
    
    public List<String> getCode(){
        System.out.println("code:"+code);
        return this.code;
    }
    
    public List<List<String>> getPogingen(){
        return this.pogingen;
    }

    public MoeilijkheidsGraad getMoeilijkheidsGraad() {
        return moeilijkheidsGraad;
    }

    public int getAantalPogingen() {
        return aantalPogingen;
    }
    
    
//    public ArrayList<EvaluatiePin> evalueerPoging(CodePin[] poging, CodePin[] code, MoeilijkheidsGraad moeilijkheidsGraad){
//        
//        ArrayList<EvaluatiePin> evaluatie = new ArrayList();
//        moeilijkheidsGraad = this.getMoeilijkheidsGraad();
//        if(moeilijkheidsGraad instanceof Gemakkelijk){
//            for(int i = 0; i < code.length ; i++){
//                if(poging[i] == code[i]){
//                    evaluatie.add(new EvaluatiePin("Zwart"));
//                }
//                else{
//                    for (int j = 0; i < code.length; i++){
//                        if(poging[i].getKleur().equals(code[j].getKleur())){
//                        evaluatie.add(new EvaluatiePin("Wit"));
//                        }
//                        else{
//                        evaluatie.add(new EvaluatiePin("Rood"));
//                        }
//                    }
//                } 
//                    
//            }
//        }
//        else if(moeilijkheidsGraad instanceof Gemiddeld || moeilijkheidsGraad instanceof Moeilijk){
//            for (int i = 0; i < code.length; i++){
//                if(poging[i].equals(code[i])){
//                    if(evaluatie.isEmpty())
//                        evaluatie.add(new EvaluatiePin("Zwart"));
//                    else if(evaluatie.contains("Zwart")){
//                        int index = evaluatie.lastIndexOf("Zwart");
//                        evaluatie.add(index, new EvaluatiePin("Zwart"));
//                    }
//                }
//                 else{
//                    for (int j = 0; i < code.length; i++){
//                        if(poging[i].getKleur().equals(code[j].getKleur())){
//                            evaluatie.add(new EvaluatiePin("Wit"));
//                        }
//                        else{
//                        evaluatie.add(new EvaluatiePin("Rood"));
//                        }
//                    }
//                } 
//            }
//        }
//            
//            
//        
//        return evaluatie;
//    }
    
    public ArrayList<String> evalueerPoging(String[] poging, String[] code, MoeilijkheidsGraad moeilijkheidsGraad){
        ArrayList<String> evaluatie = new ArrayList(code.length);
        moeilijkheidsGraad = this.getMoeilijkheidsGraad();
        if(moeilijkheidsGraad instanceof Gemakkelijk){
            for(int i = 0; i < code.length ; i++){
                if(poging[i].equals(code[i])){
                    evaluatie.add("Zwart");
                }
                else{
                    for (int j = 0; i < code.length; i++){
                        if(poging[i].equals(code[j])){
                        evaluatie.add("Wit");
                        }
                        else{
                        evaluatie.add("Rood");
                        }
                    }
                }       
            }
        }
                
        else if(moeilijkheidsGraad instanceof Gemiddeld || moeilijkheidsGraad instanceof Moeilijk){
 
                for (int i = 0; i < code.length; i++){
                if(poging[i].equals(code[i])){
                    if(evaluatie.isEmpty())
                        evaluatie.add("Zwart");
                    else if(evaluatie.contains("Zwart")){
                        int index = evaluatie.lastIndexOf("Zwart");
                        evaluatie.add(index + 1, "Zwart");
                    }
                }
                else{
                    for (int j = 0; i < code.length; i++){
                        if(poging[i].equals(code[j])){
                            if(evaluatie.isEmpty())
                                evaluatie.add("Wit");
                            else if(evaluatie.contains("Zwart")){
                                int index = evaluatie.lastIndexOf("Zwart");
                                evaluatie.add(index + 1, "Zwart");
                            }
                        }
                        else{
                            if(evaluatie.isEmpty())
                                evaluatie.add("Rood");
                            else if (evaluatie.contains("Zwart")){
                                int index = evaluatie.lastIndexOf("Zwart");
                                evaluatie.add(index + 1, "Wit");
                            }
                            else{
                                int index = evaluatie.lastIndexOf("Wit");
                                evaluatie.add(index + 1, "Rood");
                            }
                        }
                    }
                }
            } 
        }
    return evaluatie;
    }
    
    private boolean geldigePoging(CodePin[] poging, MoeilijkheidsGraad moeilijkheidsGraad){
        boolean geldig = false;
        
        if(moeilijkheidsGraad instanceof Gemakkelijk || moeilijkheidsGraad instanceof Gemiddeld){
            if(poging.length <= 4)
                geldig = true;
            else
                throw new InputMismatchException();
        }
        else{
          if(poging.length <= 5)
              geldig = true;
          else
              throw new InputMismatchException();
        }
       
        return geldig;
    }
}
