package pl.jasox.medward.client;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;
import javax.naming.*;

/**
 * Proste testowanie połączeń JDBC na różnych serwerach aplikacji
 * @author Janusz Swół
 */
public class Client {    
  
    private Context    ctx = null;
    private DataSource ds  = null;    
    private Connection con = null;
  
    public void getDatasource(String datasourceJndiName) throws NamingException {
        ctx = getInitialContext();
        ds  = (DataSource)ctx.lookup(datasourceJndiName);
    }
    
    /** uzyskanie pocztkowego kontekstu JNDI
      * @return InitialContext
      * @throws javax.naming.NamingException */
    public static Context getInitialContext() throws NamingException {
        return new InitialContext();
    }

    public void executeSQL(String username, String password, String query ) throws SQLException {      
        PreparedStatement pstmt;        
        try {
            con = ds.getConnection(username, password);
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);            
            pstmt.execute();
            con.commit();
            pstmt.close();
        } 
        finally {
            if (con != null) con.close();
        }
    }
    
    /** */
    public static void main(String[] args) {
      String dsJndiName = "jdbc/medward_Hsql_Datasource";
      String username   = "SA";
      String password   = "";
      String query      = "SELECT * FROM PUBLIC.Doctor";
      
      Client client = new Client();
      
      try {
        client.getDatasource(dsJndiName);
      } 
      catch (NamingException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
        client.executeSQL(username, password, query);
      } 
      catch (SQLException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }      
   }
    
  
}
