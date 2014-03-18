package pl.jasox.medward.model.domainobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import pl.jasox.medward.model.validation.Email;
import pl.jasox.medward.model.IMedwardUser;


/**
 * TABLE doctor <br/>
  id_doctor     INT(10) unsigned NOT NULL auto_increment, -- id lekarza <br/> 
  symbol_doctor VARCHAR(10) NOT NULL ,  -- symbol uprawnień lekarza     <br/> 
  symbol_spec   VARCHAR(15),            -- symbol specializacji         <br/>
  first_name    VARCHAR(15),            -- imię lekarza                 <br/>
  last_name     VARCHAR(25)  NOT NULL,  -- nazwisko lekarza             <br/> 
  email_adress  VARCHAR(75),            -- email                        <br/>
  password      VARCHAR(32),            -- hasło do systemu             <br/>  
  remarks       VARCHAR(40)             -- uwagi                        <br/>
 */
@Entity                           // JPA annotations   
@Table(name="doctor")
@XmlRootElement(name = "doctor")  // JAXB annotations  
@XmlType(propOrder = { "idDoctor", "symbolDoctor", "symbolSpec", "firstName", 
                       "lastName", "emailAddress", "password", "remarks", "loggedIn" })
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Doctor implements IMedwardUser, Serializable {
  
  private static final long serialVersionUID = 5895563461304230955L;
  
  private Integer idDoctor;  
  private String  symbolDoctor;
  private String  symbolSpec;
  private String  firstName;
  private String  lastName;  
  private String  emailAddress;
  private String  password;
  private String  remarks;
  private boolean loggedIn; // transient
  
  // Getters and Setters
  // ---------------------------------------------------------------------------
  
  /** Identyfikator typu <b>Integer</b> nie jest w tym wypadku kluczem głównym, <br/>
   *  tylko parametrem pomocniczym, wprowadzonym dla możliwych poźniejszych zastosowań */
  @Column(name="id_doctor")  
  @XmlAttribute(name = "id")
  public Integer getIdDoctor() {
    return idDoctor;
  }
  public void setIdDoctor(Integer idDoctor) {
    this.idDoctor = idDoctor;
  }
  
  /** Kluczem głównym dla doktorów jest numer lekarski typu <b>String</b> (symbol uprawnień lekarza) */
  @Id
  @Column(name="symbol_doctor")
  @Size(min = 1, max = 10)
  @NotNull
  @XmlElement(name = "symbol-doctor")
  public String getSymbolDoctor() {
    return symbolDoctor;
  }
  public void setSymbolDoctor(String symbolDoctor) {
    this.symbolDoctor = symbolDoctor;
  }  
  
  @Column(name="symbol_spec")
  @Size(min = 1, max = 15) 
  @XmlElement(name = "symbol-spec")
  public String getSymbolSpec() {
    return symbolSpec;
  }
  public void setSymbolSpec(String symbolSpec) {
    this.symbolSpec = symbolSpec;
  }
  
  @Column(name="first_name")
  @Size(max = 15)
  @XmlElement(name = "first-name")
  public String getFirstName() {
    return firstName;
  }  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  @Column(name="last_name", nullable=false)
  @Size(min = 2, max = 25)
  @NotNull
  @XmlElement(name = "last-name")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  @Column(name="email_adress")
  @Email
  @XmlElement(name = "email-address")
  @Override
  public String getEmailAddress() {
    return emailAddress;
  }
  @Override
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  
  @Column(name="password")
  @Size(min = 3, max = 32)
  @XmlElement(name = "password")
  @Override
  public String getPassword() {
    return password;
  }
  @Override
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Column(name="remarks")
  @Size(max = 40)
  @XmlElement(name = "remarks")
  public String getRemarks() {
    return remarks;
  }
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }
  
  // Constructors
  // ---------------------------------------------------------------------------
  
  /**   */
  public Doctor() {
    super();
  }
  
  /**   */
  public Doctor(String symbolDoctor,  String firstName, String lastName) {
    super();    
    this.symbolDoctor = symbolDoctor;    
    this.firstName    = firstName;
    this.lastName     = lastName;    
  }
  
  /**   */
  public Doctor(String symbolDoctor,  String firstName, String lastName, String emailAddress) {
    super();    
    this.symbolDoctor = symbolDoctor;    
    this.firstName    = firstName;
    this.lastName     = lastName;
    this.emailAddress = emailAddress;
  }
  
  /**   */
  public Doctor(Integer idDoctor, String symbolDoctor, String symbolSpec,
      String firstName, String lastName, String emailAddress,
      String password, String remarks, boolean loggedIn) {
    super();
    this.idDoctor     = idDoctor;
    this.symbolDoctor = symbolDoctor;
    this.symbolSpec   = symbolSpec;
    this.firstName    = firstName;
    this.lastName     = lastName;
    this.emailAddress = emailAddress;
    this.password     = password;
    this.remarks      = remarks;
    this.loggedIn     = loggedIn;
  }
  
  // Methods
  // ---------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Doctor [idDoctor=" + idDoctor + ", symbolDoctor="
        + symbolDoctor + ", symbolSpec=" + symbolSpec + ", firstName="
        + firstName + ", lastName=" + lastName + ", emailAddress="
        + emailAddress + ", password=" + password + ", remarks="
        + remarks + ", loggedIn=" + loggedIn + "]";
  }
  
  /** For interface <i>IMedwardUser</i>
   *  @return  firstName + " " + lastName : dla doktora */
  @Transient
  @XmlTransient
  @Override
  public String getName() {    
    return getFirstName() + " " + getLastName();
  }
  
  /** For interface <i>IMedwardUser</i> - Loginem doktora jest jego numer lekarski */
  @Transient
  @XmlTransient
  @Override  
  public String getUsername() {    
    return this.symbolDoctor;
  }  
  @Override
  public void setUsername(String username) {
    this.symbolDoctor = username;    
  }
  
  /** For interface <i>IMedwardUser</i> - czy użytkownik jest prawidłowo zalogowany */
  @Transient
  @XmlTransient
  @Override  
  public boolean getLoggedIn() {    
    return this.loggedIn;
  }  
  @Override
  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;    
  }
  
  /** For interface <i>User</i> in PicketLink - ID doktora jest jego numer lekarski */
  @Transient
  @XmlTransient
  @Override
  public String getId() {    
    return getUsername();
  }
  
  /** For interface <i>User</i> in PicketLink */
  @Transient
  @XmlTransient
  @Override
  public String getKey() {
    // For User this will return same value as getId(). 
    return getId();
  }  
  
}

/*
@XmlType
When property (method) access is used, the entries in the propOrder attribute correspond 
to the property names. When field (instance variable) access is used, the entries 
in the propOrder attribute correspond to the field names.
*/
