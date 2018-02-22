/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import persistentie.Connectie;

/**
 *
 * @author Groep 77
 */
public class SpelerMapper {
    
    private static final String INSERT_SPELER = "INSERT INTO ID222177_g77.speler(naam, wachtwoord)" + "VALUES(?, ?)";
    
    /*Verantwoordelijke voor het toevoegen van een speler aan de database*/
    public void voegToe(Speler speler){
        try(Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            PreparedStatement query = conn.prepareStatement(INSERT_SPELER))
        {
            query.setString(1, speler.getNaam());
            query.setString(2, speler.getWachtwoord());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}