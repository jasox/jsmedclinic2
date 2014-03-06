package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.domainobject.MedProcedure;

@Stateless
public class MedProcedureEjbDao implements IMedProcedureDao, Serializable {

  //@Inject
  //Logger log;
   
  @Inject
  @ApplicationDatabase
  private EntityManager em;
  

  public MedProcedureEjbDao() {    
  }
  
  @PostConstruct
  public void init() {
    // ...
  }

  // ---------------------------------------------------------------------------
  
  @Override
  public MedProcedure findById(int id) {
    MedProcedure medProcedure = em.find(MedProcedure.class, id);
    return medProcedure;
  }

  @Override
  public void save(MedProcedure medProcedure) {
     em.persist(medProcedure);
  }

  @Override
  public void saveOrUpdate(MedProcedure medProcedure) {
    em.merge(medProcedure);
  }

  @Override
  public void delete(MedProcedure medProcedure) {
    em.remove(medProcedure);
  }
  
  @Override
  public List<MedProcedure> getAll() {
    String query = "SELECT p FROM MedProcedure p";
    List<MedProcedure> medProcedures = 
            em.createQuery(query, MedProcedure.class).getResultList();  
    return medProcedures;  
  }
  
}

