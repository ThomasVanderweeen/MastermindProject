package persistentie;

import domein.Spel;
import exceptions.ServerOnbereikbaarException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Groep 77
 */
public class SpelMapper {
    
        public void voegSpelToe(Spel sp){
            
        }
    
    /*Werkt na veel geprutst moet nog eens overgegaan worden*/
       public boolean spelBestaat(String spelNaam){
        boolean bestaat = false;
        
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = connectie.prepareStatement("SELECT count(naam) FROM ID222177_g77.Spel WHERE naam = ?;");){
           
            query.setString(1,spelNaam);
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
       

}
