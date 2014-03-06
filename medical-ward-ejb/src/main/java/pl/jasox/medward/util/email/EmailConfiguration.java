package pl.jasox.medward.util.email;

//--------------------------------------------------------------------
// Java
import java.io.Serializable;

//--------------------------------------------------------------------

/**
 * Komponent wsparcia służący do zarządzania konfiguracją poczty e-mail. 
 * @contains emailHost,
 *           emailUsername,
 *           emailPassword,
 *           emailAddressForTesting
 */
public class EmailConfiguration implements Serializable {
  private static final long serialVersionUID = 1L;
  // ...
  private String emailHost;
  private String emailUsername;
  private String emailPassword;
  private String emailAddressForTesting;

  /** */
  public EmailConfiguration() {
  }

  /** */
  public EmailConfiguration(
      String emailHost, 
      String emailUsername,
      String emailPassword, 
      String emailAddressForTesting ) {
    this.emailHost     = emailHost;
    this.emailUsername = emailUsername;
    this.emailPassword = emailPassword;
    this.emailAddressForTesting = emailAddressForTesting;
  }
  
  public String getEmailAddressForTesting() {
    return emailAddressForTesting;
  }

  public String getEmailHost() {
    return emailHost;
  }

  public String getEmailPassword() {
    return emailPassword;
  }

  public String getEmailUsername() {
    return emailUsername;
  }

  public void setEmailAddressForTesting(String emailAddressForTesting) {
    this.emailAddressForTesting = emailAddressForTesting;
  }

  public void setEmailHost(String emailHost) {
    this.emailHost = emailHost;
  }

  public void setEmailPassword(String emailPassword) {
    this.emailPassword = emailPassword;
  }

  public void setEmailUsername(String emailUsername) {
    this.emailUsername = emailUsername;
  }
  
}
//--------------------------------------------------------------------
