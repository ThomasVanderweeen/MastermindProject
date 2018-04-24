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
     /**
     * spelBestaat gaat na of een spelnaam uniek is.
     * 
     * @param spelNaam spelnaam die moet vergeleken worden met spelnamen in de databank.
     * @return boolean
     */
       public boolean spelBestaat(String spelNaam){
        boolean bestaat = false;
        
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement
        ("SELECT count(naam) FROM ID222177_g77.Spel WHERE naam = ?;");
            
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
    
    private String geefVorigeNaamUitdagingSpel(Spel spel){
        //select naam from Spel where naam!="test" and spelerNaam = "test" and UitdagingID = 1;
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement(
                    "select naam from ID222177_g77.Spel where naam!= ? and spelerNaam = ? and UitdagingID = "+
                            spel.getUitdagingID()+";");
            
            query.setString(1, spel.getNaam());
            query.setString(2, spel.getSpeler().getNaam());
            
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    return rs.getString("naam");
                }
            }
            
        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }
        
        return "";
    }
       
     /**
     * voegSpelToe voegt een spel toe aan de databank.
     * 
     * @param spel het toe te voegen spel.
     */
    public void voegSpelToe(Spel spel){
             /*moeilijkheidsgraad*/
            Spelbord spelbord = spel.getSpelBord();
            int mg = spelbord.geefMoeilijkheidsGraad();
        
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("INSERT INTO ID222177_g77.Spel VALUES (?,"+mg+",?,"+
                    (spel.getUitdagingID()==0?"null":String.valueOf(spel.getUitdagingID()))+");");
            
            String spelNaam = spel.getNaam();
            /*indien het deel is van een uitdaging moet de vorige versie verwijderd worden
            enkel indien dit geen error geeft uiteraard*/
            if(!(spel.getUitdagingID()==0)&&spelNaam!=null){
                    String vorigSpel = geefVorigeNaamUitdagingSpel(spel);
                    verwijderSpel(vorigSpel);
            }
            
            /*indien het een uitdaging is zonder naam ken een naam toe*/
            if(spelNaam == null)
                spelNaam = "uitdaging"+spel.getUitdagingID()+spel.getSpeler().getNaam();

            
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
    
    public void voegSpelTegenstanderToe(Spel spel,String tegenstander){
             /*moeilijkheidsgraad*/
            Spelbord spelbord = spel.getSpelBord();
            int mg = spelbord.geefMoeilijkheidsGraad();
        
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("INSERT INTO ID222177_g77.Spel VALUES (?,"+mg+",?,"+spel.getUitdagingID()+");");
            query.setString(2,tegenstander);
            
            String spelNaam = "uitdaging"+spel.getUitdagingID()+tegenstander;
            
            query.setString(1,spelNaam);
            query.executeUpdate();
                
            voegCodeToe(spelNaam,spelbord.getCode());

        }
        catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * voegCodeToe voegt een lijst van pinnen toe aan de databank.
     * 
     * @param spelNaam de naam van het spel waaraan de pinnen toebehoren.
     * @param pinnen de toe te voegen pinnen.
     */
    private void voegCodeToe(String spelNaam,List<CodePin> pinnen){
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement(INSERT_RIJ);
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
    
    /**
     * voegRijToe voegt een rij toe aan de databank.
     * 
     * @param indx de index van de rij op het spelbord. (1-12)
     * @param naam naam van het spel waaraan de rijen toebehoren.
     * @param rij toe te voegen rij.
     */
    private void voegRijToe(int indx,String naam,Rij rij){     
        this.rijIndx = indx;
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("INSERT INTO ID222177_g77.Rij VALUES ("+this.rijIndx+",?,?);");
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
    
    /**
     * voegPinToe voegt een pin toe aan de databank.
     * 
     * @param RijIndx index van de rij op het spelbord. (1-12)
     * @param naam naam van het spel waaraan de pinnen toebehoren.
     * @param pin toe te voegen pin.
     * @param plaats plaats in de rij. 1-4 bij makkelijk/normaal, 1-5 bij moeilijk.
     */
    private void voegPinToe(int RijIndx,String naam,Pin pin,int plaats){     
         this.rijIndx = RijIndx;
         this.positie = plaats;
        try{
           PreparedStatement query = SpelerMapper.conn.prepareStatement("INSERT INTO ID222177_g77.Pin_Rij VALUES ("+positie+","+rijIndx+",?,?);");
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
    
    /**
     * getCountSpellen haalt het aantal spellen voor een bepaalde speler uit de databank. 
     * 
     * @param spelernaam de speler waarvan de spelcount moet opgehaald worden.
     * @return String
     */
    private String getCountSpellen(String spelernaam){
        String aantal="";
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("SELECT count(naam) FROM ID222177_g77.Spel WHERE spelerNaam = ?"
                    + " AND uitdagingID is null;");
            
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
    
    /**
     * heeftOpgelsagenSpellen gebruikt de getCountSpellen om na te gaan of een speler opgelsagen spellen heeft in de databank.
     * 
     * @param spelernaam de speler waarvan wordt nagegaan of deze spellen heeft in de databank.
     * @return boolean
     */
    public boolean heeftOpgeslagenSpellen(String spelernaam){
        boolean opgeslaan = false;
        
           if(!(getCountSpellen(spelernaam).equals("0")))
                opgeslaan=true;
                    
        return opgeslaan;
    }
    
     /**
     * toonSpellen toont een lijst van alle spellen die in de databank gelinkt zijn aan een bepaalde speler.
     * 
     * @param spelernaam de speler wiens spellen getoond worden. 
     * @return String[][]
     */
    public String[][] toonSpellen(String spelernaam){
        String[][] resultaat =
                new String[Integer.parseInt(getCountSpellen(spelernaam))][2];
                
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement
        ("SELECT naam,moeilijkheidsgraad FROM ID222177_g77.Spel WHERE spelerNaam = ? and uitdagingID is null;");
            
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
    
     /**
     * verwijderSpel verwijdert een spel aan de hand van 3 hulpmethodes: verwijderPinnen, verwijderRijen, en verwijderSpelTabel.
     * 
     * @param spelnaam het spel dat verwijderd wordt.
     */

    public void verwijderSpel(String spelnaam){
        verwijderPinnen(spelnaam);

    }
    
     /**
     * verwijderPinnen verwijdert pinnen die aan een bepaald spel toebehoren.
     * 
     * @param spelnaam spel waarvan de pinnen verwijderd worden.
     */
    private void verwijderPinnen(String spelnaam){
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("DELETE FROM ID222177_g77.Pin_Rij WHERE spelNaam = ?");
            query.setString(1, spelnaam);
            query.executeUpdate();
            verwijderRijen(spelnaam);
        
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }
    }
    
     /**
     * verwijderRijen verwijdert rijen die aan een bepaald spel toebehoren.
     * 
     * @param spelnaam spel waarvan de rijen verwijderd worden.
     */
    private void verwijderRijen(String spelnaam){
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("DELETE FROM ID222177_g77.Rij WHERE spelNaam = ?");
         
            query.setString(1, spelnaam);
            query.executeUpdate();
            verwijderSpelTabel(spelnaam);
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
     /**
     * verwijderSpelTabel verwijdert de tabel 'Spel' uit de databank.
     * 
     * @param spelnaam de spelnaam waarvan de tabel 'Spel' verwijderd wordt.
     */
        private void verwijderSpelTabel(String spelnaam){
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("DELETE FROM ID222177_g77.Spel WHERE naam = ?");
            query.setString(1, spelnaam);
            query.executeUpdate();
        }catch(SQLException e){
            if(e.hashCode()==933699219)
                throw new ServerOnbereikbaarException();
            else
                throw new RuntimeException(e.getMessage());
        }  
    }
    
    /**
     * maakSpel maakt een spel dat kan doorgegeven worden aan SpelRepository. maakSpel gebruikt hiervoor getRijKleuren, getMoeilijkheidsgraad, en geefCountRijen als hulpmethodes. 
     * 
     * @param spelnaam naam van het spel dat wordt gemaakt.
     * @param speler Speler die het spel speelt.
     * @return Spel
     */
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
    
    /**
     * getRijKleuren haalt de kleuren van de pinnen van een bepaalde rij uit de databank.
     * 
     * @param spelnaam spel waarvan de kleuren worden opgehaald.
     * @param rij rij waarvan de kleuren worden opgehaald.
     * @return List<String>
     */
    private List<String> getRijKleuren(String spelnaam,int rij){
        List<String> kleur = new ArrayList<>();
        
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement
        ("SELECT kleur FROM ID222177_g77.Pin_Rij WHERE spelNaam = ? AND nummer = "+rij+";");
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
    
     /**
     * getMoeilijkheidsGraad haalt de moeilijkheidsgraad van een bepaald spel uit de databank. 
     * 
     * @param spelnaam naam van het spel waarvan de moeilijkheidsgraad wordt opgehaald.
     * @return integer
     */
    private int getMoeilijkheidsGraad(String spelnaam){
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("SELECT moeilijkheidsGraad FROM ID222177_g77.Spel WHERE naam = ?;");
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
    
     /**
     * geefCountRijen haalt het aantal rijen van een bepaald spel uit de databank.
     * 
     * @param spelnaam naam van het Spel waarvan het aantal rijen wordt opgehaald.
     * @return integer
     */
    private int geefCountRijen(String spelnaam){
        try{
            PreparedStatement query = SpelerMapper.conn.prepareStatement("SELECT count(nummer) FROM ID222177_g77.Rij WHERE spelNaam = ?;");
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
