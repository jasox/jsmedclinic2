package pl.jasox.medward.rest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MedwardApplication extends Application {
  
   private Set<Object>   singletons = new HashSet<Object>();
   private Set<Class<?>> empty      = new HashSet<Class<?>>();

   public MedwardApplication() {
      super();
      singletons.add( new DoctorResourceRESTService() );
   }

   @Override
   public Set<Class<?>> getClasses() {
      return empty;
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
   
}
