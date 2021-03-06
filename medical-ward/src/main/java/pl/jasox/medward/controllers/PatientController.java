package pl.jasox.medward.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.exceptions.CatchException;
import pl.jasox.medward.log.Loggable;
import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.domainobject.Patient;

@RequestScoped
@Named
@Loggable
@CatchException
public class PatientController extends AController implements Serializable {

  @Inject
  @ApplicationDatabase
  private IPatientDao patientRepository;
  
  private Patient     patient;
  
  // ---------------------------------------------------------------------------
    
  @Produces
  @Named
  public Patient getPatient() {
    return patient;
  }
  
  // ---------------------------------------------------------------------------
  
  public Patient retrievePatientbyPesel( String strPesel ) {  
    if ( strPesel != null ) {
      Double pesel = Double.parseDouble(strPesel);
      this.patient = patientRepository.findById(pesel);    
    }
    System.out.print(patient);
    return patient;
  }
  
  public Patient retrievePatientbyPesel( Double pesel ) { 
    if ( pesel != null ) {
      this.patient = patientRepository.findById(pesel);
      System.out.print(patient);
    }
    return patient;
  }
  
  public String savePatient() {
    // TODO - code to persist patient
    return "save";
  }
  
  public String cancel() {
    return "cancel";
  }
  
}
