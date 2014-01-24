package pl.jasox.medward.model.service;

//--------------------------------------------------------------------
// Java
import java.util.List;

import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.model.domainobject.MedProcedure;
import pl.jasox.medward.model.domainobject.MedProcedureType;
import pl.jasox.medward.model.domainobject.Patient;
import pl.jasox.medward.util.email.EmailConfiguration;

//--------------------------------------------------------------------

/** */
public interface IReviewService {

  /** */
	@SuppressWarnings("rawtypes")
	void addNewReview(
	    MedProcedureType medProcedureType, 
	    Doctor  doctor, 
	    List   selectedAnswersArray[],
			List<MedProcedure> medProcedures, 
			Patient   reviewer,
			EmailConfiguration emailConfiguration);
	
	/** */
	List<MedProcedureType> findAll();

}
//--------------------------------------------------------------------

