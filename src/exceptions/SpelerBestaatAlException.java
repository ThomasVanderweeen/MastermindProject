/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Groep 77
 */
public class SpelerBestaatAlException extends RuntimeException{
    
    public SpelerBestaatAlException(){
        
    }
    
    public SpelerBestaatAlException(String msg){
        super(msg);
    }
    
}
