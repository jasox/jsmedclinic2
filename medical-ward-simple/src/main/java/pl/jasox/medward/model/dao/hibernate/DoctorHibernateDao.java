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
		getSession().delete(doctor);		
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
    Doctor doctor = null;
    try { 
        Query query = getSession().getNamedQuery("getDoctorById");  
        query.setParameter("symbolDoctor", id);
        doctors     = query.list();   
    }
    finally {
        //session.getTransaction().commit();
    }
    if ( doctors != null ) {
        if ( !doctors.isEmpty() ) {
            doctor = (Doctor)doctors.toArray()[0];
        }
    }   
    return doctor;	    
	}
  
  @Override
	public Doctor findByEmail(String email) {
		List<Doctor> doctors = null;
    Doctor doctor = null;
    try { 
        Query query = getSession().getNamedQuery("getDoctorByEmail");  
        query.setParameter("emailAddress", email);
        doctors     = query.list();   
    }
    finally {
        //session.getTransaction().commit();
    }
    if ( doctors != null ) {
        if ( !doctors.isEmpty() ) {
            doctor = (Doctor)doctors.toArray()[0];
        }
    }   
    return doctor;	
	}
 
	@Override
	public void save(Doctor doctor) {
		getSession().save(doctor);		
	}

	@Override
	public void saveOrUpdate(Doctor doctor) {
		getSession().saveOrUpdate(doctor);		
	}	 
  
}
