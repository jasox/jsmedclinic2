package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.domainobject.MedProcedure;

public class MedProcedureHibernateDao extends AGenericHibernateDao implements IMedProcedureDao {

	public MedProcedureHibernateDao() {
		super(MedProcedure.class);
	}
	
	@Override
	public List<MedProcedure> getAll() {
		List<MedProcedure> list = null;
	    try { 
	        Query query = getSession().getNamedQuery("getMedProcedureAll");       
	        list = query.list();
	    }
	    finally {
	        //session.getTransaction().commit();
	    }
		return list;
	}

  @Override
  public MedProcedure findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void save(MedProcedure medProcedure) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void saveOrUpdate(MedProcedure medProcedure) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void delete(MedProcedure medProcedure) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }
  
}

