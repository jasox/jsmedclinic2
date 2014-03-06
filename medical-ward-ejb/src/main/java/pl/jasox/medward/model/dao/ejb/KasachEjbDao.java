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
import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.domainobject.Kasach;

@Stateless
public class KasachEjbDao implements IKasachDao, Serializable {
  
  //@Inject
  //Logger log;
   
  @Inject
  @ApplicationDatabase
  private EntityManager em;
  
  
  public KasachEjbDao() {    
  }
  
  @PostConstruct
  public void init() {
    // ...
  }

  // ---------------------------------------------------------------------------

  @Override
  public Kasach findById(String id) {
    Kasach kasach = em.find(Kasach.class, id);
    return kasach;
  }

  @Override
  public void save(Kasach kasach) {
     em.persist(kasach); 
  }

  @Override
  public void saveOrUpdate(Kasach kasach) {
     em.merge(kasach);
  }

  @Override
  public void delete(Kasach kasach) {
     em.remove(kasach);  
  }  
  
  @Override
  public List<Kasach> getAll() {
    String query = "SELECT k FROM Kasach k";
    List<Kasach> kasachs =  em.createQuery(query, Kasach.class).getResultList();    
    return kasachs;
  }

}
