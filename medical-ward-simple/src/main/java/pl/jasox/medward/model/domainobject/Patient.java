package pl.jasox.medward.model.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
  `pesel`         DECIMAL(11) NOT NULL,  -- pesel pacjenta                      <br/>  
  `last_name`     VARCHAR(35) NOT NULL,  -- nazwisko pacjenta                   <br/>
  `first_name`    VARCHAR(25),           -- imię pacjenta                       <br/> 
  `sex`           VARCHAR(6),            -- płeć pacjenta                       <br/>
  `zip_code`      VARCHAR(6),            -- kod zamieszkania pacjenta           <br/>
  `city`          VARCHAR(20),           -- miejscowość zamieszkania pacjenta   <br/>
  `street`        VARCHAR(55),           -- adres zamieszkania pacjenta         <br/>  
  `phone`         VARCHAR(15),           -- telefon kontaktowy                  <br/>
  `workplace`     VARCHAR(35),           -- miejsce pracy                       <br/>
  `occupation`    VARCHAR(35),           -- zawód wykonywany                    <br/>   
  `insurance`     BOOLEAN,               -- czy pacjent jest ubezpieczony w NFZ <br/>
  `symbol_kasa`   VARCHAR(10) NOT NULL,  -- symbol kasy chorych                 <br/> 
  `contact`       VARCHAR(35),           -- osoba kontaktowa                    <br/>
  `remarks`       VARCHAR(20),           -- uwagi                               <br/>
 */
@Entity
@Table(name="patient")
public class Patient implements java.io.Serializable {  
	private static final long serialVersionUID = 1L;
	// ...
	private Double   pesel;  // Double because legacy database, display : getStrPesel()
	private String   lastName;
	private String   firstName;	
	private String   sex;
	private String   zipCode;
	private String   city;
	private String   street;	
	private String   phone;	
	private String   workplace;
	private String   occupation;
	private Boolean  insurance;
	private Kasach   kasaCh;	
	private String   contact;
	private String   remarks;
	
	@Id
	@Column(name="pesel")
	public Double getPesel() {
		return pesel;
	}
	public void setPesel(Double pesel) {
		this.pesel = pesel;
	}
	/** Original PESEL is double because legacy database */
  @Transient
	public String getStrPesel() {
		String strPesel = (new Long(pesel.longValue())).toString();
		return strPesel;
	}
	
	@Column(name="last_name")
  @NotNull
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name="zip_code")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="workplace")
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	
	@Column(name="occupation")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Column(name="insurance")
	public Boolean isInsurance() {
		return insurance;
	}
	public void setInsurance(Boolean insurance) {
		this.insurance = insurance;
	}
  
	@ManyToOne
	@JoinColumn(name="symbol_kasa")  
	public Kasach getKasaCh() {
		return kasaCh;
	}
	public void setKasaCh(Kasach kasaCh) {
		this.kasaCh = kasaCh;
	}
	
	@Column(name="contact")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// ---------------------------------------------------------------------------
	
	/** */
	public Patient() {
		super();
	}
  
  /** */
	public Patient(Double pesel, String lastName, String firstName) {
		super();
		this.pesel = pesel;
		this.lastName  = lastName;
		this.firstName = firstName;		
	}
  
  /** */
	public Patient(Double pesel, String lastName, String firstName, Kasach kasaCh) {
		super();
		this.pesel = pesel;
		this.lastName  = lastName;
		this.firstName = firstName;		
		this.kasaCh = kasaCh;
		
	}
	
	/** */
	public Patient(Double pesel, String lastName, String firstName,
			String sex, String zipCode, String city, String street,
			String phone, String workplace, String occupation,
			boolean insurance, Kasach kasaCh, String contact, String remarks) {
		super();
		this.pesel = pesel;
		this.lastName = lastName;
		this.firstName = firstName;
		this.sex = sex;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.phone = phone;
		this.workplace = workplace;
		this.occupation = occupation;
		this.insurance = insurance;
		this.kasaCh  = kasaCh;
		this.contact = contact;
		this.remarks = remarks;
	}
	
	// ---------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "Patient [pesel=" + getStrPesel() + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", sex=" + sex + ", zipCode="
				+ zipCode + ", city=" + city + ", street=" + street
				+ ", phone=" + phone + ", kasaCh=" + kasaCh + "]";
	}	
	

}


