package pl.jasox.medward.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 Usage : <pre>
   &#064;Inject 
   transient ResourceBundle bundle;  
   (...)  
   public void test() {  
     log.info(bundle.getString("myMessage"));  
   } </pre>   
 */
public class ResourceBundleProducer {
  
  @Inject         
  public Locale locale;  
    
  @Inject         
  private FacesContext facesContext; // = FacesContext.getCurrentInstance();  
  
  @Produces  
  public ResourceBundle getResourceBundle() {  
    return ResourceBundle.getBundle("/i18n/messages", facesContext.getViewRoot().getLocale() );  
  }  
  
}  

