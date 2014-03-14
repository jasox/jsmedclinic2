package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.IMedwardUserRepository;
import pl.jasox.medward.model.domainobject.Doctor;

public interface IDoctorDao extends IMedwardUserRepository {
  
  public abstract Doctor       findById(String id);
  
  public abstract Doctor       findByEmail(String email); 

  public abstract void         save(Doctor doctor);

  public abstract void         saveOrUpdate(Doctor doctor);

  public abstract void         delete(Doctor doctor);

  public abstract List<Doctor> getAll();    

}
