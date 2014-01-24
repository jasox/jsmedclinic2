package pl.jasox.medward.db;

import java.io.Serializable;

import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Exposes the <i>EntityManager's</i> available in this application <br/> 
 *  The advantage of producing the EntityManager in a separate class rather than simply injecting
 *  it into an enterprise bean is that the object can easily be reused in a typesafe way. 
 *  Also, a more complex application may want to create multiple entity managers using multiple 
 *  persistence units, and this mechanism isolates this code for easy maintenance.
 */
@Singleton
@Startup
public class EntityManagerProducer implements Serializable {
	
	  private static final long serialVersionUID = 6970822929709709751L;

		public static class Database {
      public enum Type {  
        DEFAULT, AS, HSQL, MySQL; 
      }
    }
    
    // Baza produkcyjna, baza defaultowa dla aplikacji
    Database.Type actualDatabaseType = Database.Type.MySQL;
    
    // -------------------------------------------------------------------------
    
    // wbudowana baza na serwerze aplikacji
    // (Database.Type.AS)
    @PersistenceContext(unitName="medward_AS")    
	  private EntityManager entityManagerAs;
	  
    // testowa baza Hsql w lokalnym pliku tekstowym
    // (Database.Type.HSQL)
    @PersistenceContext(unitName="medward_Hsql")    
	  private EntityManager entityManagerHsql;
    
    // produkcyjna baza na Amazon WS
    // (Database.Type.MySQL)
    @PersistenceContext(unitName="medward_MySQL")    
	  private EntityManager entityManagerMySQL;
    
    // -------------------------------------------------------------------------
    
   
    @Produces
    @ApplicationDatabase        
    public EntityManager getEntityManager() { 
      
       EntityManager entityManager = null;
       
       if ( actualDatabaseType == Database.Type.DEFAULT) {
          entityManager = entityManagerMySQL; // MySQL
       }
       if ( actualDatabaseType == Database.Type.AS) {
          entityManager = entityManagerAs;  
       }
       if ( actualDatabaseType == Database.Type.HSQL) {
          entityManager = entityManagerHsql; 
       }
       if ( actualDatabaseType == Database.Type.MySQL) {
          entityManager = entityManagerMySQL; 
       }       
       return entityManager;
    }   
     
    
    public void closeEntityManager(@Disposes @ApplicationDatabase EntityManager em) {
      if ( em.isOpen() ) {      
        em.close();
      }  
    }        
   
}
