package pl.jasox.medward.producers;

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
public class PatientListProducer {

  @Inject
  @ApplicationDatabase
  private IPatientDao   patientRepository;
  
  private List<Patient> patients;

  // ---------------------------------------------------------------------------
    
  @Produces
  @Named
  public List<Patient> getPatients() {
    return patients;
  }
  
  // ---------------------------------------------------------------------------

  public void onPatientListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Patient patient) {
    retrieveAllPatients();
  }

  @PostConstruct
  public void retrieveAllPatients() {
    patients = patientRepository.getAll();    
  }
    
}
