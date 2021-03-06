package pl.jasox.medward.rest;

import java.util.Collections;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class TestApplicationConfig02 extends Application {
  
   private Set<Object>   singletons = new HashSet<Object>();
   private Set<Class<?>> classes    = new HashSet<Class<?>>();
   

   public TestApplicationConfig02() {
      super();
      // ...             
      classes.add(DoctorHibernateRestService.class);
      classes = Collections.unmodifiableSet(classes);
      //singletons.add(new DoctorResourceRESTService());
      //singletons = Collections.unmodifiableSet(singletons);
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
