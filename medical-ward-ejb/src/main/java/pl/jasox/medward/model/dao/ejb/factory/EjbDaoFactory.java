package pl.jasox.medward.model.dao.ejb.factory;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
//import javax.inject.Inject;


import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.dao.IAdmissionDao;
import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.dao.IMedProcedureTypeDao;
import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.dao.factory.IDaoFactory;

/**  
 * point of access to EJB DAO's - 
 * this persistence layer is set on <b>clear</b> EJB 
 */

@ApplicationDatabase
@Stateless
public class EjbDaoFactory implements IDaoFactory, Serializable {	
  
  private static final long serialVersionUID = 8562456009982938311L;
  
  
  @EJB  private IDoctorDao           doctorDao; 
  
  @EJB  private IPatientDao          patientDao; 
  
  @EJB  private IKasachDao           kasachDao;
  
  @EJB  private IClinicDao           clinicDao;
  
  @EJB  private IAdmissionDao        admissionDao;  
  
  @EJB  private IMedProcedureDao     medProcedureDao;
  
  @EJB  private IMedProcedureTypeDao medProcedureTypeDao;
  
  @EJB  private IMedProcedureCatDao  medProcedureCatDao;    
  
  // ---------------------------------------------------------------------------
  
  public EjbDaoFactory( ) {
  }
  /*
  @EJB
  public EjbDaoFactory( IDoctorDao           doctorDao,
                        IPatientDao          patientDao,
                        IKasachDao           kasachDao,
                        IClinicDao           clinicDao,
                        IAdmissionDao        admissionDao,
                        IMedProcedureDao     medProcedureDao,
                        IMedProcedureTypeDao medProcedureTypeDao,
                        IMedProcedureCatDao  medProcedureCatDao) 
  {  
    this.doctorDao           = doctorDao;
    this.patientDao          = patientDao;
    this.kasachDao           = kasachDao;
    this.clinicDao           = clinicDao;
    this.admissionDao        = admissionDao;
    this.medProcedureDao     = medProcedureDao;
    this.medProcedureTypeDao = medProcedureTypeDao;
    this.medProcedureCatDao  = medProcedureCatDao;
  } 
  */ 
  
  @PostConstruct
  public void init() {
    // ...
  }
  
  // ---------------------------------------------------------------------------
  
  @Override
  public IPatientDao getPatientDao() {    
    return patientDao;
  }

  @Override
  public IKasachDao getKasachDao() {      
    return kasachDao;
  }

  @Override
  public IDoctorDao getDoctorDao() {    
    return doctorDao;
  }  

  @Override
  public IMedProcedureDao getMedProcedureDao() {    
    return medProcedureDao;
  }

  @Override
  public IMedProcedureTypeDao getMedProcedureTypeDao() {    
    return medProcedureTypeDao;
  }
  
  @Override
  public IMedProcedureCatDao getMedProcedureCatDao() {    
    return medProcedureCatDao;
  }
  
  @Override
  public IAdmissionDao getAdmissionDao() {    
    return admissionDao;
  }

  @Override
  public IClinicDao getClinicDao() {    
    return clinicDao;
  }
  
  // ---------------------------------------------------------------------------
    
  @Override
  public String toString() {
    return "EjbDaoFactory [" + super.toString() + "]";
  }
  
}

