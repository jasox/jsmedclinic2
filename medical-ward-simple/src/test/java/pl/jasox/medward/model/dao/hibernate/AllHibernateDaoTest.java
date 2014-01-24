package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.jasox.medward.model.domainobject.Admission;
import pl.jasox.medward.model.domainobject.Clinic;
import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.model.domainobject.Kasach;
import pl.jasox.medward.model.domainobject.MedProcedure;
import pl.jasox.medward.model.domainobject.MedProcedureCat;
import pl.jasox.medward.model.domainobject.MedProcedureType;
import pl.jasox.medward.model.domainobject.Patient;

/** JUnit4 Test Class */
public class AllHibernateDaoTest extends HibernateDaoTest {
	
	final static Logger log = Logger.getLogger( AllHibernateDaoTest.class.getName() );

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		createDaoFactory();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//getSessionFactory().close();
	}

	@Before
	public void setUp() throws Exception {
		beforeAssertions();
	}

	@After
	public void tearDown() throws Exception {
		afterAssertions();
	}

	@Test
	public void testGetAllKasach() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'Kasach'
      log.info("> begin Kasach");      
      List<Kasach> listKasach = getDaoFactory().getKasachDao().getAll();      
      for ( Kasach kasa : listKasach ) {
    	  log.info(kasa);  
      }
      log.info("> end   Kasach");
	}
	
	@Test
	public void testGetAllPatient() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'Patient'
      log.info("> begin Patient");      
      List<Patient> listPatient = getDaoFactory().getPatientDao().getAll();      
      for ( Patient patient : listPatient ) {
    	  log.info(patient);  
      }
      log.info("> end   Patient");
	}
	
	@Test
	public void testGetAllDoctor() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'Doctor'
      log.info("> begin Doctor");      
      List<Doctor> listDoctor = getDaoFactory().getDoctorDao().getAll();      
      for ( Doctor doctor : listDoctor ) {
    	  log.info(doctor);  
      }
      log.info("> end   Doctor");
	}
	
	@Test
	public void testGetAllClinic() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'Clinic'
      log.info("> begin Clinic");      
      List<Clinic> listClinic = getDaoFactory().getClinicDao().getAll();      
      for ( Clinic clinic : listClinic ) {
    	  log.info(clinic);  
      }
      log.info("> end   Clinic");
	}
	
	@Test
	public void testGetAllMedProcedureCat() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'MedProcedureCat'
      log.info("> begin MedProcedureCat");      
      List<MedProcedureCat> listMedProcedureCat = getDaoFactory().getMedProcedureCatDao().getAll();      
      for ( MedProcedureCat medProcedureCat : listMedProcedureCat ) {
    	  log.info(medProcedureCat);  
      }
      log.info("> end   MedProcedureCat");
	}
	
	@Test
	public void testGetAllMedProcedureType() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'MedProcedureType'
      log.info("> begin MedProcedureType");      
      List<MedProcedureType> listMedProcedureType = getDaoFactory().getMedProcedureTypeDao().getAll();      
      for ( MedProcedureType medProcedureType : listMedProcedureType ) {
    	  //log.info(medProcedureType);  
      }
      log.info("> end   MedProcedureType");
	}
	
	@Test
	public void testGetAllMedProcedure() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'MedProcedure'
      log.info("> begin MedProcedure");      
      List<MedProcedure> listMedProcedure = getDaoFactory().getMedProcedureDao().getAll();      
      for ( MedProcedure medProcedure : listMedProcedure ) {
    	  //log.info(medProcedure);  
      }
      log.info("> end   MedProcedureType");
	}
	
	@Test
	public void testGetAllAdmission() {
	  // fail("Not yet implemented");
	  // dostęp do tabeli 'Admission'
      log.info("> begin Admission");      
      List<Admission> listAdmission = getDaoFactory().getAdmissionDao().getAll();      
      for ( Admission admission : listAdmission ) {
    	  //log.info(admission);  
      }
      log.info("> end   Admission");
	}

}
