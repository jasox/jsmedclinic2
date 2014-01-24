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

/**
 * TABLE `med_procedure_type` <br/>
  `id_med_proc_type` INT(10) unsigned NOT NULL auto_increment,   -- id typu procedury     <br/>
  `symbol_proc_type` VARCHAR(15) NOT NULL, -- symbol typu czynności                       <br/>
  `symbol_kasa`      VARCHAR(10) NOT NULL, -- symbol kasy chorych dla tego typu procedury <br/>
  `serial_no`        DECIMAL(10,2),        -- numer kolejny                               <br/>
  `description`      VARCHAR(120),         -- opis typu czynności                         <br/> 
  `point_value`      DECIMAL(6),           -- wartość punktowa                            <br/>
  `symbol_proc_cat`  VARCHAR(10),          -- symbol kategorii procedury ( działu )       <br/>
  `begin_date`       DATE,                 -- data wprowadzenia                           <br/>
  `expire_date`      DATE,                 -- data wycofania                              <br/>
  `medical_code`     VARCHAR(25),          -- kod medyczny procedury                      <br/> 
  `remarks`          VARCHAR(30)           -- uwagi
 */
@Entity
@Table(name="med_procedure_type")
public class MedProcedureType implements java.io.Serializable {  
	
	private static final long serialVersionUID = 1L;
	
	private Integer         idMedProcedureType;
	private String          symbolMedProcedureType;
	private Kasach          kasach;
	private Double          serialNo;
	private String          description;
	private Double          pointValue;
	private MedProcedureCat medProcedureCat;
	private Date            beginDate;
	private Date            expireDate;
	private String          medicalCode;
	private String          remarks;
	
	@Id
	@Column(name="id_med_proc_type")
	public Integer getIdMedProcedureType() {
		return idMedProcedureType;
	}
	public void setIdMedProcedureType(Integer idMedProcedureType) {
		this.idMedProcedureType = idMedProcedureType;
	}

	@Column(name="symbol_proc_type", nullable=false)
  @NotNull
	public String getSymbolMedProcedureType() {
		return symbolMedProcedureType;
	}
	public void setSymbolMedProcedureType(String symbolMedProcedureType) {
		this.symbolMedProcedureType = symbolMedProcedureType;
	}
	
	@ManyToOne
	@JoinColumn(name="symbol_kasa", nullable=false)
	public Kasach getKasach() {
		return kasach;
	}
	public void setKasach(Kasach kasach) {
		this.kasach = kasach;
	}
	
	@Column(name="serial_no")
	public Double getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Double serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="point_value")
	public Double getPointValue() {
		return pointValue;
	}
	public void setPointValue(Double pointValue) {
		this.pointValue = pointValue;
	}

	@ManyToOne
	@JoinColumn(name="symbol_proc_cat", nullable=false)
	public MedProcedureCat getMedProcedureCat() {
		return medProcedureCat;
	}
	public void setMedProcedureCat(MedProcedureCat medProcedureCat) {
		this.medProcedureCat = medProcedureCat;
	}

	@Column(name="begin_date")
	@Temporal(value = TemporalType.DATE)
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	@Column(name="expire_date")
	@Temporal(value = TemporalType.DATE)
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name="medical_code")
	public String getMedicalCode() {
		return medicalCode;
	}
	public void setMedicalCode(String medicalCode) {
		this.medicalCode = medicalCode;
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
	public MedProcedureType() {
		super();
	}
	
	/** */
	public MedProcedureType(Integer idMedProcedureType,
			String symbolMedProcedureType, Kasach kasach, Double serialNo,
			String description, Double pointValue,
			MedProcedureCat medProcedureCat, Date beginDate, Date expireDate,
			String medicalCode, String remarks) {
		super();
		this.idMedProcedureType = idMedProcedureType;
		this.symbolMedProcedureType = symbolMedProcedureType;
		this.kasach = kasach;
		this.serialNo = serialNo;
		this.description = description;
		this.pointValue = pointValue;
		this.medProcedureCat = medProcedureCat;
		this.beginDate = beginDate;
		this.expireDate = expireDate;
		this.medicalCode = medicalCode;
		this.remarks = remarks;
	}
	
	// ---------------------------------------------------------------------------

	@Override
	public String toString() {
		return "MedProcedureType [idMedProcedureType=" + idMedProcedureType
				+ ", symbolMedProcedureType=" + symbolMedProcedureType
				+ ", kasach=" + kasach + ", serialNo=" + serialNo
				+ ", description=" + description + ", pointValue=" + pointValue
				+ ", medProcedureCat=" + medProcedureCat + ", beginDate="
				+ beginDate + ", expireDate=" + expireDate + ", medicalCode="
				+ medicalCode + ", remarks=" + remarks + "]";
	}	
	
}
