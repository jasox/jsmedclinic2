package pl.jasox.medward.model.service;

//--------------------------------------------------------------------
// Java
import java.util.List;

import pl.jasox.medward.model.domainobject.Patient;
import pl.jasox.medward.util.email.EmailConfiguration;

//--------------------------------------------------------------------

/** */
public interface IUserService {

	Patient findById(int id);

	String getAdministratorEmail();

	void update(Patient patient);

	Patient checkIfEmailExistInDB(String email);

	void registerNewAuthor(
	    Patient patient, 
	    EmailConfiguration emailConfiguration);

	void registerNewReviewer(
	    Patient   patient, 
	    String passwordCopy,
			EmailConfiguration emailConfiguration);

	Patient authenticate(String email, String password);

	boolean delete(Patient patient);

	void saveNewPassword(
	    Patient   patient, 
	    String password,
			EmailConfiguration emailConfiguration);

	List<Patient> getReviewers();

	List<Patient> getAllUsers(String sortColumn);
	
}
//--------------------------------------------------------------------

