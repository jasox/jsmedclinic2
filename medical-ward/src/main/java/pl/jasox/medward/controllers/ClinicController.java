package pl.jasox.medward.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.exceptions.CatchException;
import pl.jasox.medward.log.Loggable;
import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.domainobject.Clinic;

@RequestScoped
@Named
@Loggable
@CatchException
public class ClinicController extends AController implements Serializable {

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
