/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ThomasV
 */
public class SpelbordScherm extends AnchorPane{
    private FXMLLoader fxml;

    public Parent maakParent(){
        Parent pr = null;
        

        try
        {
            this.fxml = new FXMLLoader(getClass().getResource("Spelbord.fxml"));
            pr = this.fxml.load();

        } catch (IOException ex)
        {
            Logger.getLogger(SpelbordScherm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pr;
    }
    
    public SpelbordController geefController(){

        return this.fxml.getController();
    }
}
