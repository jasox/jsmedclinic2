package pl.jasox.medward.model.dao.hibernate;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;

import pl.jasox.medward.model.dao.ADaoTest;
import pl.jasox.medward.model.dao.hibernate.factory.HibernateDaoFactory;
import pl.jasox.medward.model.util.hibernate.HibernateUtil;

/**
 * HibernateDaoFactory Test Base.
 *
 * @author Janusz Swół
 */
@Ignore
public class HibernateDaoTest extends ADaoTest {
	
  final static Logger log = Logger.getLogger( HibernateDaoTest.class.getName() );
  private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
  protected static void createDaoFactory() {	  
		if ( getDaoFactory() == null ) {
		  setDaoFactory( HibernateDaoFactory.getInstance() );	  
		} 
  }
  
  @Override
  protected void beforeAssertions() {	
	
    if ( getDaoFactory() == null ) {
      createDaoFactory();
    }  
    Session session = sessionFactory.getCurrentSession();     
    try {
      session.beginTransaction();      
    }
    catch ( Exception e ) {
   	  try {
		    if ( session.getTransaction().isActive() ) {
	        session.getTransaction().rollback();
		    }
	    } 
      catch ( HibernateException eh ) {
		    log.error("Could not rollback after exception!", eh);
		    eh.printStackTrace();
	    }
    }
    
  }
  
  @Override
  protected void afterAssertions() {
	
    if ( getDaoFactory() != null ) {
      Session session = sessionFactory.getCurrentSession();   
	    try {
        session.getTransaction().commit();
      }
      catch ( Exception e ) {
        try {
	  		  if ( session.getTransaction().isActive() ) {
	  		    session.getTransaction().rollback();
	  		  }
  		  } 
        catch ( HibernateException eh ) {
  		    log.error("Could not commit after exception!", eh);
  		    eh.printStackTrace();
  		  }
      }      
    } 
       
  }

  public static SessionFactory getSessionFactory() {
		return sessionFactory;
  }
	
  public static void setSessionFactory(SessionFactory factory) {
		sessionFactory = factory;
  }

  @Override
  protected void onSetUpInTransaction() throws Exception {
		// TODO Auto-generated method stub		
  } 
  
}

/*
 *  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session        session        = sessionFactory.getCurrentSession();
    Transaction    txn            = session.getTransaction();
    ADaoFactory    daoFactory     = HibernateDaoFactory.getInstance();
    try {
      txn.begin();
      // pobranie lokalizatora usług 
      IServiceLocator services = new ServiceLocator( daoFactory );
      }  
    catch (Exception e ) {
      txn.rollback();  
    }
    finally {
      System.out.println("> DONE");
    }
 */

