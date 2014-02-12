package pl.jasox.medward.model.dao.ejb;

import java.io.File;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.db.DatabaseType;
import pl.jasox.medward.db.DatabaseTypeProducer;
import pl.jasox.medward.db.EntityManagerProducer;
import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.dao.factory.IDaoFactory;
import pl.jasox.medward.db.TestDatabase;
import pl.jasox.medward.model.domainobject.MedProcedureCat;
import pl.jasox.medward.util.ResourcesProducer;

/**
 *
 * @author Janusz Swół
 */
@RunWith(Arquillian.class)
public class MedProcedureCatEjbDaoTest {
 
  final static int NUMBER_OF_PROCEDURES = 34;
  
  @Inject 
  private Logger log;
  
  @Inject
  private UserTransaction utx; 	
  
  @EJB
  private IDaoFactory daoFactory;
  
  //@EJB
  private IMedProcedureCatDao medProcedureCatDao; 
  
  // ---------------------------------------------------------------------------
  
  public MedProcedureCatEjbDaoTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
     
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() throws NotSupportedException, SystemException {   
    medProcedureCatDao = daoFactory.getMedProcedureCatDao();   
    utx.begin();
  }
  
  @After
  public void tearDown() throws IllegalStateException, SecurityException, SystemException {    
    try {
      utx.commit();
    } 
    catch (RollbackException ex ) {
    	log.severe( ex.toString() );	
    }
    catch ( HeuristicMixedException ex ) {
    	 log.severe( ex.toString() ); 
    }
    catch ( HeuristicRollbackException ex ){    	
      log.severe( ex.toString() );
    }  
  }
  
  // ---------------------------------------------------------------------------
  
  @Deployment
  public static Archive<?> createDeployment() {

    File[] medical_ward_simple = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("pl.jasox.medclinic:medical-ward-simple")
       .withoutTransitivity().asFile();
    
    File[] picketlink_idm = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.picketlink.idm:picketlink-idm-core")
       .withTransitivity().asFile();
    
    return ShrinkWrap.create(WebArchive.class, "test.war")
       .addPackages(true,"pl.jasox.medward.model.dao.ejb")
       //.addClass(MedProcedureCatEjbDao.class)
       //.addClass(EjbDaoFactory.class)      
       .addClass(ResourcesProducer.class)
       .addClass(ApplicationDatabase.class)
       .addClass(TestDatabase.class)    
       .addClass(DatabaseType.class)      
       .addClass(DatabaseTypeProducer.class)  
       .addClass(EntityManagerProducer.class)
       .addAsLibraries(medical_ward_simple)       
       .addAsLibraries(picketlink_idm)       
       .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
       .addAsResource("log4j.properties", "log4j.properties")
       .addAsResource("logging.properties", "logging.properties")       
       //.addAsWebInfResource("META-INF/seam-beans.xml", "seam-beans.xml")
       .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
  }
  
  
  /**
   * Test of DaoFactory injection.
   */
  @Test
  public void shouldDaoFactoryBeInjected() {    
    assertNotNull(daoFactory);       
  }
  
  /**
   * Test of Logger injection.
   */
  @Test
  public void shouldLoggerBeInjected() {    
    assertNotNull(log);       
  }
  
  /**
   * Test of MedProcedureCatDao obtaining from DaoFactory.
   */
  @Test
  public void shouldDaoBeObtained() {    
    assertNotNull(medProcedureCatDao);       
  }

  /**
   * Test of findById method, of class MedProcedureCatEjbDao.
   */
  @Test  
  public void testFindById() {
    log.info("findById");
    Integer id           = 10;  
    String  description  = "Endodoncja";    
    String  symbolKasach = "09R";
    MedProcedureCat medProcedureCat = medProcedureCatDao.findById(id);
    assertEquals(id,           medProcedureCat.getIdMedProcedureCat());
    assertEquals(description,  medProcedureCat.getDescription());    
    assertEquals(symbolKasach, medProcedureCat.getKasach().getSymbolKasa());
    id           = 30;  
    description  = "Protetyka całkowita";    
    symbolKasach = "P/P";
    medProcedureCat = medProcedureCatDao.findById(id);
    assertEquals(id,           medProcedureCat.getIdMedProcedureCat());
    assertEquals(description,  medProcedureCat.getDescription());    
    assertEquals(symbolKasach, medProcedureCat.getKasach().getSymbolKasa());
  }

  /**
   * Test of getAll method, of class MedProcedureCatEjbDao.
   */
  @Test
  public void testGetAll() {  	
    log.info("getAll");
    MedProcedureCat medProcedureCat; 
    int i = 0;
    List<MedProcedureCat> result = medProcedureCatDao.getAll();
    Iterator<MedProcedureCat> iter = result.iterator();
    while ( iter.hasNext() ) {   
	      medProcedureCat = iter.next();        
	      log.info(medProcedureCat.toString());
        i++;
	  }    
    assertEquals(NUMBER_OF_PROCEDURES, i);
  }
  
  
}
