package pl.jasox.medward.rest;

import java.util.Collections;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class TestApplicationConfig01 extends Application {
  
   private Set<Object>   singletons = new HashSet<Object>();
   private Set<Class<?>> classes    = new HashSet<Class<?>>();
   

   public TestApplicationConfig01() {
      super();
      // ...        
      //HashSet<Class<?>> c = new HashSet<>();
      classes.add(DoctorCRUDRestService.class);
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
