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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Groep 77
 */
public class SpelerMapper {
    private static final String INSERT_SPELER = "INSERT INTO ID222177_g77.Speler(naam, wachtwoord)" + "VALUES(?, ?)";
    private static final String INSERT_SCOREMAKKELIJK = "INSERT INTO ID222177_G77.speler(UitdagingScoreMakkelijk)" + "VALUES(?)";
    private static final String INSERT_SCOREGEMIDDELD = "INSERT INTO ID222177_G77.speler(UitdagingScoreGemiddeld)" + "VALUES(?)";
    private static final String INSERT_SCOREMOEILIJK = "INSERT INTO ID222177_G77.speler(UitdagingScoreMoeilijk)" + "VALUES(?)";
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
    
    public  List<String[]> geefBeschikbareSpelersUitdaging(int moeilijkheidsGraad,String naam){
        String tableNaam = "gewonnenMakkelijk";
        List<String[]> spelers  = new ArrayList<>();
            
        if(moeilijkheidsGraad==3)
            tableNaam = "gewonnenGemiddeld";
        
        
        try{
            PreparedStatement query;
            
                if(moeilijkheidsGraad>1)
                query = conn.prepareStatement(
                        "SELECT naam,"+tableNaam+" FROM ID222177_g77.Speler WHERE "+tableNaam+">=20 AND naam != '"+naam+"';");
            else
                query = conn.prepareStatement(
                        "SELECT naam,"+tableNaam+" FROM ID222177_g77.Speler WHERE naam != '"+naam+"';");
            
            try(ResultSet rs = query.executeQuery()){
                  
                while(rs.next()){
                    String[] speler = new String[2];
                    speler[0] = rs.getString("naam");
                    speler[1] = rs.getString(tableNaam);
                    spelers.add(speler);
                }
                
             }

        
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
        return spelers;
    }
    
    public List<String[]> geefKlassement(int graad){
        List<String[]> spelers = new ArrayList<String[]>();
        String difficulty = "";
        switch(graad){
            case 1: difficulty = "gewonnenMakkelijk";
                    break;
            case 2: difficulty = "gewonnenGemiddeld";
                    break;
            case 3: difficulty = "gewonnenMoeilijk";
                    break;
        }
        try{
            PreparedStatement query;
            
                query = conn.prepareStatement(
                        "SELECT naam, "+difficulty+" FROM ID222177_g77.Speler ORDER BY "+difficulty+" desc;");
                        
            
            try(ResultSet rs = query.executeQuery()){
                  
                while(rs.next()){
                    String[] speler = new String[2];
                    speler[0] = rs.getString("naam");
                    speler[1] = rs.getString(difficulty);
                    spelers.add(speler);
                }
                
             }

        
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
        return spelers;
    }
    
    public Speler geefSpelerNaam(String gebruikersnaam){
        Speler speler = null;
        String wachtwoord = "";
        
        try{
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g77.Speler WHERE naam = ?");
            query.setString(1,gebruikersnaam);
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

    public void SlaScoreMakkelijkOp(int score) {
        try{
            PreparedStatement query = conn.prepareStatement(INSERT_SCOREMAKKELIJK);
            query.setInt(1, score);
            query.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }    
    }
    
    public void SlaScoreGemiddeldOp(int score) {
        try{
            PreparedStatement query = conn.prepareStatement(INSERT_SCOREGEMIDDELD);
            query.setInt(1, score);
            query.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }    
    }
    
    public void SlaScoreMoeilijkOp(int score) {
        try{
            PreparedStatement query = conn.prepareStatement(INSERT_SCOREMOEILIJK);
            query.setInt(1, score);
            query.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }    
    }
}
