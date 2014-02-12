package pl.jasox.medward.db;

import java.io.Serializable;

import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
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
    
    // Baza produkcyjna, baza defaultowa dla aplikacji = DatabaseType.MySQL;
    @Inject
    @ApplicationDatabase 
    DatabaseType actualDatabaseType; 
    
    // -------------------------------------------------------------------------
    
    // wbudowana baza na serwerze aplikacji
    // (DatabaseType.AS)
    @PersistenceContext(unitName="medward_AS")    
	  private EntityManager entityManagerAs;
	  
    // testowa baza Hsql w lokalnym pliku tekstowym
    // (DatabaseType.HSQL)
    @PersistenceContext(unitName="medward_Hsql")    
	  private EntityManager entityManagerHsql;
    
    // produkcyjna baza na Amazon WS
    // (DatabaseType.MySQL)
    @PersistenceContext(unitName="medward_MySQL")    
	  private EntityManager entityManagerMySQL;
    
    // -------------------------------------------------------------------------
    // @ApplicationDatabase  
   
    @Produces
    @ApplicationDatabase        
    public EntityManager getEntityManager() { 
      
       EntityManager entityManager = null;
       
       if ( actualDatabaseType == DatabaseType.DEFAULT) {
          entityManager = entityManagerMySQL; // MySQL
       }
       if ( actualDatabaseType == DatabaseType.AS) {
          entityManager = entityManagerAs;  
       }
       if ( actualDatabaseType == DatabaseType.HSQL) {
          entityManager = entityManagerHsql; 
       }
       if ( actualDatabaseType == DatabaseType.MySQL) {
          entityManager = entityManagerMySQL; 
       }       
       return entityManager;
    }   
     
    
    public void closeEntityManager(@Disposes @ApplicationDatabase EntityManager em) {
      if ( em.isOpen() ) {      
        em.close();
      }  
    }   
    
    // -------------------------------------------------------------------------
    // @TestDatabase
    
    @Produces
    @TestDatabase(DatabaseType.DEFAULT)        
    public EntityManager getEntityManagerDefault() {       
       EntityManager entityManager = entityManagerHsql;             
       return entityManager;
    }
           
    @Produces
    @TestDatabase(DatabaseType.AS)        
    public EntityManager getEntityManagerAs() {       
       EntityManager entityManager = entityManagerAs;             
       return entityManager;
    }     
    
    @Produces
    @TestDatabase(DatabaseType.HSQL)        
    public EntityManager getEntityManagerHsql() {       
       EntityManager entityManager = entityManagerHsql;             
       return entityManager;
    }   
    
    @Produces
    @TestDatabase(DatabaseType.MySQL)        
    public EntityManager getEntityManagerMySQL() {       
       EntityManager entityManager = entityManagerMySQL;             
       return entityManager;
    }
   
       
    public void closeEntityManagerAs(@Disposes @TestDatabase(DatabaseType.AS) EntityManager em) {
      if ( em.isOpen() ) { 
        em.close();
      }  
    }     
    
    public void closeEntityManagerHsql(@Disposes @TestDatabase(DatabaseType.HSQL) EntityManager em) {
      if ( em.isOpen() ) { 
        em.close();
      }  
    }  
    
    public void closeEntityManagerMySQL(@Disposes @TestDatabase(DatabaseType.MySQL) EntityManager em) {
      if ( em.isOpen() ) { 
        em.close();
      }  
    } 
    
    public void closeEntityManagerDefault(@Disposes @TestDatabase(DatabaseType.DEFAULT) EntityManager em) {
      if ( em.isOpen() ) { 
        em.close();
      }  
    }         
   
}
