package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.domainobject.Doctor;


@Stateless
public class DoctorEjbDao implements IDoctorDao, Serializable {
  
  private static final long serialVersionUID = -2046140499998287419L;

  //@Inject
  //Logger log;
   
  @Inject
  @ApplicationDatabase
  private EntityManager em;

  /** */
  public DoctorEjbDao() {    
  }
  
  @PostConstruct
  public void init() {
    // ...
  }
  
  // ---------------------------------------------------------------------------
  
  @Override
  public void delete(Doctor doctor) {
    em.remove(doctor);    
  }
  
  @Override
  public Doctor findById(String id) {
    Doctor doctor = em.find(Doctor.class, id);
    return doctor;
  }
  
  @Override
  public Doctor findByEmail(String email) {
    Doctor doctor = null; // em.find(Doctor.class, id);
    return doctor;
  }
  
  @Override
  public void saveOrUpdate(Doctor doctor) {
    em.merge(doctor);
  }
  
  @Override
  public void save(Doctor doctor) {
    em.persist(doctor);
  }   

  // ---------------------------------------------------------------------------
  
  @Override
  public List<Doctor> getAll() {      
    String query = "SELECT d FROM Doctor d";
    List<Doctor> doctors = em.createQuery(query, Doctor.class).getResultList();     
    return doctors;
  }

  @Override
  public IMedwardUser find(String username) {
    return findById(username);    
  }
  
  @Override
  public void store(IMedwardUser user) {
    this.saveOrUpdate( (Doctor)user );  
  }

  @Override
  public void remove(IMedwardUser user) {
    this.delete( (Doctor)user );  
  }
  
  // ---------------------------------------------------------------------------  
  
  public EntityManager getEntityManager() {
    return em;
  }

}
