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
import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.dao.factory.IDaoFactory;
import pl.jasox.medward.model.domainobject.Patient;
import pl.jasox.medward.db.TestDatabase;
import pl.jasox.medward.util.ResourcesProducer;

/**
 *
 * @author Janusz Swół
 */
@RunWith(Arquillian.class)
public class PatientEjbDaoTest {
 
  final static int NUMBER_OF_PATIENTS = 40;
  
  @Inject 
  private Logger log;
  
  @Inject
  private UserTransaction utx;   
  
  @EJB
  private IDaoFactory daoFactory;
  
  //@EJB
  private IPatientDao patientDao; 
  
  // ---------------------------------------------------------------------------
  
  public PatientEjbDaoTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
     
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() throws NotSupportedException, SystemException {   
    patientDao = daoFactory.getPatientDao();   
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
    
    File[] medical_ward_domain = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("pl.jasox.medclinic:medical-ward-domain")
       .withoutTransitivity().asFile();

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
       //.addClass(PatientEjbDao.class)
       //.addClass(EjbDaoFactory.class)      
       .addClass(ResourcesProducer.class)
       .addClass(ApplicationDatabase.class)
       .addClass(TestDatabase.class)  
       .addClass(DatabaseType.class)       
       .addClass(DatabaseTypeProducer.class)
       .addClass(EntityManagerProducer.class)
       .addAsLibraries(medical_ward_domain)       
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
   * Test of PatientDao obtaining from DaoFactory.
   */
  @Test
  public void shouldDaoBeObtained() {    
    assertNotNull(patientDao);       
  }

  /**
   * Test of findById method, of class PatientEjbDao.
   */
  @Test
  public void testFindById() {
    log.info("testFindById");
    Double id           = 94033100000D;  // pesel - jedenaście znaków    
    String firstName    = "Mateusz";
    String lastName     = "Sajdak";
    String symbolKasach = "P/P";
    Patient patient  = patientDao.findById(id);
    assertEquals(id,           patient.getPesel());
    assertEquals(firstName,    patient.getFirstName());
    assertEquals(lastName,     patient.getLastName());  
    assertEquals(symbolKasach, patient.getKasaCh().getSymbolKasa());
    id           = 98031308787D;  // pesel - jedenaście znaków    
    firstName    = "Justyna";
    lastName     = "Żuromińska";
    symbolKasach = "09R";
    patient  = patientDao.findById(id);
    assertEquals(id,           patient.getPesel());
    assertEquals(firstName,    patient.getFirstName());
    assertEquals(lastName,     patient.getLastName());  
    assertEquals(symbolKasach, patient.getKasaCh().getSymbolKasa());
  }

  /**
   * Test of getAll method, of class PatientEjbDao.
   */
  @Test
  public void testGetAll() {    
    log.info("testGetAll");
    Patient patient; 
    int i = 0;
    List<Patient> result   = patientDao.getAll();
    Iterator<Patient> iter = result.iterator();
    while ( iter.hasNext() ) {   
        patient = iter.next();        
        log.info(patient.toString());
        i++;
    }    
    assertEquals(NUMBER_OF_PATIENTS, i);
  }  
  
}
