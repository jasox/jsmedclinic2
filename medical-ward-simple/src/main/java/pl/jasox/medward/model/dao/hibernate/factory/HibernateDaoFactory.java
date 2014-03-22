package pl.jasox.medward.model.dao.hibernate.factory;

import org.apache.log4j.Logger;

import pl.jasox.medward.model.dao.IAdmissionDao;
import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.dao.IMedProcedureTypeDao;
import pl.jasox.medward.model.dao.IPatientDao;
import pl.jasox.medward.model.dao.factory.ADaoFactory;
import pl.jasox.medward.model.dao.hibernate.AdmissionHibernateDao;
import pl.jasox.medward.model.dao.hibernate.ClinicHibernateDao;
import pl.jasox.medward.model.dao.hibernate.DoctorHibernateDao;
import pl.jasox.medward.model.dao.hibernate.KasachHibernateDao;
import pl.jasox.medward.model.dao.hibernate.MedProcedureCatHibernateDao;
import pl.jasox.medward.model.dao.hibernate.MedProcedureHibernateDao;
import pl.jasox.medward.model.dao.hibernate.MedProcedureTypeHibernateDao;
import pl.jasox.medward.model.dao.hibernate.PatientHibernateDao;

/**  
 * point of access to Hibernate DAO's - 
 * this persistence layer is set on <b>clear</b> Hibernate 
 */
public class HibernateDaoFactory extends ADaoFactory {
  
   final static Logger log = Logger.getLogger( HibernateDaoFactory.class.getName() );  
  
   public static ADaoFactory getInstance() {  
    if ( instance == null ) {
      synchronized ( HibernateDaoFactory.class ) {
        if ( instance == null ) {
          instance = new HibernateDaoFactory();    
        }
      }
    }    
    return instance;
  }
   
  public HibernateDaoFactory() {
    log.info("> HibernateDaoFactory initialized ");
  } 
  
  @Override
  public IPatientDao getPatientDao() {
    IPatientDao pdao = new PatientHibernateDao();
    //log.info(pdao);
    return pdao;
  }

  @Override
  public IKasachDao getKasachDao() {    
    IKasachDao kdao = new KasachHibernateDao();
    //log.info(kdao);
    return kdao;
  }

  @Override
  public IDoctorDao getDoctorDao() {
    IDoctorDao ddao = new DoctorHibernateDao();
    //log.info(ddao);
    return ddao;
  }

  @Override
  public IMedProcedureDao getMedProcedureDao() {
    return new MedProcedureHibernateDao();
  }

  @Override
  public IMedProcedureTypeDao getMedProcedureTypeDao() {
    return new MedProcedureTypeHibernateDao();
  }
  
  @Override
  public IMedProcedureCatDao getMedProcedureCatDao() {
    return new MedProcedureCatHibernateDao();
  }
  
  @Override
  public IAdmissionDao getAdmissionDao() {
    return new AdmissionHibernateDao();
  }

  @Override
  public IClinicDao getClinicDao() {
    return new ClinicHibernateDao();
  }
  
  // ---------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "HibernateDaoFactory [" + super.toString() + "]";
  }
   
}

