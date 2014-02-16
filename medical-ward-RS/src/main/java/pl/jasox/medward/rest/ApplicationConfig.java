package pl.jasox.medward.rest;

import java.util.Collections;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
//import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {
  
   private Set<Object>   singletons = new HashSet<Object>();
   private Set<Class<?>> classes    = new HashSet<Class<?>>();
   

   public ApplicationConfig() {
      super();
      // ...        
      //HashSet<Class<?>> c = new HashSet<>();
      classes.add(DoctorResourceRESTService.class);
      classes = Collections.unmodifiableSet(classes);
   }
  
   @Override
   public Set<Class<?>> getClasses() {
      return classes;
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
   
}
