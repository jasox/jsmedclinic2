package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.MedProcedureCat;

public interface IMedProcedureCatDao {	
	
	public abstract MedProcedureCat findById(int id);

	public abstract void save(MedProcedureCat medProcedureCat);

	public abstract void saveOrUpdate(MedProcedureCat medProcedureCat);

	public abstract void delete(MedProcedureCat medProcedureCat);
  
  public abstract List<MedProcedureCat> getAll();
}
