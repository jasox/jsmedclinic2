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
import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.dao.factory.IDaoFactory;
import pl.jasox.medward.db.TestDatabase;
import pl.jasox.medward.model.domainobject.MedProcedure;
import pl.jasox.medward.util.ResourcesProducer;

/**
 *
 * @author Janusz Swół
 */
@RunWith(Arquillian.class)
public class MedProcedureEjbDaoTest {
 
  final static int NUMBER_OF_PROCEDURES = 44;
  
  @Inject 
  private Logger log;
  
  @Inject
  private UserTransaction utx; 	
  
  @EJB
  private IDaoFactory daoFactory;
  
  //@EJB
  private IMedProcedureDao medProcedureDao; 
  
  // ---------------------------------------------------------------------------
  
  public MedProcedureEjbDaoTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
     
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() throws NotSupportedException, SystemException {   
    medProcedureDao = daoFactory.getMedProcedureDao();   
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
       //.addClass(MedProcedureEjbDao.class)
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
   * Test of MedProcedureDao obtaining from DaoFactory.
   */
  @Test
  public void shouldDaoBeObtained() {    
    assertNotNull(medProcedureDao);       
  }

  /**
   * Test of findById method, of class MedProcedureEjbDao.
   */
  @Test
  public void testFindById() {
    log.info("testFindById");
    Integer id           = 45;  // 10.05.2012, P.06.73, 94033100000
    String  description  = "";    
    String  symbolKasach = "P/P";
    String firstName    = "Mateusz";
    String lastName     = "Sajdak";
    MedProcedure medProcedure = medProcedureDao.findById(id);
    assertEquals(id,           medProcedure.getIdMedProcedure());
    assertEquals(description,  medProcedure.getDescription());    
    assertEquals(symbolKasach, medProcedure.getKasach().getSymbolKasa());
    assertEquals(firstName,    medProcedure.getPatient().getFirstName());
    assertEquals(lastName,     medProcedure.getPatient().getLastName());
    id           = 27;  // 7.05.2012, 95010100000
    description  = "kl.1 Charisma";    
    symbolKasach = "P/P";
    String symbolType   = "P.02.47";    
    medProcedure = medProcedureDao.findById(id);
    assertEquals(id,           medProcedure.getIdMedProcedure());
    assertEquals(description,  medProcedure.getDescription());    
    assertEquals(symbolKasach, medProcedure.getKasach().getSymbolKasa());
    assertEquals(symbolType,   medProcedure.getMedProcedureType().getSymbolMedProcedureType());   
    assertEquals(symbolType,   medProcedure.getSymbolMedProcedureType());
  }

  /**
   * Test of getAll method, of class MedProcedureEjbDao.
   */
  @Test
  public void testGetAll() {  	
    log.info("testGetAll");
    MedProcedure medProcedure; 
    int i = 0;
    List<MedProcedure> result = medProcedureDao.getAll();
    Iterator<MedProcedure> iter = result.iterator();
    while ( iter.hasNext() ) {   
	      medProcedure = iter.next();        
	      log.info(medProcedure.toString());
        i++;
	  }    
    assertEquals(NUMBER_OF_PROCEDURES, i);
  }
  
  
}
