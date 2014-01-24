package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IMedProcedureTypeDao;
import pl.jasox.medward.model.domainobject.MedProcedureType;

@Stateless
public class MedProcedureTypeEjbDao implements IMedProcedureTypeDao, Serializable {
   
  //@Inject
  //Logger log;
	 
  @Inject
  @ApplicationDatabase
	private EntityManager em;
  

	public MedProcedureTypeEjbDao() {		
	}
  
  @PostConstruct
	public void init() {
		// ...
	}

	// ---------------------------------------------------------------------------
	  
	@Override
  public MedProcedureType findById(int id) {
    MedProcedureType medProcedureType = em.find(MedProcedureType.class, id);
		return medProcedureType;
  }

  @Override
  public void save(MedProcedureType medProcedureType) {
    em.persist(medProcedureType);
  }

  @Override
  public void saveOrUpdate(MedProcedureType medProcedureType) {
    em.merge(medProcedureType);
  }

  @Override
  public void delete(MedProcedureType medProcedureType) {
    em.remove(medProcedureType);
  }
  
  @Override
	public List<MedProcedureType> getAll() {		
    String query = "SELECT p FROM MedProcedureType p";
		List<MedProcedureType> medProcedureTypes = 
            em.createQuery(query, MedProcedureType.class).getResultList();	
		return medProcedureTypes;			
	}
  
}
