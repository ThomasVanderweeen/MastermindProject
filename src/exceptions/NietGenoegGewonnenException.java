package exceptions;

/**
 *
 * @author Michiel S.
 */
public class NietGenoegGewonnenException extends RuntimeException{
    public NietGenoegGewonnenException(){
        
    }
    
    public NietGenoegGewonnenException(String msg){
        super(msg);
    }
}
