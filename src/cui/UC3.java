/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cui;

import domein.DomeinController;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author ThomasV
 */
public class UC3 {
    
    private final ResourceBundle r;
    private final DomeinController dc;
    private final Scanner sc;
    
    public UC3(ResourceBundle r,DomeinController dc){
        this.r = r;
        this.dc = dc;
        this.sc = new Scanner(System.in);
    }
    
    public void start(){
    }
}
