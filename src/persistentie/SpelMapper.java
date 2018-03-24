package persistentie;


import exceptions.ServerOnbereikbaarException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Spel;
import domein.Speler;
import domein.Rij;
import domein.CodePin;
import domein.Spelbord;
import domein.Pin;
import java.util.List;
/**
 *
 * @author Groep 77
 */
public class SpelMapper {
    private int rijIndx,positie;
    private final String INSERT_RIJ = "INSERT INTO ID222177_g77.Rij VALUES ("+rijIndx+",?,?);";

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
       
    public void voegSpelToe(Spel spel){
 
        
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = connectie.prepareStatement("INSERT INTO ID222177_g77.Spel VALUES (?,?);");){
           
            String spelNaam = spel.getNaam();
            int rijNr = 1;
            
            query.setString(1,spelNaam);
            Speler speler = spel.getSpeler();
            query.setString(2,speler.getNaam());
            query.executeUpdate();
            
            Spelbord spelbord = spel.getSpelBord();
            
            try{
                /*rij 0 is code*/
                voegCodeToe(spelNaam,spelbord.getCode());
                
                /*Zolang een rij een evaluatie heeft voeg toe*/
                for(Rij rij:spelbord.getRijen()){
                    if(rij.heeftEvaluatie()){
                        voegRijToe(rijNr,spelNaam,rij);
                        rijNr++;
                    }
                    else
                        break;
                }
                
            }catch(Exception e){
                throw(e);
            }

        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }
        
    }
    
    private void voegCodeToe(String spelNaam,List<CodePin> pinnen){
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement(INSERT_RIJ);){
            this.positie = 0;
            this.rijIndx = 0;
            query.setString(1, "1");
            query.setString(2,spelNaam);

            query.executeUpdate();
            
            for(Pin p:pinnen){
                voegPinToe(this.rijIndx,spelNaam,p,this.positie);
                this.positie++;
            }
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }
    }
    
    private void voegRijToe(int indx,String naam,Rij rij){     
        this.rijIndx = indx;
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("INSERT INTO ID222177_g77.Rij VALUES ("+this.rijIndx+",?,?);");){
            this.positie = 0;
            
            query.setString(1, "0");
            query.setString(2,naam);
 
            query.executeUpdate();
            
            for(Pin p: rij.getPoging()){
                voegPinToe(this.rijIndx,naam,p,this.positie);
                this.positie++;
            }
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
    private void voegPinToe(int RijIndx,String naam,Pin pin,int plaats){     
         this.rijIndx = RijIndx;
         this.positie = plaats;
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("INSERT INTO ID222177_g77.Pin_Rij VALUES ("+positie+","+rijIndx+",?,?);");){
           
            query.setString(1, naam);
            query.setString(2, pin.getKleur());
            
            query.executeUpdate();
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }

}
