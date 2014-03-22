package pl.jasox.medward.log;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * This class uses CDI to alias Java EE resources to CDI beans
 * 
 * <p> Example injection on a managed bean field: </p> * 
 * <pre>
 * &#064;Inject
 * private Logger log;
 * </pre>
 */
public class LoggingProducer {
   
   @Produces
   public Logger produceLog(InjectionPoint injectionPoint) {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
   
}
