package exceptions;

/**
 *
 * @author Michiel S.
 */
public class HeeftLopendeUitdagingException extends RuntimeException{
    
    public HeeftLopendeUitdagingException(){
        super("Deze speler heeft al een lopende uitdaging.");
    }
    
    public HeeftLopendeUitdagingException(String msg){
        super(msg);
    }
}
