package exceptions;

/**
 *
 * @author Michiel S.
 */
public class AanmeldException extends RuntimeException{
    public AanmeldException(){
        
    }
    
    public AanmeldException(String msg){
        super(msg);
    }
}
