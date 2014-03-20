package pl.jasox.medward.util;

import java.util.logging.Logger;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * This class uses CDI to alias Java EE resources to CDI beans
 * 
 * <p> Example injection on a managed bean field: </p> * 
 * <pre>
 * &#064;Inject
 * private Logger log;
 * </pre>
 */
public class ResourcesProducer {
   
   @Produces
   public Logger produceLog(InjectionPoint injectionPoint) {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }
  
   @Produces
   @RequestScoped   
   public FacesContext produceFacesContext() {
      FacesContext ctx = FacesContext.getCurrentInstance();  
      if (ctx == null) {
         throw new ContextNotActiveException("FacesContext is not active"); 
      }    
      return ctx;      
   }
   
}
