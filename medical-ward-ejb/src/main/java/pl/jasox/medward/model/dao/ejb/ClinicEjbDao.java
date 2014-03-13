package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.domainobject.Clinic;


@ApplicationDatabase
@Stateless
public class ClinicEjbDao implements IClinicDao, Serializable {


  @Inject
  @ApplicationDatabase
  private EntityManager em;
  

  public ClinicEjbDao() {    
  }
  
  @PostConstruct
  public void init() {
    // ...
  }

  // ---------------------------------------------------------------------------  

  @Override
  public Clinic findById(int id) {
    Clinic clinic = em.find(Clinic.class, id);
    return clinic;
  }

  @Override
  public void save(Clinic clinic) {
    em.persist(clinic); 
  }

  @Override
  public void saveOrUpdate(Clinic clinic) {
    em.merge(clinic);
  }

  @Override
  public void delete(Clinic clinic) {
    em.remove(clinic);
  }
  
  @Override
  public List<Clinic> getAll() {
    String query = "SELECT c FROM Clinic c";
    List<Clinic> clinics = em.createQuery(query, Clinic.class).getResultList();    
    return clinics;
  }
  
}
