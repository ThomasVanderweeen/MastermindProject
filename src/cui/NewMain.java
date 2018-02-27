package cui;

import domein.DomeinController;
import domein.Speler;
import java.util.Scanner;

/**
 *
 * @author Michiel S.
 */
public class NewMain {
        private final static DomeinController dc = new DomeinController();
        private final Scanner sc = new Scanner(System.in);
 /*main klasse voornamelijk gebruikt om te debuggen*/
 /*Hoe werkt Resources was leuk geweest als iemand die kon impelementeren na afspraak. ;) */
    public static void main(String[] args) {
        
        UC1 uc1 = new UC1();
        uc1.UC1Start();
    }
}



