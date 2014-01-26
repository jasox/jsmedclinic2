package pl.jasox.medward.model.dao.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.db.DatabaseType;
import pl.jasox.medward.db.TestDatabase;
import pl.jasox.medward.db.TestEntityManagerProducer;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.dao.ejb.factory.EjbDaoFactory;
import pl.jasox.medward.model.dao.factory.IDaoFactory;
import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.util.ResourcesProducer;

/**
 *
 * @author Janusz Swół
 */

@RunWith(Arquillian.class)
public class DoctorEjbDaoTest {
 
  final static int NUMBER_OF_DOCTORS = 10;
  
  @Inject 
  private Logger log;
  
  @Inject
  private UserTransaction utx; 	
  
  @EJB
  private IDaoFactory daoFactory;
  
  @EJB
  private IDoctorDao  doctorDao; 
  
  // ---------------------------------------------------------------------------
  
  public DoctorEjbDaoTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() throws NotSupportedException, SystemException {      
    //doctorDao = daoFactory.getDoctorDao();    
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
       .resolve("pl.jasox.medclinic2:medical-ward-simple")
       .withoutTransitivity().asFile();
    
    File[] picketlink_idm = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.picketlink.idm:picketlink-idm-core")
       .withTransitivity().asFile();
    
    return ShrinkWrap.create(WebArchive.class, "test.war")       
       .addClass(DoctorEjbDao.class)       
       .addClass(EjbDaoFactory.class)      
       .addClass(ResourcesProducer.class)
       .addClass(ApplicationDatabase.class)
       .addClass(TestDatabase.class)   
       .addClass(DatabaseType.class)      
       .addClass(TestEntityManagerProducer.class)        
       .addAsLibraries(medical_ward_simple)       
       .addAsLibraries(picketlink_idm)       
       .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
       .addAsResource("log4j.properties", "log4j.properties")
       .addAsResource("logging.properties", "logging.properties")
       .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
       .addAsWebInfResource("META-INF/seam-beans.xml", "seam-beans.xml");
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
   * Test of DoctorDao obtaining from DaoFactory.
   */
  @Test
  public void shouldDaoBeObtained() {    
    assertNotNull(doctorDao);       
  }

  /**
   * Test of findById method, of class DoctorEjbDao.
   */
  @Test
  public void testFindById() {
    log.info("findById");
    String id        = "0000007";  // siedem znaków    
    String firstName = "Michał";
    String lastName  = "Sobański";    
    Doctor doctor = doctorDao.findById(id);
    assertEquals(id,        doctor.getUsername());
    assertEquals(firstName, doctor.getFirstName());
    assertEquals(lastName,  doctor.getLastName());    
  }
  
  /**
   * Test of save and delete method, of class DoctorEjbDao.
   */
  @Test
  public void testSaveAndtestDeleteAndtestFindById() {
    log.info("save and delete and findById");
    String username  = "test";
    String firstName = "Joe";
    String lastName  = "Doe";    
    Doctor  doctor = new Doctor(username, firstName, lastName);    
    log.info(doctor.toString());
    doctorDao.save(doctor);         
    doctor = doctorDao.findById(username);
    assertEquals(username,  doctor.getUsername());
    assertEquals(firstName, doctor.getFirstName());
    assertEquals(lastName,  doctor.getLastName());
    doctorDao.delete(doctor);
    doctor = doctorDao.findById(username);
    assertNull(doctor);    
  }

  /**
   * Test of saveOrUpdate method, of class DoctorEjbDao.
   */
  @Test
  public void testSaveOrUpdateAndtestDeleteAndtestFindById() {
    log.info("saveOrUpdate and delete and findById");
    String username  = "test";
    String firstName = "Joe";
    String lastName  = "Doe";    
    Doctor  doctor = new Doctor(username, firstName, lastName);    
    log.info(doctor.toString());
    doctorDao.saveOrUpdate(doctor);         
    doctor = doctorDao.findById(username);
    assertEquals(username,  doctor.getUsername());
    assertEquals(firstName, doctor.getFirstName());
    assertEquals(lastName,  doctor.getLastName());
    doctorDao.delete(doctor);
    doctor = doctorDao.findById(username);
    assertNull(doctor);  
  }

  /**
   * Test of getAll method, of class DoctorEjbDao.
   */
  @Test
  public void testGetAll() {  	
    log.info("getAll");
    Doctor doctor; 
    int i = 0;
    List<Doctor> result = doctorDao.getAll();
    Iterator<Doctor> iter = result.iterator();
    while ( iter.hasNext() ) {   
	      doctor = iter.next();        
	      log.info(doctor.toString());
        i++;
	  }    
    assertEquals(NUMBER_OF_DOCTORS, i);
  }
  
  /**
   * Test of find method, of class DoctorEjbDao - implements method from IMedwardUserRepository
   */
  @Test
  public void testFind() {
    log.info("find");
    String username     = "0000001";
    String expectedName = "Michał Zubek";
    IMedwardUser user = doctorDao.find(username);
    assertEquals(expectedName, user.getName());    
  }
  
}
