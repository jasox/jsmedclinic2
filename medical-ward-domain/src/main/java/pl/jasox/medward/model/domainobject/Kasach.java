package pl.jasox.medward.model.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TABLE `kasaCh` <br/>
  `symbol_kasa` VARCHAR(10) NOT NULL,  -- symbol kasy chorych   <br/>                     
  `description` VARCHAR(45) NOT NULL,  -- opis kasy chorych     <br/>
  `remarks`     VARCHAR(20),           -- uwagi do kasy chorych <br/>
 */
@Entity
@Table(name="kasaCh")
public class Kasach implements java.io.Serializable { 
  private static final long serialVersionUID = 1L;
  
  private String symbolKasa;
  private String description;
  private String remarks;    
  
  @Id
  @Column(name="symbol_kasa")
  public String getSymbolKasa() {
    return symbolKasa;
  }
  public void setSymbolKasa(String symbolKasa) {
    this.symbolKasa = symbolKasa;
  }
  
  @Column(name="description", nullable=false, unique=true)
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
  
  public Kasach() {
    super();
  }
  
  /**
   * @param symbolKasa
   * @param description
   * @param remarks
   */
  public Kasach(String symbolKasa, String description, String remarks) {
    super();
    this.symbolKasa  = symbolKasa;
    this.description = description;
    this.remarks     = remarks;
  }
  
  // ---------------------------------------------------------------------------
  
  @Override
  public String toString() {
    return "Kasach [symbolKasa=" + symbolKasa + ", description="
        + description + ", remarks=" + remarks + "]";
  }  

  
}
