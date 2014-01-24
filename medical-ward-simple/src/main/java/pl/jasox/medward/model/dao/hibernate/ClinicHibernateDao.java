package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.domainobject.Clinic;

public class ClinicHibernateDao extends AGenericHibernateDao implements IClinicDao {

	public ClinicHibernateDao() {
		super(Clinic.class);
	}

	@Override
	public List<Clinic> getAll() {
		List<Clinic> clinic = null;
	    try { 
	        Query query = getSession().getNamedQuery("getClinicAll");       
	        clinic     = query.list();
	    }
	    finally {
	        //session.getTransaction().commit();
	    }
		return clinic;
	}

  @Override
  public Clinic findById(int id) {
    throw new UnsupportedOperationException("Not supported yet.");     
  }

  @Override
  public void save(Clinic clinic) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void saveOrUpdate(Clinic clinic) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

  @Override
  public void delete(Clinic clinic) {
    throw new UnsupportedOperationException("Not supported yet.");    
  }

}
