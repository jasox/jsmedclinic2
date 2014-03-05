package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.domainobject.MedProcedureCat;

public class MedProcedureCatHibernateDao extends AGenericHibernateDao implements IMedProcedureCatDao {

  public MedProcedureCatHibernateDao() {
    super(MedProcedureCat.class);
  }
  
  @Override
  public List<MedProcedureCat> getAll() {
    List<MedProcedureCat> list = null;
      try { 
          Query query = getSession().getNamedQuery("getMedProcedureCatAll");       
          list = query.list();
      }
      finally {
          //session.getTransaction().commit();
      }
    return list;
  }

  @Override
  public MedProcedureCat findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void save(MedProcedureCat medProcedureCat) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void saveOrUpdate(MedProcedureCat medProcedureCat) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void delete(MedProcedureCat medProcedureCat) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

}
