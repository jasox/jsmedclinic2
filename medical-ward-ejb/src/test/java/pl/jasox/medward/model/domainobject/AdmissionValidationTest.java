package pl.jasox.medward.model.domainobject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.jasox.medward.model.validation.Completion;
import pl.jasox.medward.model.validation.Creation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import pl.jasox.medward.util.DateUtil;

/**
 * @author Janusz Swół
 * @credit Antonio Goncalves
 *           APress Book - 'Beginning Java EE 7 with Glassfish 4'
 *           http://www.apress.com/          <br/>
 *           http://www.antoniogoncalves.org <br/>
 *           --
 */
public class AdmissionValidationTest {

  protected static ValidatorFactory vf;
  protected static Validator validator;

  private static Date creationDate;
  private static Date admissionDate;
  private static Date dischargeDate;


  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @BeforeClass
  public static void init() throws ParseException {
    vf = Validation.buildDefaultValidatorFactory();
    validator = vf.getValidator();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");

    creationDate  = dateFormat.parse("01.12.2013 00:00");
    admissionDate = dateFormat.parse("02.12.2013 00:00");
    dischargeDate = dateFormat.parse("13.12.2013 00:00");
    
    System.out.println("--- AdmissionValidationTest ---");
    System.out.println(creationDate);
    System.out.println(admissionDate);
    System.out.println(dischargeDate);
  }

  @AfterClass
  public static void close() {
    //vf.close();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  @Test  // 1
  public void shouldRaiseNoConstraintsViolation() {  	
    Admission admission = new Admission();   
    admission.setAdmissionDate(admissionDate);
    Set<ConstraintViolation<Admission>> violations = 
    		validator.validate(admission, Default.class, Creation.class);
    assertEquals(0, violations.size());
    admission.setDischargeDate(dischargeDate);
    violations = validator.validate(admission, Default.class, Completion.class);
    assertEquals(0, violations.size());    
  }
 
  @Test  // 2
  public void shouldRaiseConstraintsViolationCauseAdmissionDateIsThenSetToNull() {  	
    Admission admission = new Admission(); 
    admission.setAdmissionDate(admissionDate);
    Set<ConstraintViolation<Admission>> violations = 
    		validator.validate(admission, Default.class, Creation.class);
    assertEquals(0, violations.size());
    admission.setAdmissionDate(null);
    admission.setDischargeDate(dischargeDate);
    violations = validator.validate(admission, Default.class, Completion.class);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size()); // Raises @NotNull but no @ChronologicalDates
  }
  
  @Test // 3-1
  public void shouldRaiseConstraintsViolationCauseAdmissionDateDischargeDateAreThenSetToNull_1() {  	
    Admission admission = new Admission();
    admission.setAdmissionDate(admissionDate);
    Set<ConstraintViolation<Admission>> violations = 
    		validator.validate(admission, Default.class, Creation.class);
    assertEquals(0, violations.size()); // w fazie kreacji @ChronologicalDates 
                                        // oraz @NotNull nie jest sprawdzane    
  }  
  
  @Test // 3-2
  public void shouldRaiseConstraintsViolationCauseAdmissionDateDischargeDateAreThenSetToNull_2() {  	
    Admission admission = new Admission();      
    admission.setAdmissionDate(null);    
    admission.setDischargeDate(null);
    Set<ConstraintViolation<Admission>> violations = 
        validator.validate(admission, Default.class, Completion.class);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size()); // Raises @NotNull but no @ChronologicalDates         
  }

  @Test // 4-1
  public void shouldRaiseConstraintsViolationCauseAdmissionDateBiggerThanDischargeDate_1() {    
    Admission admission = new Admission();
    admission.setAdmissionDate(dischargeDate);
    admission.setDischargeDate(admissionDate);
    Set<ConstraintViolation<Admission>> violations = validator.validate(admission, Creation.class);
    assertEquals(0, violations.size()); // Not raises @ChronologicalDates because it is Creation phase    
  }
  
  @Test // 4-2
  public void shouldRaiseConstraintsViolationCauseAdmissionDateBiggerThanDischargeDate_2() {    
    Admission admission = new Admission();
    admission.setAdmissionDate(dischargeDate);
    admission.setDischargeDate(admissionDate);    
    Set<ConstraintViolation<Admission>> violations = validator.validate(admission, Completion.class);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size()); // Raises @ChronologicalDates ( because it is Completion phase )
  }

  @Test  // 5
  public void shouldRaiseConstraintsViolationCauseAmissionDateInTheFuture() {  
    Admission admission = new Admission();
    admissionDate = DateUtil.getAddDaysDate( new Date(), 2); // 2 days in the future
    admission.setAdmissionDate(admissionDate);
    Set<ConstraintViolation<Admission>> violations = validator.validate(
            admission, Default.class, Creation.class, Completion.class);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());  // Raises @Past
  }
  
  // ---------------------------------------------------------------------------

  private void displayConstraintViolations(Set<ConstraintViolation<Admission>> constraintViolations) {
    for (ConstraintViolation<Admission> constraintViolation : constraintViolations) {
      System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() + "." +
         constraintViolation.getPropertyPath() + " - Invalid Value = " + 
         constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());
    }
  }
  
}