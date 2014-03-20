package pl.jasox.medward.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;

import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.domainobject.Patient;

@RequestScoped
@Named
public class PatientController {

  @Inject
  @ApplicationDatabase
  private IPatientDao   patientRepository;
  
  private Patient       patient;
  private List<Patient> patients;

    
  @Produces
  @Named
  public List<Patient> getPatients() {
    return patients;
  }
  
  @Produces
  @Named
  public Patient getPatient() {
    return patient;
  }

  public void onPatientListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Patient patient) {
    retrieveAllPatients();
  }

  @PostConstruct
  public void retrieveAllPatients() {
    patients = patientRepository.getAll();    
  }
  
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
    return "save";
  }
  
  public String cancel() {
    return "cancel";
  }
  
}
