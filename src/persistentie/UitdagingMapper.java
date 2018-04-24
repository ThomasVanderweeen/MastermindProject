package persistentie;
import domein.Speler;
import domein.Uitdaging;
import domein.Spel;
import exceptions.ServerOnbereikbaarException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static persistentie.SpelerMapper.conn;
/**
 *
 * @author Michiel S.
 */
public class UitdagingMapper {
    
    public void voegUitdagingToe(Uitdaging uitdaging){
        int uitdagingNr = geefUitdagingNummer();
        String tegenstander = uitdaging.getTegenstander();
        Spel spel = uitdaging.getSpel();
        Speler uitdager = spel.getSpeler();
        
        voegUitdagingToe();
        associeerSpelersMetUitdaging(uitdager,tegenstander,uitdagingNr);
    }
    
    private int geefUitdagingNummer(){
        try{
            PreparedStatement query = conn.prepareStatement("SELECT max(id) FROM ID222177_g77.Uitdaging;");

            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    return (rs.getInt("max(id)")+1);
                }
            }
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
        return 1;
    }
    
    private void voegUitdagingToe(){
        try{
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g77.Uitdaging VALUES (NULL);");
            query.executeUpdate();
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
    }
    
    private void associeerSpelersMetUitdaging(Speler speler,String uitdager,int ID){
      try{
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g77.Speler_Uitdaging VALUES "
                    + "("+ID+",?,1,null), ("+ID+",?,0,null);");
            query.setString(1, speler.getNaam());
            query.setString(2, uitdager);
            query.executeUpdate();
      }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
    }
    
    public boolean heeftLopendeUitdaging(Speler speler){
        try{
            PreparedStatement query = conn.prepareStatement("SELECT count(ID) FROM "
                    + "ID222177_g77.Speler_Uitdaging WHERE geacepteerd = 1 AND aantalPogingen is null AND spelerNaam = ?");
            query.setString(1,speler.getNaam());

            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    int count = rs.getInt("count(ID)");
                    if(count!=0)
                        return true;
                    else
                        return false;
                }
            }
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
        
        return false;
    }
    
   public boolean spelerIsAlUitgedaag(Speler speler, String tegenstander){
        try{
            PreparedStatement query = conn.prepareStatement("select count(ID) from ID222177_g77.Speler_Uitdaging where spelerNaam = ? or spelerNaam = ? "+""
                    + "group by ID having count(ID)=2;");
            query.setString(1,speler.getNaam());
            query.setString(2, tegenstander);
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    return true;
                }else{
                    return false;
                }
            }
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
    }
   
   public int geefUitdagingID(Speler speler, String tegenstander){
       try{
            PreparedStatement query = conn.prepareStatement("Select ID from ID222177_g77.Speler_Uitdaging where spelerNaam = ? or spelerNaam = ?"+
                "group by ID having count(ID)=2;");
            query.setString(1,speler.getNaam());
            query.setString(2, tegenstander);
            try(ResultSet rs = query.executeQuery()){
                if(rs.next())
                    return rs.getInt(("ID"));
            }
       }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
       return -1;
   }
   
   public int geefHuidigeUitdagingID(Speler speler){
        try{
            PreparedStatement query = conn.prepareStatement("select ID from ID222177_g77.Speler_Uitdaging where "
                    + "spelerNaam = ? and aantalPogingen is null and geacepteerd=1;");
            
            query.setString(1, speler.getNaam());
            try(ResultSet rs = query.executeQuery()){
                rs.next();
                return rs.getInt("ID");
            }
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
   }
   
   public String geefNaamTegenstanderID(Speler speler,int ID){
        try{
            PreparedStatement query = conn.prepareStatement("select spelerNaam from "
                    + "ID222177_g77.Speler_Uitdaging where spelerNaam != ?and ID ="+ID+";");
            
            query.setString(1, speler.getNaam());
            try(ResultSet rs = query.executeQuery()){
                rs.next();
                return rs.getString("spelerNaam");
            }
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
   }
   
}
