package exceptions;

/**
 *
 * @author Michiel S.
 */
public class NiemandBeschikbaarVoorUitdagingException extends RuntimeException{
    
    public NiemandBeschikbaarVoorUitdagingException(){
        super("Er is niemand op het gekozen uitdaging niveau");
    }
    
    public NiemandBeschikbaarVoorUitdagingException(String msg){
        super(msg);
    }
}
