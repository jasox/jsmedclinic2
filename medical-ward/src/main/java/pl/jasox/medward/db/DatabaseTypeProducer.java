package pl.jasox.medward.db;

import java.io.Serializable;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

/**
 * Exposes the <code>DatabaseType</code> available for the application <br/> 
 */
@Singleton
@Startup
public class DatabaseTypeProducer implements Serializable {
   
    @Produces
    @ApplicationDatabase 
    static DatabaseType actualDatabaseType = DatabaseType.HSQL;
  
}
