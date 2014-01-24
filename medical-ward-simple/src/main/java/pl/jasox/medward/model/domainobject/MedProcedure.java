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

/**
 * TABLE `med_procedure` <br/>
  `id_med_procedure` INT(10) unsigned NOT NULL auto_increment,  -- id procedury                    <br/>
  `symbol_doctor`    VARCHAR(10) NOT NULL,       -- symbol uprawnień wykonującego/odpowiedzialnego <br/>
  `id_admiss`        INT(10) unsigned,           -- identyfikator przyjęcia                        <br/>
  `data`             DATE,                       -- data wykonania procedury                       <br/>
  `pesel`            DECIMAL(11) NOT NULL,       -- pesel pacjenta poddanego tej procedurze        <br/>
  `symbol_kasa`      VARCHAR(10) NOT NULL,       -- symbol kasy chorych dla tej procedury          <br/>
  `primary_data`     VARCHAR(15),                -- dane dotyczące procedury ( np. ząb zabiegu )   <br/>
  `additional_data`  VARCHAR(15),                -- dodatkowe dane                                 <br/>
  `symbol_proc_type` VARCHAR(15) NOT NULL,       -- symbol typu procedury                          <br/>
  `id_proc_type`     INT(10) unsigned NOT NULL,  -- symbol typu procedury                          <br/>
  `no_adm`           DECIMAL(3)  DEFAULT 1,      -- numer kolejny w ramach przyjęcia               <br/>
  `surcharge`        BOOLEAN,                    -- czy była dopłata w ramach NFZ                  <br/>
  `amount`           DECIMAL(10,2),              -- kwota dopłaty                                  <br/> 
  `description`      VARCHAR(75),                -- opis leczenia                                  <br/> 
  `remarks`          VARCHAR(50),                -- uwagi                                          <br/>
 */
@Entity
@Table(name="med_procedure")
public class MedProcedure implements java.io.Serializable {  
	
	private static final long serialVersionUID = 1L;
	// ...
	private Integer          idMedProcedure;
	private Doctor           doctor;
	private Admission        admission;
	private Date             procedureDate;
	private Patient          patient;
	private Kasach           kasach;
	private String           primaryInfo;
	private String           additionalInfo;
  private String           symbolMedProcedureType;
	private MedProcedureType medProcedureType;
	private Double           noAdm;
	private Boolean          surcharge;
	private Double           amount;
	private String           description;
	private String           remarks;
		
	@Id
	@Column(name="id_med_procedure")
	public Integer getIdMedProcedure() {
		return idMedProcedure;
	}
	public void setIdMedProcedure(Integer idMedProcedure) {
		this.idMedProcedure = idMedProcedure;
	}

	@ManyToOne
	@JoinColumn(name="symbol_doctor", nullable=false)
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@ManyToOne
	@JoinColumn(name="id_admiss", nullable=true)
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	@Column(name="data")
	@Temporal(value = TemporalType.DATE)
	public Date getProcedureDate() {
		return procedureDate;
	}
	public void setProcedureDate(Date date) {
		this.procedureDate = date;
	}
	
	@ManyToOne
	@JoinColumn(name="pesel", nullable=false)
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@ManyToOne
	@JoinColumn(name="symbol_kasa", nullable=false)
	public Kasach getKasach() {
		return kasach;
	}
	public void setKasach(Kasach kasach) {
		this.kasach = kasach;
	}

	@Column(name="primary_data")
	public String getPrimaryInfo() {
		return primaryInfo;
	}
	public void setPrimaryInfo(String primaryInfo) {
		this.primaryInfo = primaryInfo;
	}
	
	@Column(name="additional_data")
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
  
	@ManyToOne
	@JoinColumn(name="id_proc_type", nullable=false)
	public MedProcedureType getMedProcedureType() {
		return medProcedureType;
	}
	public void setMedProcedureType(MedProcedureType medProcedureType) {
		this.medProcedureType = medProcedureType;
	}
  
  
  @Column(name="symbol_proc_type")
  public String getSymbolMedProcedureType() {
		return symbolMedProcedureType;
	}
	public void setSymbolMedProcedureType(String symbolMedProcedureType) {
		this.symbolMedProcedureType = symbolMedProcedureType;
	}
  
	@Column(name="no_adm")
	public Double getNoAdm() {
		return noAdm;
	}
	public void setNoAdm(Double noAdm) {
		this.noAdm = noAdm;
	}

	@Column(name="surcharge")
	public Boolean getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(Boolean surcharge) {
		this.surcharge = surcharge;
	}

	@Column(name="amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public MedProcedure() {
		super();
	}

	/** */
	public MedProcedure(Integer idMedProcedure, Doctor doctor,
			Admission admission, Date date, Patient patient, Kasach kasach,
			String primaryInfo, String additionalInfo, String symbolMedProcedureType,
			MedProcedureType medProcedureType, Double noAdm, Boolean surcharge,
			Double amount, String description, String remarks) {
		super();
		this.idMedProcedure = idMedProcedure;
		this.doctor = doctor;
		this.admission = admission;
		this.procedureDate = date;
		this.patient = patient;
		this.kasach = kasach;
		this.primaryInfo = primaryInfo;
		this.additionalInfo = additionalInfo;
    this.symbolMedProcedureType = symbolMedProcedureType;
		this.medProcedureType = medProcedureType;    
		this.noAdm = noAdm;
		this.surcharge = surcharge;
		this.amount = amount;
		this.description = description;
		this.remarks = remarks;
	}
	
	// ---------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "MedProcedure [idMedProcedure=" + idMedProcedure + ", doctor="
				+ doctor + ", admission=" + admission + ", date=" + procedureDate
				+ ", patient=" + patient + ", kasach=" + kasach
				+ ", primaryInfo=" + primaryInfo + ", additionalInfo="
				+ additionalInfo + ", medProcedureType=" + symbolMedProcedureType
				+ ", noAdm=" + noAdm + ", surcharge=" + surcharge + ", amount="
				+ amount + ", description=" + description + ", remarks="
				+ remarks + "]";
	}
	
}

