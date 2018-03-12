package domein;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Michiel S.
 */
public class Spelbord {
    
    private MoeilijkheidsGraad moeilijkheidsGraad;
    private List<String> code;
    private List<List<String>> pogingen;
    
    public Spelbord(int moeilijkheidsGraad){
        this.pogingen = new ArrayList<>();
        stelMoeilijkheidsGraadIn(moeilijkheidsGraad);
        setCode();
        maakSpelbord();
    }

    private void setCode(){
        this.code = this.moeilijkheidsGraad.genereerCode();
    }
    
    private void stelMoeilijkheidsGraadIn(int moeilijkheidsGraad){
        switch(moeilijkheidsGraad){
            case 1:
                this.moeilijkheidsGraad = new Gemakkelijk();
                break;
            case 2:
                this.moeilijkheidsGraad = new Gemiddeld();
                break;
            case 3:
                this.moeilijkheidsGraad = new Moeilijk();
                break;
        }
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
        
        ArrayList<String> evaluatie = new ArrayList();
        moeilijkheidsGraad = this.getMoeilijkheidsGraad();
        if(moeilijkheidsGraad instanceof Gemakkelijk){
            for(int i = 0; i < code.length ; i++){
                if(poging[i] == code[i]){
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
}
