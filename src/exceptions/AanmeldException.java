package exceptions;

/**
 *
 * @author Groep 77
 */
public class AanmeldException extends RuntimeException{
    public AanmeldException(){
        
    }
    
    public AanmeldException(String msg){
        super(msg);
    }
}
