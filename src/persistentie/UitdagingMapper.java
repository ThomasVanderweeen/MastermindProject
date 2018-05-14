package persistentie;
import domein.Speler;
import domein.Uitdaging;
import domein.Spel;
import exceptions.ServerOnbereikbaarException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static persistentie.SpelerMapper.conn;
/**
 *
 * @author Michiel S.
 */
public class UitdagingMapper {
    
    /**
     * Slaat een uitdaging op in de databank
     * @param uitdaging uitdaging die wordt opgeslaan
     */
    public void voegUitdagingToe(Uitdaging uitdaging){
        int uitdagingNr = geefUitdagingNummer();
        String tegenstander = uitdaging.getTegenstander();
        Spel spel = uitdaging.getSpel();
        Speler uitdager = spel.getSpeler();
        
        voegUitdagingToe();
        associeerSpelersMetUitdaging(uitdager,tegenstander,uitdagingNr);
    }
    
    /**
     * geeft het nummer van een uitdaging terug.
     * @return int
     */
    private int geefUitdagingNummer(){
        try{
            PreparedStatement query = conn.prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'ID222177_g77' AND   TABLE_NAME   = 'Uitdaging';");

            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    return rs.getInt("AUTO_INCREMENT");
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
    
    /**
     * voegt een uitdaging toe met als waarde NULL voor elke kolom
     */
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
    
    /**
     * legt een associatie tussen een speler en een uitdaging
     * 
     * @param speler speler van de uitdaging
     * @param uitdager uitdager van de uitdaging
     * @param ID ID van de uitdaging
     */
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
    
    /**
     * gaat na of een speler lopende uitdagingen heeft.
     * 
     * @param speler speler die gecheckt moet worden
     * @return boolean
     */
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
   
    /**
     * gaat na of een speler al uitgedaagd is door een tegenstander
     * @param speler speler van de uitdaging
     * @param tegenstander tegenstander van de speler
     * @return boolean
     */
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
   
   /**
    * geeft het ID van een uitdaging.
    * @param speler speler van de uitdaging
    * @param tegenstander tegenstander van de speler
    * @return int
    */
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
   
   /**
    * Geeft het ID van een uitdaging die de speler op dit moment geaccepteerd heeft.
    * @param speler van de uitdaging
    * @return int
    */
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
   
   /**
    * Geeft de naam van een tegenstander op basis van ID
    * @param speler speler van de uitdaging
    * @param ID id van de uitdaging
    * @return 
    */
   public String geefNaamTegenstanderID(Speler speler,int ID){
        try{
            PreparedStatement query = conn.prepareStatement("select spelerNaam from "
                    + "ID222177_g77.Speler_Uitdaging where spelerNaam != ? and ID ="+ID+";");
            
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
   
   /**
    * verwijdert een uitdaging
    * @param ID id van de te verwijderen uitdaging
    */
   public void verwijderUitdaging(int ID){
       verwijderSpelerUitdagingAssociatie(ID);
       verwijderUitdagingID(ID);
   }
   
   /**
    * verwijdert een associatie tussen speler en uitdager
    * @param ID id van de uitdaging
    */
   private void verwijderSpelerUitdagingAssociatie(int ID){
         try{
            PreparedStatement query = conn.prepareStatement("delete from "
                    + "ID222177_g77.Speler_Uitdaging where ID ="+ID+";");
            
            query.executeUpdate();
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
         
   }
   
   /**
    * verwijdert een uitdaging
    * @param ID id van de te verwijderen uitdaging
    */
   private void verwijderUitdagingID(int ID){
         try{
            PreparedStatement query = conn.prepareStatement("delete from "
                    + "ID222177_g77.Uitdaging where ID ="+ID+";");
            
            query.executeUpdate();
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
      }
   
   /**
    * update een uitdaging
    * @param uitdaging up te daten uitdaging
    */
   public void updateUitdaging(Uitdaging uitdaging){
       try{
            PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g77.Speler_Uitdaging SET aantalPogingen = ? "
                    + "WHERE ID = ? AND spelerNaam = ?;");
            
            query.setInt(1, uitdaging.getSpel().getAantalPogingen());
            query.setInt(2, uitdaging.getSpel().getUitdagingID());
            query.setString(3, uitdaging.getSpel().getSpeler().getNaam());
            
            query.executeUpdate();
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }

   }
   
   /**
    * Geeft het ID van elke openstaande uitdaging van een speler
    * 
    * @param speler waarvan de uitdaging IDs worden opgehaald
    * @return List<Integer>
    */
      private List<Integer> geefIDsopenstaandeUitdagingen(Speler speler) {
        List<Integer> IDs = new ArrayList<>();
        try {            
            PreparedStatement query = conn.prepareStatement("select ID from "
                    + "ID222177_g77.Speler_Uitdaging where geacepteerd = 0 and spelerNaam = ?;");
            query.setString(1, speler.getNaam());
             
            try (ResultSet rs = query.executeQuery()) {
                while(rs.next()){
                    IDs.add(rs.getInt("ID"));
                }
            }
            
        }catch (SQLException e) {

        }
        return(IDs);
    }

    private String[][] geefNaamUitdagerEnId(Speler speler, List<Integer> IDs){
            
            String[][] res = new String[IDs.size()][2];
            int indx=0;
            for(int id:IDs){
                res[indx][0] = Integer.toString(id);
                res[indx][1] = geefNaamTegenstanderID(speler, id);
                indx++;
            }
            return res;
    }
    
    public String[][] geefUitdagingenSpeler(Speler speler) {
            List<Integer> IDs = geefIDsopenstaandeUitdagingen(speler);
            String[][] uitdagerEnId = geefNaamUitdagerEnId(speler, IDs);
            
            return uitdagerEnId;
    }
    
    /**
     * update de databank zodat geaccepteerd 1 wordt wanneer een speler een uitdaging accepteerd.
     * @param ID id van de geaccepteerde uitdaging
     * @param speler speler die de uitdaging accepteerd
     */
    public void accepteerUitdaging(int ID,Speler speler){
        try{
            PreparedStatement querry = conn.prepareStatement("UPDATE ID222177_g77.Speler_Uitdaging SET geacepteerd = 1 "
                +"WHERE ID = "+ID+" AND spelerNaam = ?;");
            
            querry.setString(1, speler.getNaam());
            querry.executeUpdate();
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e);
        }
    }
}
