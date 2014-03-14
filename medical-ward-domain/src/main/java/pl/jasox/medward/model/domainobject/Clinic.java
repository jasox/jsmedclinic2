package pl.jasox.medward.model.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * TABLE `clinic` <br/>
  `id_clinic`   INT(10) unsigned NOT NULL auto_increment, -- id miejsca skierowania   <br/>
  `description` VARCHAR(35) NOT NULL,                     -- opis miejsca skierowania <br/>
  `remarks`     VARCHAR(20)                               -- uwagi                    <br/>
 */
@Entity
@Table(name="clinic")
public class Clinic implements java.io.Serializable { 
  
  private static final long serialVersionUID = 1L;
  
  private Integer idClinic;
  private String  description;
  private String  remarks;
    
  @Id
  @Column(name="id_clinic")
  public Integer getIdClinic() {
    return idClinic;
  }
  public void setIdClinic(Integer idClinic) {
    this.idClinic = idClinic;
  }
  
  @Column(name="description", nullable=false)
  @NotNull
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
  public Clinic() {
    super();
  }

  /** */
  public Clinic(Integer idClinic, String description, String remarks) {
    super();
    this.idClinic = idClinic;
    this.description = description;
    this.remarks = remarks;
  }
  
  // ---------------------------------------------------------------------------

  @Override
  public String toString() {
    return "Clinic [idClinic=" + idClinic + ", description=" + description
        + ", remarks=" + remarks + "]";
  }
  

}
