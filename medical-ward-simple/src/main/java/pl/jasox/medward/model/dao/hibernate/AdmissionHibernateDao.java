package pl.jasox.medward.model.dao.hibernate;


import java.util.List;

import org.hibernate.Query;

import pl.jasox.medward.model.dao.IAdmissionDao;
import pl.jasox.medward.model.domainobject.Admission;

public class AdmissionHibernateDao extends AGenericHibernateDao implements IAdmissionDao {

	public AdmissionHibernateDao() {
		super(Admission.class);	}

	
	@Override
	public List<Admission> getAll() {
		List<Admission> list = null;
	    try { 
	        Query query = getSession().getNamedQuery("getAdmissionAll");       
	        list = query.list();
	    }
	    finally {
	        //session.getTransaction().commit();
	    }
		return list;
	}

  @Override
  public Admission findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void save(Admission admission) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void saveOrUpdate(Admission admission) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void delete(Admission admission) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }
  
}
