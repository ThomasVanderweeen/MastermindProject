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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groep 77
 */
public class SpelerMapper {
    private static final String INSERT_SPELER = "INSERT INTO ID222177_g77.Speler(naam, wachtwoord)" + "VALUES(?, ?)";
    protected static Connection conn;
    
    public SpelerMapper(){
        try{
           conn = DriverManager.getConnection(Connectie.JDBC_URL);
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    /*Verantwoordelijke voor het toevoegen van een speler aan de database*/
     /**
     * voegToe voegt een speler toe aan de databank.
     * 
     * @param speler speler die toegevoegd wordt.
     */
    public void voegToe(Speler speler){
        try{
            PreparedStatement query = conn.prepareStatement(INSERT_SPELER);
            query.setString(1, speler.getNaam());
            query.setString(2, speler.getWachtwoord());
            query.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    /**
     * geefSpeler haalt informatie over een speler uit de databank.
     * 
     * @param gebruikersnaam naam van de speler die uit de databank gehaald wordt.
     * @param wachtwoord wachtwoord van de speler die uit de databank gehaald wordt.
     * @return Speler
     */
    public Speler geefSpeler(String gebruikersnaam,String wachtwoord){
        Speler speler = null;
        
        try{
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g77.Speler WHERE naam = ? AND wachtwoord = ?");
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
     /**
     * spelerBestaat gaat na of de gerbuikersnaam van een speler uniek is.
     * 
     * @param gebruikersnaam gebruikersnaam die gecontrolleerd wordt. 
     * @return boolean
     */
       public boolean spelerBestaat(String gebruikersnaam){
        boolean bestaat = false;
        
        try{
            PreparedStatement query = conn.prepareStatement("SELECT count(naam) FROM ID222177_g77.Speler WHERE naam = ?;");
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
       
         /**
     * updateScore wijzigt de score van een bepaalde speler. 
     * 
     * @param sp speler waarvan de score gewijzigd wordt.
     * @param moeilijkheidsGraad moeilijkheidsGraad waarbij een punt moet worden opgeteld. (makkelijk, moeilijk of normaal) 
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
        try{
                PreparedStatement query = conn.prepareStatement("update  ID222177_g77.Speler "
                        + "set "+rowname+"=+"+score+" where naam=?;");
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
