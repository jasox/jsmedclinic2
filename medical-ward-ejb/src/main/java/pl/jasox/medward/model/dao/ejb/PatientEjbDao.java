package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.domainobject.Patient;

@Stateless
public class PatientEjbDao implements IPatientDao, Serializable {
  
  //@Inject
  //Logger log;
	 
  @Inject
  @ApplicationDatabase
	private EntityManager em;
  

	public PatientEjbDao() {		
	}
  
  @PostConstruct
	public void init() {
		// ...
	}

	// ---------------------------------------------------------------------------

  @Override
  public Patient findById(Double pesel) {
     Patient patient = em.find(Patient.class, pesel);
		 return patient;
  }

  @Override
  public void save(Patient patient) {
     em.persist(patient);
  }

  @Override
  public void saveOrUpdate(Patient patient) {
     em.merge(patient);
  }

  @Override
  public void delete(Patient patient) {
     em.remove(patient); 
  }  
  
	@Override
	public List<Patient> getAll() {		  
    String query = "SELECT p FROM Patient p";
		List<Patient> patients = em.createQuery(query, Patient.class).getResultList();	
		return patients;
	}

}

