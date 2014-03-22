package pl.jasox.medward.util;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * This class uses CDI to alias Java EE resources to CDI beans
 * 
 * <p> Example injection on a managed bean field: </p> * 
 * <pre>
 * &#064;Inject
 * private FacesContext facesContex;
 * </pre>
 */
public class FacesContextProducer {
   
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
