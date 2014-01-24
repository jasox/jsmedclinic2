package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.MedProcedureType;

public interface IMedProcedureTypeDao {	
	
	public abstract MedProcedureType findById(int id);

	public abstract void save(MedProcedureType medProcedureType);

	public abstract void saveOrUpdate(MedProcedureType medProcedureType);

	public abstract void delete(MedProcedureType medProcedureType);
   
  public abstract List<MedProcedureType> getAll();
  
}
