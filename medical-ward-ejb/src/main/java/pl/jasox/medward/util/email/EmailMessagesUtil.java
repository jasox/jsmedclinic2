package pl.jasox.medward.util.email;

//--------------------------------------------------------------------
// Java
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.model.domainobject.Patient;

/**
 * Klasa odpowiedzialna za generowanie treści emaili <br/>
 *  (lokalizowane zasoby pobierane są z pliku Messages.properties). <br/>
 *  łańcuchy są sparametryzowane
 */
public class EmailMessagesUtil {
  // ...
  private static ResourceBundle resource = 
    ResourceBundle.getBundle("isrp.messages.Messages");
  
  /** */
  public static String getString(String key, Object... params  ) {
    try {
      return MessageFormat.format(resource.getString(key), params);
    } 
    catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }
  
  /** */
  public static EmailContent newPaperVersionMessage(
      Patient author, 
      Doctor  doctor,
      String toAddress, 
      String toName) {
    String title   = resource.getString("new.version");
    String message = null;
    /*
    String message = getString(
        "new.version.msg",
        author.getFirstName(), 
        author.getLastName(),
        doctor.getSymbolDoctor(), 
        doctor.getSymbolSpec(), 
        doctor.getRemarks());
        */
    return new EmailContent(toAddress, toName, title, message);
  }
}
