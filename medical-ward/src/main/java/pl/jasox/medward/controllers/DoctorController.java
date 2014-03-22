package pl.jasox.medward.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.exceptions.CatchException;
import pl.jasox.medward.log.Loggable;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.domainobject.Doctor;

@RequestScoped
@Named
@Loggable
@CatchException
public class DoctorController extends AController implements Serializable {

  @Inject
  @ApplicationDatabase
  private IDoctorDao doctorRepository;

  private Doctor     doctor;
  
  // ---------------------------------------------------------------------------
  
  @Produces
  @Named
  public Doctor getDoctor() {
    return doctor;
  }  
   
}
