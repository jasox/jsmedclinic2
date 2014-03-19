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

import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.domainobject.Kasach;

@RequestScoped
public class KasachListProducer {

  @Inject
  @ApplicationDatabase
  private IKasachDao kasachRepository;

  private List<Kasach> kasachs;

  // @Named provides access the return value via the EL variable name "kasachs" in the UI 
  // (e.g. Facelets or JSP view)
  @Produces
  @Named
  public List<Kasach> getKasachs() {
    return kasachs;
  }

  public void onKasachListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Kasach kasach) {
    retrieveAllKasachs();
  }

  @PostConstruct
  public void retrieveAllKasachs() {
    kasachs = kasachRepository.getAll();
  }
}
