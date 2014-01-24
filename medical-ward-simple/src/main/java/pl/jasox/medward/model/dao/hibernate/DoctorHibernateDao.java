package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.domainobject.Doctor;

public class DoctorHibernateDao extends AGenericHibernateDao implements IDoctorDao {

    /** */
	public DoctorHibernateDao() {
		super(Doctor.class);
	}

	@Override
	public void delete(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Doctor> getAll() {		
		List<Doctor> doctors = null;
	    try { 
	        Query query = getSession().getNamedQuery("getDoctorAll");       
	        doctors     = query.list();   
	    }
	    finally {
	        //session.getTransaction().commit();
	    }
		return doctors;
	}

	@Override
	public IMedwardUser find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}
	

}
