package pl.jasox.medward.controllers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;

import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.domainobject.Kasach;

@RequestScoped
public class KasachController {

  @Inject
  @ApplicationDatabase
  private IKasachDao kasachRepository;

  private Kasach     kasach;
  
  // ---------------------------------------------------------------------------
  
  @Produces
  @Named
  public Kasach getKasach() {
    return kasach;
  }
  
}
