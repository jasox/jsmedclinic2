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

import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.domainobject.Kasach;

@RequestScoped
public class KasachListProducer {

  @Inject
  @ApplicationDatabase
  private IKasachDao kasachRepository;

  private List<Kasach> kasachs;
  
  // ---------------------------------------------------------------------------
  
  @Produces
  @Named
  public List<Kasach> getKasachs() {
    return kasachs;
  }
  
  // ---------------------------------------------------------------------------

  public void onKasachListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Kasach kasach) {
    retrieveAllKasachs();
  }

  @PostConstruct
  public void retrieveAllKasachs() {
    kasachs = kasachRepository.getAll();
  }
  
}
