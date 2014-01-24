package pl.jasox.medward.model.domainobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

import pl.jasox.medward.util.DateUtil;

import static org.junit.Assert.*;

/**
 * @author Janusz Swół
 * @credit Antonio Goncalves
 *           APress Book - Beginning Java EE 7 with Glassfish 4
 *           http://www.apress.com/          <br/>
 *           http://www.antoniogoncalves.org <br/>
 *           --
 */
public class AdmissionPersistentTest extends AbstractPersistentTest {
  
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
  private static Date admissionDate;
  private static Date dischargeDate;

  // ======================================
  // =              Unit tests            =
  // ======================================

  @Test // 1
  public void shouldPersistAndThenRemoveAdmission() throws Exception {
    
    admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("1", "Przykładowa1", null);
    Patient   patient   = new Patient(1D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000001", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(1, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();

    assertNotNull(kasach.getSymbolKasa());
    assertNotNull(patient.getPesel());
    assertNotNull(doctor.getSymbolDoctor());
    assertNotNull(admission.getIdAdmission());
    
    // Removing the objects
    tx.begin();
    em.remove(admission);
    em.remove(kasach);    
    em.remove(patient);    
    em.remove(doctor);        
    tx.commit();    
  }
  
  @Test // 2
  public void shouldFindAdmission() throws Exception {

  	admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("2", "Przykładowa2", null);
    Patient   patient   = new Patient(2D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000002", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(2, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();

    // Clear
    em.clear();

    admission = em.find(Admission.class, admission.getIdAdmission());
    assertNotNull(admission);    
    
    // Removing the object
    tx.begin();
    em.remove(admission);    
    tx.commit();
    
    admission = em.find(Admission.class, admission.getIdAdmission());
    assertNull(admission);    
  }
  
  
  @Test // 3
  public void shouldGetAReferenceToAdmission() throws Exception {
    
  	admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("3", "Przykładowa3", null);
    Patient   patient   = new Patient(3D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000003", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(3, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();   

    // Clear
    em.clear();

    admission = em.getReference(Admission.class, admission.getIdAdmission());
    assertNotNull(admission);     
  }
  

  @Test // 4
  public void shouldPersistAdmissionAndThenRefreshIt() throws Exception {

  	admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("4", "Przykładowa4", null);
    Patient   patient   = new Patient(4D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000004", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(4, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();   
    
    admission = em.find(Admission.class, admission.getIdAdmission());
    assertNotNull(admission);    
    assertEquals(admission.getAdmissionDate(), admissionDate);

    admission.setAdmissionDate(new Date());
    assertEquals(admission.getAdmissionDate(), new Date());   

    em.refresh(admission);
    assertEquals(admission.getAdmissionDate(), admissionDate);    
  }

  @Test // 5
  public void shouldCheckIfItContainsAdmission() throws Exception {

  	admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("5", "Przykładowa5", null);
    Patient   patient   = new Patient(5D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000005", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(5, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();      

    assertTrue(em.contains(admission));
    assertTrue(em.contains(kasach));
    assertTrue(em.contains(patient));
    assertTrue(em.contains(doctor));

    // Removing the objects
    tx.begin();
    em.remove(admission);
    em.remove(kasach);    
    em.remove(patient);    
    em.remove(doctor); 
    tx.commit();

    assertFalse(em.contains(admission));
    assertFalse(em.contains(kasach));
    assertFalse(em.contains(patient));
    assertFalse(em.contains(doctor));
  }

  @Test // 6
  public void shouldDetachAdmission() throws Exception {

  	admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("6", "Przykładowa6", null);
    Patient   patient   = new Patient(6D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000006", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(6, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();      

    assertTrue(em.contains(admission));

    // Detaches the object
    em.detach(admission);

    assertFalse(em.contains(admission));     
  }

  @Test // 7
  public void shouldClearAndThenMergeAdmission() throws Exception {

  	admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    Kasach    kasach    = new Kasach("7", "Przykładowa7", null);
    Patient   patient   = new Patient(7D, "Doe", "Joe", kasach);
    Doctor    doctor    = new Doctor("0000007", "Jan", "Kowalski", "jan.kowalski@mail.net");
    Admission admission = new Admission(7, patient, admissionDate, doctor, dischargeDate );    

    // Persist the objects
    tx.begin();
    em.persist(kasach);    
    em.persist(patient);    
    em.persist(doctor);   
    em.persist(admission);   
    tx.commit();      

    assertTrue(em.contains(admission));

    em.clear();
    assertFalse(em.contains(admission));

    Date dischargeDate2 = DateUtil.getAddDaysDate(dischargeDate, 2);
    admission.setDischargeDate(dischargeDate2);
    tx.begin();
    em.merge(admission);
    tx.commit();

    em.clear();
    assertFalse(em.contains(admission));

    admission = em.find(Admission.class, admission.getIdAdmission());
    assertEquals(admission.getDischargeDate(), dischargeDate2);
    assertTrue(em.contains(admission));
  }

}
