package pl.jasox.medward.db;

import java.io.Serializable;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

/**
 * Exposes the <code>EntityManager</code>'s available for test of application <br/> 
 */
@Singleton
@Startup
public class TestEntityManagerProducer implements Serializable {	  
    
    @Produces
    @ApplicationDatabase 
    static DatabaseType actualDatabaseType = DatabaseType.AS;
     
}
