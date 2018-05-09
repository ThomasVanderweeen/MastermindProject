package exceptions;

/**
 *
 * @author Michiel S.
 */
public class GeenOpenstaandeUitdagingException extends RuntimeException{
    
    public GeenOpenstaandeUitdagingException(){
        super("je hebt geen openstaande uitdagingen");
    }
    
    public GeenOpenstaandeUitdagingException(String msg){
        super(msg);
    }
    
}
