package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.Admission;

public interface IAdmissionDao {  
  
  public abstract Admission findById(int id);

  public abstract void save(Admission admission);

  public abstract void saveOrUpdate(Admission admission);

  public abstract void delete(Admission admission);
  
  public abstract List<Admission> getAll();
  
}
