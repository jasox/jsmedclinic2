package pl.jasox.medward.controllers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;

import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.domainobject.Clinic;

@RequestScoped
public class ClinicController {

  @Inject
  @ApplicationDatabase
  private IClinicDao clinicRepository;

  private Clinic     clinic;

  // ---------------------------------------------------------------------------
  
  @Produces
  @Named
  public Clinic getClinic() {
    return clinic;
  }
      
}
