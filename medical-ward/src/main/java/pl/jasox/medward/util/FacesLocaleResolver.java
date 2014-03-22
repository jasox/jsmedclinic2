package pl.jasox.medward.util;

import java.util.Locale;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class FacesLocaleResolver {  

   @Inject  
   private FacesContext facesContext; // = FacesContext.getCurrentInstance();;  
  
   public boolean isActive() {  
      return (facesContext != null) && (facesContext.getCurrentPhaseId() != null);  
   }  
  
   @Produces // @Faces  
   public Locale getLocale() {  
      if (facesContext.getViewRoot() != null) {   
         return facesContext.getViewRoot().getLocale();  
      }   
      else {
         return facesContext.getApplication().getViewHandler().calculateLocale(facesContext); 
      }    
   }  
}  
