package pl.jasox.medward.util.email;

//--------------------------------------------------------------------

/**
 * Klasa reprezentująca zawartość wiadomości e-mail.
 * @contains toAddress,
 *           toName,
 *           title,
 *           message
 */
public class EmailContent {

  private String toAddress;
  private String toName;
  private String title;
  private String message;

  /** */
  public EmailContent(
      String toAddress, 
      String toName, 
      String title,
      String message ) {
    this.toAddress = toAddress;
    this.toName = toName;
    this.title = title;
    this.message = message;
  }

  public String getToAddress() {
    return toAddress;
  }

  public void setToAddress(String toAddress) {
    this.toAddress = toAddress;
  }

  public String getToName() {
    return toName;
  }

  public void setToName(String toName) {
    this.toName = toName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
//--------------------------------------------------------------------
