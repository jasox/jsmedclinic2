package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.Clinic;

public interface IClinicDao {  

  public abstract Clinic findById(int id);

  public abstract void   save(Clinic clinic);

  public abstract void   saveOrUpdate(Clinic clinic);

  public abstract void   delete(Clinic clinic);
  
  public abstract List<Clinic> getAll();
}
