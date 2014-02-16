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
		session.delete(doctor);		
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
		return findById(id);
	}

	@Override
	public Doctor findById(String id) {
		List<Doctor> doctors = null;
    try { 
        Query query = getSession().getNamedQuery("getDoctorById");  
        query.setParameter("symbolDoctor", id);
        doctors     = query.list();   
    }
    finally {
        //session.getTransaction().commit();
    }
    return (Doctor)doctors.toArray()[0];		
	}
 
	@Override
	public void save(Doctor doctor) {
		session.save(doctor);		
	}

	@Override
	public void saveOrUpdate(Doctor doctor) {
		session.saveOrUpdate(doctor);		
	}	
 
  
}
