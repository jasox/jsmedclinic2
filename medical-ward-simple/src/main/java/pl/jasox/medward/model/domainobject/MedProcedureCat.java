package pl.jasox.medward.model.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TABLE `med_procedure_cat`                                                                 <br/>
  `id_med_proc_cat` INT(10) unsigned NOT NULL auto_increment,   -- id kategorii              <br/>
  `symbol_kasa`     VARCHAR(10) NOT NULL, -- symbol kasy chorych dla tej kategorii procedury <br/>
  `symbol_proc_cat` VARCHAR(10) NOT NULL, -- symbol tej kategorii procedury                  <br/>
  `description`     VARCHAR(55),          -- opis                                            <br/>
  `remarks`         VARCHAR(20)           -- uwagi                                           <br/>
 */
@Entity
@Table(name="med_procedure_cat")
public class MedProcedureCat implements java.io.Serializable { 
	
	private static final long serialVersionUID = 1L;
  
  private Integer idMedProcedureCat;
  private String  symbolMedProcedureCat;
  private String  description;
  private String  remarks;  
  private Kasach  kasach;  

  @Id
	@Column(name="id_med_proc_cat")
  public Integer getIdMedProcedureCat() {
    return idMedProcedureCat;
  }
  public void setIdMedProcedureCat(Integer idMedProcedureCat) {
    this.idMedProcedureCat = idMedProcedureCat;
  }

  @Column(name="symbol_proc_cat", nullable=false, unique=true)
  public String getSymbolMedProcedureCat() {
    return symbolMedProcedureCat;
  }
  public void setSymbolMedProcedureCat(String symbolMedProcedureCat) {
    this.symbolMedProcedureCat = symbolMedProcedureCat;
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

  @ManyToOne
	@JoinColumn(name="symbol_kasa", nullable=false)
  public Kasach getKasach() {
    return kasach;
  }
  public void setKasach(Kasach kasach) {
    this.kasach = kasach;
  }
  
  // ---------------------------------------------------------------------------
  
  public MedProcedureCat() {
    super();
  }  

  public MedProcedureCat(Integer idMedProcedureCat, 
                         String symbolMedProcedureCat, 
                         String description, 
                         String remarks, 
                         Kasach kasach) {
    this.idMedProcedureCat = idMedProcedureCat;
    this.symbolMedProcedureCat = symbolMedProcedureCat;
    this.description = description;
    this.remarks = remarks;
    this.kasach = kasach;
  }
  
  // ---------------------------------------------------------------------------

  @Override
  public String toString() {
    return "MedProcedureCat{" + "idMedProcedureCat=" + idMedProcedureCat + 
            ", symbolMedProcedureCat=" + symbolMedProcedureCat + ", description=" 
    		    + description + ", remarks=" + remarks + ", kasach=" + kasach + '}';
  }  
  
	
}

