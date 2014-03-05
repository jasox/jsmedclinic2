package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.domainobject.Patient;

public class PatientHibernateDao extends AGenericHibernateDao implements IPatientDao {

  public PatientHibernateDao() {
    super(Patient.class);
  }
  

  /** */  
  @SuppressWarnings("unchecked")
  public List<Patient> getPatients(String sortColumn) {
    if (sortColumn == null) {
      sortColumn = "firstName";
    }
    return (List<Patient>) 
      session.createCriteria(persistedClass).addOrder(Order.asc(sortColumn)).list();
  }


  @SuppressWarnings("unchecked")
  @Override
  public List<Patient> getAll() {
    List<Patient> patients = null;
      try { 
          Query query = getSession().getNamedQuery("getPatientAll");       
          patients     = query.list();   
      }
      finally {
          //session.getTransaction().commit();
      }
    return patients;
  }

  @Override
  public Patient findById(Double id) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void save(Patient patient) {
    throw new UnsupportedOperationException("Not supported yet.");  
  }    
    
  @Override
  public void saveOrUpdate(Patient patient) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }  

  @Override
  public void delete(Patient patient) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
  
}

