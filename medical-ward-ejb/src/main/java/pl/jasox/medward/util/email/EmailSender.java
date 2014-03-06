package pl.jasox.medward.util.email;


// Java
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;
// Mail
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Klasa odpowiedzialna za generowanie wiadomosci e-mail. <br/>
 * Uwaga : W przypadku wystąpienia błędów podczas wysyłania e-maili, <br/> 
 *         transakcje w bazie danych nie są wycofywane!
 */
public class EmailSender {
  // ...
  private EmailConfiguration emailConfiguration;
  private Logger logger = Logger.getLogger(this.getClass().getName());

  /** */
  public EmailSender(EmailConfiguration emailConfiguration) {
    this.emailConfiguration = emailConfiguration;
  }

  /** */
  public boolean sendEMail(EmailContent emailContent) {
    // check emailConfiguration
    if ( this.emailConfiguration == null ) {
      return false;
    }
    // send e-mail
    try {
      String copyAddress = 
        emailConfiguration.getEmailAddressForTesting();
      Properties props = new Properties();
      props.put("mail.smtps.auth", "true");
      Session session = Session.getDefaultInstance(props);
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom( 
        new InternetAddress(
          emailConfiguration.getEmailUsername() + '@' + 
          emailConfiguration.getEmailHost(),
          "Internetowy system recenzowania publikacji"));
      msg.addRecipient( 
        Message.RecipientType.BCC, 
        new InternetAddress(copyAddress) );
      msg.addRecipient(
        Message.RecipientType.TO, 
        new InternetAddress(
          emailContent.getToAddress(), emailContent.getToName()));
      msg.setSubject(emailContent.getTitle());
      msg.setText(emailContent.getMessage());
      Transport t = session.getTransport("smtps");
      t.connect(
          emailConfiguration.getEmailHost(), 
          emailConfiguration.getEmailUsername(), 
          emailConfiguration.getEmailPassword());
      t.sendMessage(msg, msg.getAllRecipients());
      t.close();
    } 
    catch (MessagingException ex) {
      logger.severe(ex.getMessage());
    } 
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return true;
  }

}

