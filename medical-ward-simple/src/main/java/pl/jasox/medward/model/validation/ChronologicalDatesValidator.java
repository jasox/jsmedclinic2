package pl.jasox.medward.model.validation;

import  pl.jasox.medward.model.domainobject.Admission;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Janusz Swół
 * @credit Antonio Goncalves
 *           APress Book - 'Beginning Java EE 7 with Glassfish 4'
 *           http://www.apress.com/          <br/>
 *           http://www.antoniogoncalves.org <br/>
 *           --
 */
public class ChronologicalDatesValidator implements ConstraintValidator<ChronologicalDates, Admission> {

  @Override
  public void initialize(ChronologicalDates constraintAnnotation) {
  }

  @Override
  public boolean isValid(Admission admission, ConstraintValidatorContext context) {  	
  	// Gdy któraś z dat jest równa null to ograniczenie wyłączamy - 
  	//     sprawdzane jest tylko wtedy gdy obie daty są ustawione, wtedy to ma sens
  	if ( admission == null ) {
  		return true;
  	}
    if ( admission.getAdmissionDate() == null || admission.getDischargeDate() == null ) {
      return true;
    }
    return admission.getAdmissionDate().getTime() < admission.getDischargeDate().getTime();  	
  } 
  	
}
