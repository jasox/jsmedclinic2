package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import pl.jasox.medward.model.domainobject.Doctor;

/** TestNG Test Class */
public class DoctorDaoTest extends HibernateDaoTest {
  
  final static Logger log = Logger.getLogger( DoctorDaoTest.class.getName() );
  
  /** @BeforeMethod - before a test method is run */
  @BeforeMethod
  public void beforeMethod() {
    beforeAssertions();
  }

  /** @AfterMethod - after a test method has been run */
  @AfterMethod
  public void afterMethod() {
    afterAssertions();
  }

  /** @BeforeClass - before a test class starts */
  @BeforeClass
  public void beforeClass() {    
  }

  /** @AfterClass - after all the test methods in a certain class have been run */
  @AfterClass
  public void afterClass() {
  }
  /* To be more specific, @BeforeClass and @AfterClass wrap instances, not classes. 
   * Therefore,if you happen to create two instances of the test class MyTest in your suite 
   * definition, the corresponding @BeforeClass and @AfterClass will be run twice. */

  /** @BeforeTest - before a test starts <br/>
   *                (remember that a test is made of one or more classes) */
  @BeforeTest
  public void beforeTest() {    
  }
  
  /** @AfterTest - after all the test methods in a certain test have been run <br/>
   *               (remember that a test is made of one or more classes) */
  @AfterTest
  public void afterTest() {
  }

  /** @BeforeSuite - before a suite starts */  
  @BeforeSuite
  public void beforeSuite() { 
    createDaoFactory();    
  }
  
  /** @AfterSuite  - after all the test methods in a certain suite have been run */
  @AfterSuite
  public void afterSuite() {
    //getSessionFactory().close();
  }
  
  @Test
  public void testGetAllDoctor() {
    // fail("Not yet implemented");
    // dostęp do tabeli 'Doctor'
      log.info("> begin Doctor all");      
      List<Doctor> listDoctor = getDaoFactory().getDoctorDao().getAll();      
      for ( Doctor doctor : listDoctor ) {
        log.info(doctor);  
      }     
  }  
  
   @Test
   public void testFindById() {
    // fail("Not yet implemented");    
      log.info("> begin Doctor - getById"); 
      String id = "0000001";
      Doctor doctor = getDaoFactory().getDoctorDao().findById(id);
      assertEquals(id, doctor.getSymbolDoctor());
      log.info(doctor);     
  } 
   
  @Test
  public void testFindByEmail() {
    // fail("Not yet implemented");    
      log.info("> begin Doctor - getByEmail"); 
      String email = null;
      Doctor doctor = getDaoFactory().getDoctorDao().findByEmail(email);
      //assertEquals(email, doctor.getEmailAddress());
      log.info(doctor);     
  }
  
}
