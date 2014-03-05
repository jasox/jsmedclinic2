package pl.jasox.medward.model.dao.hibernate;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.domainobject.Kasach;


public class KasachHibernateDao extends AGenericHibernateDao implements IKasachDao {
  
  static Logger log = Logger.getLogger( KasachHibernateDao.class.getName() );

  public KasachHibernateDao() {    
    super(Kasach.class);    
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Kasach> getAll() {
    List<Kasach> kasachs = null;
      try { 
          Query query = getSession().getNamedQuery("getKasach");       
          kasachs     = query.list();
      }
      finally {
          //session.getTransaction().commit();
      }
    return kasachs;
  }

  @Override
  public Kasach findById(String id) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void save(Kasach kasach) {
    throw new UnsupportedOperationException("Not supported yet.");   
  }

  @Override
  public void saveOrUpdate(Kasach kasach) {
    throw new UnsupportedOperationException("Not supported yet.");   
  }

  @Override
  public void delete(Kasach kasach) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

}
