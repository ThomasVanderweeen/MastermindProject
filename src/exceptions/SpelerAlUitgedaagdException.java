package exceptions;

/**
 *
 * @author Michiel S.
 */
public class SpelerAlUitgedaagdException extends RuntimeException{
    public SpelerAlUitgedaagdException(){
        super("Je hebt deze speler al eens uitgedaagd.");
    }
    
    public SpelerAlUitgedaagdException(String msg){
        super(msg);
    }
}
