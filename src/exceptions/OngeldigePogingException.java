package exceptions;

/**
 *
 * @author Groep 77
 */
public class OngeldigePogingException extends IllegalArgumentException{
    public OngeldigePogingException(){
        super("De poging bevat een ongeldig aantal Pinnen.");
    }
    
    public OngeldigePogingException(String msg){
        super(msg);
    }
}
