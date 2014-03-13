package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.domainobject.MedProcedureCat;


@ApplicationDatabase
@Stateless
public class MedProcedureCatEjbDao implements IMedProcedureCatDao, Serializable {


  @Inject
  @ApplicationDatabase
  private EntityManager em;
  
  
  public MedProcedureCatEjbDao() {    
  }
  
  @PostConstruct
  public void init() {
    // ...
  }

  // ---------------------------------------------------------------------------
  
  @Override
  public MedProcedureCat findById(int id) {
    MedProcedureCat medProcedureCat = em.find(MedProcedureCat.class, id);
    return medProcedureCat;
  }

  @Override
  public void save(MedProcedureCat medProcedureCat) {
    em.persist(medProcedureCat);
  }

  @Override
  public void saveOrUpdate(MedProcedureCat medProcedureCat) {
    em.merge(medProcedureCat);
  }

  @Override
  public void delete(MedProcedureCat medProcedureCat) {
    em.remove(medProcedureCat);
  }
  
  @Override
  public List<MedProcedureCat> getAll() {    
    String query = "SELECT p FROM MedProcedureCat p";
    List<MedProcedureCat> medProcedureCats = 
            em.createQuery(query, MedProcedureCat.class).getResultList();  
    return medProcedureCats;    
  }

}
