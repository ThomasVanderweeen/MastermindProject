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
import java.sql.ResultSet;
import java.sql.SQLException;
import persistentie.Connectie;
import exceptions.ServerOnbereikbaarException;

/**
 *
 * @author Groep 77
 */
public class SpelerMapper {
    /*Eventueel een standaard instellen in de database voor de laatste drie die bij initialisatie 0 is*/
    private static final String INSERT_SPELER = "INSERT INTO ID222177_g77.Speler(naam, wachtwoord)" + "VALUES(?, ?)";
    
    /*Verantwoordelijke voor het toevoegen van een speler aan de database*/
    public void voegToe(Speler speler){
        try(
            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            PreparedStatement query = conn.prepareStatement(INSERT_SPELER))
        {
            query.setString(1, speler.getNaam());
            query.setString(2, speler.getWachtwoord());
            query.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    /*Haalt informatie over een speler uit de database*/
    public Speler geefSpeler(String gebruikersnaam,String wachtwoord){
        Speler speler = null;
        
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = connectie.prepareStatement("SELECT * FROM ID222177_g77.Speler WHERE naam = ? AND wachtwoord = ?")){
        
            query.setString(1,gebruikersnaam);
            query.setString(2, wachtwoord);
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    speler = new Speler(gebruikersnaam, wachtwoord,
                            rs.getInt("gewonnenMakkelijk"),
                            rs.getInt("gewonnenGemiddeld"),
                            rs.getInt("gewonnenMoeilijk"));
                }
                else{
                    speler = null;
                }
            }
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
        return speler;
    }
    
    /*Werkt na veel geprutst moet nog eens overgegaan worden*/
       public boolean spelerBestaat(String gebruikersnaam){
        boolean bestaat = false;
        
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = connectie.prepareStatement("SELECT count(naam) FROM ID222177_g77.Speler WHERE naam = ?;");){
           
            query.setString(1,gebruikersnaam);
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    String aantal = rs.getString("count(naam)");
                    if(aantal.equals("1")){
                        bestaat=true;
                    }
                }
            }
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
        return bestaat;
    }
       
       /*
*/
       public void updateScore(Speler sp, int moeilijkheidsGraad){
           String rowname;
           int score;
           
           if(moeilijkheidsGraad==1){
                rowname = "gewonnenMakkelijk";
                score = sp.getAantalGewonnenMakkelijk();
            }
           else{
               if(moeilijkheidsGraad==2){
                   rowname = "gewonnenGemiddeld";
                   score= sp.getAantalGewonnenGemiddeld();
               }
               else{
                   rowname = "gewonnenMoeilijk";
                   score = sp.getAantalGewonnenMoeilijk();
               }
           }
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = connectie.prepareStatement("update  ID222177_g77.Speler "
                        + "set "+rowname+"=+"+score+" where naam=?;");){
            


            query.setString(1,sp.getNaam());
            query.executeUpdate();
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
       }
}
