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

import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.domainobject.Clinic;

@RequestScoped
public class ClinicListProducer {

  @Inject
  @ApplicationDatabase
  private IClinicDao clinicRepository;

  private List<Clinic> clinics;

  // @Named provides access the return value via the EL variable name "clinics" in the UI 
  // (e.g. Facelets or JSP view)
  @Produces
  @Named
  public List<Clinic> getClinics() {
    return clinics;
  }

  public void onClinicListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Clinic clinic) {
    retrieveAllClinics();
  }

  @PostConstruct
  public void retrieveAllClinics() {
    clinics = clinicRepository.getAll();
  }
}
