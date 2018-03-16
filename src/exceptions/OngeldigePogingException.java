package exceptions;

/**
 *
 * @author Michiel S.
 */
public class OngeldigePogingException extends IllegalArgumentException{
    public OngeldigePogingException(){
        super("De poging bevat een ongeldig aantal Pinnen.");
    }
    
    public OngeldigePogingException(String msg){
        super(msg);
    }
}
