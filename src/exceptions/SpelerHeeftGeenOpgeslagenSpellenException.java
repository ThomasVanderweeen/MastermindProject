package exceptions;

/**
 *
 * @author Michiel S.
 */
public class SpelerHeeftGeenOpgeslagenSpellenException extends RuntimeException{
    public SpelerHeeftGeenOpgeslagenSpellenException(){
        super("De speler heet geen spellen opgeslagen");
    }
    
    public SpelerHeeftGeenOpgeslagenSpellenException(String msg){
        super(msg);
    }
}
