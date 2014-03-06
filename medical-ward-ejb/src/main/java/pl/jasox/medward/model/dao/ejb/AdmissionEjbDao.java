package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IAdmissionDao;
import pl.jasox.medward.model.domainobject.Admission;

@Stateless
public class AdmissionEjbDao implements IAdmissionDao, Serializable {

  //@Inject
  //Logger log;
   
  @Inject
  @ApplicationDatabase
  private EntityManager em;
  

  public AdmissionEjbDao() {    
  }
  
  @PostConstruct
  public void init() {
    // ...
  }

  // ---------------------------------------------------------------------------
    
  @Override
  public List<Admission> getAll() {    
    String query = "SELECT a FROM Admission a";
    List<Admission> admissions = em.createQuery(query, Admission.class).getResultList();  
    return admissions;
  }

  @Override
  public Admission findById(int id) {
    Admission admission = em.find(Admission.class, id);
    return admission;  
  }

  @Override
  public void save(Admission admission) {
    em.persist(admission); 
  }

  @Override
  public void saveOrUpdate(Admission admission) {
    em.merge(admission);
  }

  @Override
  public void delete(Admission admission) {
    em.remove(admission);  
  }
  
}
