package exceptions;

/**
 *
 * @author Michiel S.
 */
public class SpelNaamNietUniekException extends IllegalArgumentException{
    
    public SpelNaamNietUniekException(){
        super("Deze spelnaam is niet uniek");
    }
    
    public SpelNaamNietUniekException(String message){
        super(message);
    }
    
}
