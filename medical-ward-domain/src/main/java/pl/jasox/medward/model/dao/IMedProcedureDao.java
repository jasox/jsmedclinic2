package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.MedProcedure;

public interface IMedProcedureDao {
  
  public abstract MedProcedure findById(int id);

  public abstract void save(MedProcedure medProcedure);

  public abstract void saveOrUpdate(MedProcedure medProcedure);

  public abstract void delete(MedProcedure medProcedure);
  
  public abstract List<MedProcedure> getAll();
  
}
