package pl.jasox.medward.controllers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;

import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.domainobject.Doctor;

@RequestScoped
public class DoctorController {

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
