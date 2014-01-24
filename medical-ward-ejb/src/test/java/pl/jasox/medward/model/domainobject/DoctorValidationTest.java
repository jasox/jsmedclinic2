package pl.jasox.medward.model.domainobject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Janusz Swół 
 * @credit Antonio Goncalves
 *           APress Book - 'Beginning Java EE 7 with Glassfish 4'
 *           http://www.apress.com/ <br/>
 *           http://www.antoniogoncalves.org        
 */
public class DoctorValidationTest {

  protected static ValidatorFactory vf;
  protected static Validator validator;

 
  // Lifecycle Methods  
  // ---------------------------------------------------------------------------
  
  @BeforeClass
  public static void init() {
    vf = Validation.buildDefaultValidatorFactory();
    validator = vf.getValidator();
  }

  @AfterClass
  public static void close() {
    //vf.close();  	
  }

  // ---------------------------------------------------------------------------
  
  @Test
  public void shouldRaiseNoConstraintViolation() {
    Doctor doctor = new Doctor("0000000","Joe","Doe");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    assertEquals(0, violations.size());
  }

  @Test
  public void shouldRaiseConstraintViolationSymbolDoctorNull() {
    Doctor doctor = new Doctor(null,"Joe","Doe");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  } 
  
  @Test
  public void shouldRaiseConstraintViolationSymbolDoctorTooLong() {
    Doctor doctor = new Doctor("000000000000000","Joe","Doe");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  }
  
  @Test
  public void shouldRaiseConstraintViolationLastNameNull() {
    Doctor doctor = new Doctor("0000000","Joe",null);
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  }
  
  @Test
  public void shouldRaiseConstraintViolationLastNameToShort() {
    Doctor doctor = new Doctor("0000000","Joe","D");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  }
  
  @Test
  public void shouldRaiseConstraintViolationMalformedEmail() {
    Doctor doctor = new Doctor("0000000","Joe","Doe","joemailcom");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  }
  
  @Test
  public void shouldRaiseConstraintViolationEmailToShort() {
    Doctor doctor = new Doctor("0000000","Joe","Doe","j@m.c");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  }
  
  @Test
  public void shouldRaiseConstraintViolationEmailWithoutDot() {
    Doctor doctor = new Doctor("0000000","Joe","Doe","joe.doe@mailcom");
    Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
    displayConstraintViolations(violations);
    assertEquals(1, violations.size());    
  }
  
  // ---------------------------------------------------------------------------

  private void displayConstraintViolations(Set<ConstraintViolation<Doctor>> constraintViolations) {
    for (ConstraintViolation<Doctor> constraintViolation : constraintViolations) {
      System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() +
           "." + constraintViolation.getPropertyPath() + " - Invalid Value = " + 
           constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());

    }
  }
  
}