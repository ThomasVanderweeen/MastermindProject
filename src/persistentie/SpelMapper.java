package persistentie;


import exceptions.ServerOnbereikbaarException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import domein.Spel;
import domein.Speler;
import domein.Rij;
import domein.CodePin;
import domein.Spelbord;
import domein.Pin;
import domein.Gemakkelijk;
import domein.Gemiddeld;
import domein.Moeilijk;
import domein.MoeilijkheidsGraad;
import domein.Code;
import java.util.Arrays;

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
             /*moeilijkheidsgraad*/
            Spelbord spelbord = spel.getSpelBord();
            int mg = spelbord.geefMoeilijkheidsGraad();
        
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = connectie.prepareStatement("INSERT INTO ID222177_g77.Spel VALUES (?,?,"+mg+");");){
           
            String spelNaam = spel.getNaam();
            int rijNr = 1;
            
            query.setString(1,spelNaam);
            Speler speler = spel.getSpeler();
            query.setString(2,speler.getNaam());
            query.executeUpdate();
         
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

    private String getCountSpellen(String spelernaam){
        String aantal="";
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("SELECT count(naam) FROM ID222177_g77.Spel WHERE spelerNaam = ?;");){
            
            query.setString(1, spelernaam);
            
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    aantal = rs.getString("count(naam)");
                }
            }
            
            return aantal;
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
    public boolean heeftOpgeslagenSpellen(String spelernaam){
        boolean opgeslaan = false;
        
           if(!(getCountSpellen(spelernaam).equals("0")))
                opgeslaan=true;
                    
        return opgeslaan;
    }
    
    public String[][] toonSpellen(String spelernaam){
        String[][] resultaat =
                new String[Integer.parseInt(getCountSpellen(spelernaam))][2];
                
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("SELECT naam,moeilijkheidsgraad FROM ID222177_g77.Spel WHERE spelerNaam = ?;");){
            
            query.setString(1, spelernaam);
            
            try(ResultSet rs = query.executeQuery()){
                int rij = 0;
                while(rs.next()){
                    resultaat[rij][0] = rs.getString("naam");
                    resultaat[rij][1] = Integer.toString(rs.getInt("moeilijkheidsgraad"));
                    rij++;
                }
            }
       
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
        
        return resultaat;
    }
    
    public void verwijderSpel(String spelnaam){
        verwijderPinnen(spelnaam);
        verwijderRijen(spelnaam);
        verwijderSpelTabel(spelnaam);
    }
    
    
    private void verwijderPinnen(String spelnaam){
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("DELETE FROM ID222177_g77.Pin_Rij WHERE spelNaam = ?");){
            
            query.setString(1, spelnaam);
            query.executeUpdate();
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
    private void verwijderRijen(String spelnaam){
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("DELETE FROM ID222177_g77.Rij WHERE spelNaam = ?");){
            
            query.setString(1, spelnaam);
            query.executeUpdate();
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
        private void verwijderSpelTabel(String spelnaam){
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("DELETE FROM ID222177_g77.Spel WHERE spelNaam = ?");){
            
            query.setString(1, spelnaam);
            query.executeUpdate();
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
    public Spel maakSpel(String spelnaam,Speler speler){
        int mg = getMoeilijkheidsGraad(spelnaam),pogingindx;
        List<CodePin> code = new ArrayList<>();
        MoeilijkheidsGraad moeilijkheidsGraad=null;
        List<String> kleuren = Arrays.asList(CodePin.getGeldigeKleuren());
        int[] poging;
        
        Code codeobj;
        Spelbord spelbord;
        Spel spel=null;
        
        
        for(int rijnr=0;rijnr<geefCountRijen(spelnaam);rijnr++){
            /*code*/
            if(rijnr==0){
                
                switch(mg){
                    case 1:
                        moeilijkheidsGraad = new Gemakkelijk();
                        break;
                    case 2:
                        moeilijkheidsGraad = new Gemiddeld();
                        break;
                    case 3:
                        moeilijkheidsGraad = new Moeilijk();
                        break;
                }
                
                for(String kleur: getRijKleuren(spelnaam,rijnr)){
                    code.add(new CodePin(kleur));
                }
                
                codeobj = new Code(code,moeilijkheidsGraad);
                spelbord = new Spelbord(codeobj);
                spel = new Spel(spelbord,speler);
            }
            
            
            else{
                poging = new int[code.size()];
                pogingindx=0;
                
                for(String kleur: getRijKleuren(spelnaam,rijnr)){
                    poging[pogingindx] = kleuren.indexOf(kleur);
                    pogingindx++;
                }
                
                spel.doePoging(poging);
            }
            
        }
        
        return spel;
    }
    
    private List<String> getRijKleuren(String spelnaam,int rij){
        List<String> kleur = new ArrayList<>();
        
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("SELECT kleur FROM ID222177_g77.Pin_Rij WHERE spelNaam = ? AND nummer = "+rij+";");){
        
            query.setString(1, spelnaam);
            
            try(ResultSet rs = query.executeQuery()){
                while(rs.next()){
                    kleur.add(rs.getString("kleur"));
                }
            }
        
       }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
        
        return kleur;
    }
    
    private int getMoeilijkheidsGraad(String spelnaam){
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("SELECT moeilijkheidsGraad FROM ID222177_g77.Spel WHERE naam = ?;");){
            
            query.setString(1, spelnaam);
            
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    return Integer.parseInt(rs.getString("moeilijkheidsGraad"));
                }
            }
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
        
        return 0;
    }
    
    private int geefCountRijen(String spelnaam){
        try(Connection connectie = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = connectie.prepareStatement("SELECT count(nummer) FROM ID222177_g77.Rij WHERE spelNaam = ?;");){
            
            query.setString(1, spelnaam);
            
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    return Integer.parseInt(rs.getString("count(nummer)"));
                }
            }
            
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
        
        return 0;
    }

}
