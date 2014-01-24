package pl.jasox.medward.model.dao.factory;

//--------------------------------------------------------------------

import pl.jasox.medward.model.dao.IAdmissionDao;
import pl.jasox.medward.model.dao.IClinicDao;
import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.dao.IMedProcedureCatDao;
import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.dao.IMedProcedureTypeDao;
import pl.jasox.medward.model.dao.IPatientDao;

//--------------------------------------------------------------------

/**  
 * point of access to set of DAO's : <br/>
 *  IPatientDao, IDoctorDao, IKasachDao, IMedProcedureTypeDao, IMedProcedureCatDao 
 *  IClinicDao, IAdmissionDao, IClinicDao <br/>
 */
public abstract class ADaoFactory implements IDaoFactory {
	
	protected static volatile ADaoFactory instance;
	
	public static void setInstance(ADaoFactory inst) {
		instance = inst;
	}
	public static ADaoFactory getInstance() {
		return instance;
	}
	
	@Override
	public String toString() {
		return "DaoFactory [" + super.toString() + "]";
	}	
	
}
//--------------------------------------------------------------------
