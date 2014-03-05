package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import pl.jasox.medward.model.dao.IMedProcedureTypeDao;
import pl.jasox.medward.model.domainobject.MedProcedureType;

public class MedProcedureTypeHibernateDao extends AGenericHibernateDao implements IMedProcedureTypeDao {

  public MedProcedureTypeHibernateDao() {
    super(MedProcedureType.class);
  }  

  @Override
  public MedProcedureType findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void save(MedProcedureType medProcedureType) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void saveOrUpdate(MedProcedureType medProcedureType) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void delete(MedProcedureType medProcedureType) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }
  
  @Override
  public List<MedProcedureType> getAll() {
    List<MedProcedureType> list = null;
      try { 
          Query query = getSession().getNamedQuery("getMedProcedureTypeAll");       
          list = query.list();
      }
      finally {
          //session.getTransaction().commit();
      }
    return list;
  }

}
