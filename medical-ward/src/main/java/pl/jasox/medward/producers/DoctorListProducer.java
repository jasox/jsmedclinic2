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

import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.domainobject.Doctor;

@RequestScoped
public class DoctorListProducer {

  @Inject
  @ApplicationDatabase
  private IDoctorDao doctorRepository;

  private List<Doctor> doctors;
  
  // ---------------------------------------------------------------------------
  
  @Produces
  @Named
  public List<Doctor> getDoctors() {
    return doctors;
  }
  
  // ---------------------------------------------------------------------------

  public void onDoctorListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Doctor doctor) {
    retrieveAllDoctors();
  }

  @PostConstruct
  public void retrieveAllDoctors() {
    doctors = doctorRepository.getAll();
  }
  
}
