package exceptions;

/**
 *
 * @author Groep 77
 */
public class NietGenoegGewonnenException extends RuntimeException{
    public NietGenoegGewonnenException(){
        
    }
    
    public NietGenoegGewonnenException(String msg){
        super(msg);
    }
}
