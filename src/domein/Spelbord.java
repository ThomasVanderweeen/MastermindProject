package domein;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author Michiel S.
 */
public class Spelbord {
    private final Code code;
    private Rij[] rijen = new Rij[12];
    
    public Spelbord(int moeilijkheidsGraad){
        this.code = new Code(moeilijkheidsGraad);
        int lengte = this.code.getCode().size();
        vulRijenOp(this.code.getCode().size());
    }
    
    public List<CodePin> getCode(){
       return this.code.getCode();
    }

    private void vulRijenOp(int lengte){
        for(int i=0;i<rijen.length;i++){
            this.rijen[i] = new Rij(lengte);
        }
    }
    
    public Rij[] getRijen(){
        return this.rijen;
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
        moeilijkheidsGraad = this.code.getMoeilijkheidsGraad();
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
