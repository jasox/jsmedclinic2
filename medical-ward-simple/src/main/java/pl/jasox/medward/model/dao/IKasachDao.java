package pl.jasox.medward.model.dao;

import java.util.List;

import pl.jasox.medward.model.domainobject.Kasach;

public interface IKasachDao {

	public abstract Kasach findById(String id);

	public abstract void   save(Kasach kasach);

	public abstract void   saveOrUpdate(Kasach kasach);

	public abstract void   delete(Kasach kasach);	   
	
	public abstract List<Kasach> getAll();
	
}
