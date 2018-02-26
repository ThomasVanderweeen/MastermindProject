package cui;

import domein.DomeinController;

/**
 *
 * @author Michiel S.
 */
public class NewMain {
 /*main klasse voornamelijk gebruikt om te debuggen*/
 /*Hoe werkt Resources was leuk geweest als iemand die kon impelementeren na afspraak. ;) */
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("120"));
        DomeinController dc = new DomeinController();
        dc.meldAan("test", "123");
    }

}
