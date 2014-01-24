package pl.jasox.medward.model.util.hibernate;

import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.HibernateException;

//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.SessionFactory;

/** 
   A <code>org.hibernate.Session</code> represents a single-threaded unit of work. <br/>
   The <code>org.hibernate.SessionFactory</code> is a thread-safe global object that is instantiated once. <br/>
   This class not only produces the global <code>org.hibernate.SessionFactory</code> reference in its static
   initializer, it also hides the fact that it uses a static singleton. <br/>
   We might just as well have looked up it reference from JNDI in an application server or any other 
   location for that matter.
*/
public class HibernateUtil {
  // ...
  private static Configuration  configuration;
  private static SessionFactory sessionFactory;
	
  private static final Logger log = Logger.getLogger(HibernateUtil.class.getName());
	
  static {
    try {
      // Tworzy obiekt SessionFactory (na podstawie hibernate.cfg.xml)
      //  if you don't use annotations or JDK 5.0
      configuration  = new Configuration();
      // configuration = new AnnotationConfiguration()
      sessionFactory = configuration.configure().buildSessionFactory();    		       
    } 
    catch (HibernateException ex) {
      // Log the exception. 
      log.severe("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);            
    }
  }
  
  /**
   * Returns the global SessionFactory.
   * @return SessionFactory
   */
  public static SessionFactory getSessionFactory() {
    SessionFactory sf = null;
    String sfName = configuration.getProperty(Environment.SESSION_FACTORY_NAME);
    if ( sfName != null) {
      log.info("Looking up SessionFactory in JNDI.");
      try {
        sf = (SessionFactory) new InitialContext().lookup(sfName);
      } 
      catch (NamingException ex) {
        throw new RuntimeException(ex);
      }
    } 
    else {
      sf = sessionFactory;
    }
    if (sf == null) {
      throw 
        new IllegalStateException("SessionFactory not available.");
    }  
    return sf;
  }  

  /**
   * Returns the original Hibernate configuration.
   * @return Configuration
   */
  public static Configuration getConfiguration() {
      return configuration;
  }
  
  /**
   * Closes the current SessionFactory and releases all resources. <br/>
   * The only other method that can be called on HibernateUtil after this one is <br/> 
   *   <code>rebuildSessionFactory(Configuration)</code>.
   */
  public static void shutdown() {
    log.info("Shutting down Hibernate.");
    // Close caches and connection pools
    getSessionFactory().close();

    // Clear static variables
    configuration  = null;
    sessionFactory = null;
  }
  
}

