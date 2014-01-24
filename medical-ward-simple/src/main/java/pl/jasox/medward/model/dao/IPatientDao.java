package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.Patient;

public interface IPatientDao {	
	
	public abstract Patient findById(Double id);

	public abstract void    save(Patient patient);

	public abstract void    saveOrUpdate(Patient patient);

	public abstract void    delete(Patient patient);
  
  public abstract List<Patient> getAll();
  
}
