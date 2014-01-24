package pl.jasox.medward.db;

import java.io.Serializable;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Exposes the <code>EntityManager</code>'s available for test of application <br/> 
 */
@Singleton
@Startup
public class TestEntityManagerProducer implements Serializable {
	
	  private static final long serialVersionUID = 6970822929709709752L;
    
    DatabaseType actualDatabaseType = DatabaseType.HSQL;

		// -------------------------------------------------------------------------
    
    // wbudowana baza na serwerze aplikacji    
    @PersistenceContext(unitName="medward_AS")    
	  private EntityManager entityManagerAs;
	  
    // testowa baza Hsql w lokalnym pliku tekstowym   
    @PersistenceContext(unitName="medward_Hsql")    
	  private EntityManager entityManagerHsql;
    
    // produkcyjna baza MySQL    
    @PersistenceContext(unitName="medward_MySQL")    
	  private EntityManager entityManagerMySQL;
    
    // -------------------------------------------------------------------------
    
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
   
    
    @Produces
    @ApplicationDatabase        
    public EntityManager getEntityManager() { 
      
       EntityManager entityManager = null;
       
       if ( actualDatabaseType == DatabaseType.DEFAULT) {
          entityManager = entityManagerHsql; // HSQL
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
    
    // -------------------------------------------------------------------------
    
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
    
    
    public void closeEntityManager(@Disposes @ApplicationDatabase EntityManager em) {
      if ( em.isOpen() ) {      
        em.close();
      }  
    } 
    
}
