package pl.jasox.medward.model.domainobject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import pl.jasox.medward.model.validation.ChronologicalDates;
import pl.jasox.medward.model.validation.Completion;


/**
 * TABLE `admission` <br/> 
  `id_admiss`  INT(10) unsigned NOT NULL auto_increment, -- id przyjęcia        <br/>
  `primary_no` INT(10) unsigned NOT NULL, -- numer główny księgi kliniki        <br/>
  `ward_no`    INT(10) unsigned NOT NULL, -- numer odziałowy                    <br/>
  `ksg`        VARCHAR(4),                -- sygnatura księgi głównej           <br/>   
  `pesel`      DECIMAL(11) NOT NULL,      -- identyfikator pacjenta             <br/>
  `admission_date`  TIMESTAMP NOT NULL,   -- data i czas przyjęcia do szpitala  <br/>
  `admission_from`  VARCHAR(35),          -- skąd skierowany                    <br/>
  `from_remarks`    VARCHAR(25),          -- uwagi o skierowaniu                <br/>
  `symbol_doctor`   VARCHAR(10) NOT NULL, -- identyfikator lekarza prowadzącego <br/>
  `init_diagnosis`  VARCHAR(45),          -- rozpoznanie wstępne                <br/>
  `final_diagnosis` VARCHAR(45),          -- rozpoznanie ostateczne             <br/>   
  `discharge_date`  TIMESTAMP,            -- data i czas wypisania ze szpitala  <br/>
  `discharge_to`    VARCHAR(35),          -- gdzie wypisany                     <br/>
  `remarks`         VARCHAR(20)           -- uwagi                              <br/>
 */
@Entity
@Table(name="admission")
@ChronologicalDates(groups = Completion.class) // walidacja dat
public class Admission implements java.io.Serializable {	
	
	private static final long serialVersionUID = -1914052683759406259L;
	
	private Integer idAdmission;
	private Integer primaryNo;
	private Integer wardNo;
	private String  ksg;
	private Patient patient;
	private Date    admissionDate;
	private String  admissionFrom;
	private String  fromRemarks;
	private Doctor  doctor;
	private String  initDiagnosis;
	private String  finalDiagnosis;
	private Date    dischargeDate;
	private String  dischargeTo;
	private String  remarks;
	
	// ---------------------------------------------------------------------------
	
	@Id
	@Column(name="id_admiss")
	public Integer getIdAdmission() {
		return idAdmission;
	}
	public void setIdAdmission(Integer idAdmission) {
		this.idAdmission = idAdmission;
	}

	@Column(name="primary_no")
	public Integer getPrimaryNo() {
		return primaryNo;
	}
	public void setPrimaryNo(Integer primaryNo) {
		this.primaryNo = primaryNo;
	}

	@Column(name="ward_no")
	public Integer getWardNo() {
		return wardNo;
	}
	public void setWardNo(Integer wardNo) {
		this.wardNo = wardNo;
	}

	@Column(name="ksg")
	public String getKsg() {
		return ksg;	}
	public void setKsg(String ksg) {
		this.ksg = ksg;
	}

	@ManyToOne
	@JoinColumn(name="pesel", nullable=false)
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name="admission_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Past
	@NotNull
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	@Column(name="admission_from")
	public String getAdmissionFrom() {
		return admissionFrom;
	}
	public void setAdmissionFrom(String admissionFrom) {
		this.admissionFrom = admissionFrom;
	}

	@Column(name="from_remarks")
	public String getFromRemarks() {
		return fromRemarks;
	}
	public void setFromRemarks(String fromRemarks) {
		this.fromRemarks = fromRemarks;
	}

	@ManyToOne
	@JoinColumn(name="symbol_doctor", nullable=false)
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@Column(name="init_diagnosis")
	public String getInitDiagnosis() {
		return initDiagnosis;
	}
	public void setInitDiagnosis(String initDiagnosis) {
		this.initDiagnosis = initDiagnosis;
	}

	@Column(name="final_diagnosis")
	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}
	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	@Column(name="discharge_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Column(name="discharge_to")
	public String getDischargeTo() {
		return dischargeTo;
	}
	public void setDischargeTo(String dischargeTo) {
		this.dischargeTo = dischargeTo;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;	
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	// ---------------------------------------------------------------------------
	
	/** */
	public Admission() {
		super();
	}
	
	/** */
	public Admission(Date admissionDate, Date dischargeDate) {
		super();		
		this.admissionDate = admissionDate;		
		this.dischargeDate = dischargeDate;		
	}
  
  /** */
	public Admission(Integer idAdmission, Patient patient, Date admissionDate,
			             Doctor doctor, Date dischargeDate ) {
		super();
		this.idAdmission    = idAdmission;		
		this.patient        = patient;
		this.admissionDate  = admissionDate;		
		this.doctor         = doctor;		
		this.dischargeDate  = dischargeDate;		
	}
	
	/** */
	public Admission(Integer idAdmission, Integer primaryNo, Integer wardNo,
			String ksg, Patient patient, Date admissionDate, String admissionFrom,
			String fromRemarks, Doctor doctor, String initDiagnosis, String finalDiagnosis, 
			Date dischargeDate, String dischargeTo, String remarks) {
		super();
		this.idAdmission    = idAdmission;
		this.primaryNo      = primaryNo;
		this.wardNo         = wardNo;
		this.ksg            = ksg;
		this.patient        = patient;
		this.admissionDate  = admissionDate;
		this.admissionFrom  = admissionFrom;
		this.fromRemarks    = fromRemarks;
		this.doctor         = doctor;
		this.initDiagnosis  = initDiagnosis;
		this.finalDiagnosis = finalDiagnosis;
		this.dischargeDate  = dischargeDate;
		this.dischargeTo    = dischargeTo;
		this.remarks        = remarks;
	}
	
	// ---------------------------------------------------------------------------

	@Override
	public String toString() {
		return "Admission [idAdmission=" + idAdmission + ", primaryNo="
				+ primaryNo + ", wardNo=" + wardNo + ", ksg=" + ksg
				+ ", patient=" + patient + ", admissionDate=" + admissionDate
				+ ", admissionFrom=" + admissionFrom + ", fromRemarks="
				+ fromRemarks + ", doctor=" + doctor + ", initDiagnosis="
				+ initDiagnosis + ", finalDiagnosis=" + finalDiagnosis
				+ ", dischargeDate=" + dischargeDate + ", dischargeTo="
				+ dischargeTo + ", remarks=" + remarks + "]";
	}	
    
}

//--------------------------------------------------------------------

