package pl.jasox.medward.model.service;

//--------------------------------------------------------------------
// Java
import java.util.List;

//import org.apache.myfaces.custom.fileupload.UploadedFile;

import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.model.domainobject.Patient;
import pl.jasox.medward.util.email.EmailConfiguration;

//--------------------------------------------------------------------

/** */
public interface IPaperService {

	boolean addPaperNewVersion(
	    Doctor doctor, 
	    Patient  author,
			//UploadedFile uploadedFile, 
			String       uploadPath,
			EmailConfiguration emailConfiguration);

	void update(Doctor doctor);

	void assignReviewer(
	    Doctor doctor, 
	    int   reviewerId,
			EmailConfiguration emailConfiguration);

	boolean addPaper(
	    Doctor  doctor, 
	    Patient   author, 
	    //UploadedFile uploadedFile,
			String       uploadPath, 
			EmailConfiguration emailConfiguration);

	Doctor findById(int id);

	void delete(Doctor doctor);

	void approvePaper(
	    Doctor doctor, 
	    EmailConfiguration emailConfiguration);

	void rejectPaper(
	    Doctor doctor, 
	    EmailConfiguration emailConfiguration);

	List<Doctor> getReviewerPapers(int reviewerId, String sortColumn);

	List<Doctor> getAuthorPapers(int userId, String sortColumn);

	List<Doctor> getAllPapers(String sortColumn);

	List<Doctor> getAllPapers(
	    String  sortColumn, 
	    Integer userId, 
	    Integer role);
}
//--------------------------------------------------------------------
