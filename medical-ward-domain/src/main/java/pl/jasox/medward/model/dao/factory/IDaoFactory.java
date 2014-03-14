package pl.jasox.medward.model.dao.factory;

import pl.jasox.medward.model.dao.IAdmissionDao;
import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.dao.IMedProcedureTypeDao;
import pl.jasox.medward.model.dao.IPatientDao;

/**
 *
 * @author Janusz Swół
 */
public interface IDaoFactory {

  // Admission
  public abstract IAdmissionDao        getAdmissionDao();

  // Clinic
  public abstract IClinicDao           getClinicDao();

  // Doctor
  public abstract IDoctorDao           getDoctorDao();

  // Kasa Chorych
  public abstract IKasachDao           getKasachDao();

  // MedProcedureType
  public abstract IMedProcedureCatDao  getMedProcedureCatDao();

  // MedProcedure
  public abstract IMedProcedureDao     getMedProcedureDao();

  // MedProcedureType
  public abstract IMedProcedureTypeDao getMedProcedureTypeDao();

  // Patient
  public abstract IPatientDao          getPatientDao();
  
}
