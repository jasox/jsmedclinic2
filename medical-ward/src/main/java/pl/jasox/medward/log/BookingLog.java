package pl.jasox.medward.log;

import org.jboss.solder.logging.Log;
import org.jboss.solder.logging.Logger.Level;
import org.jboss.solder.logging.MessageLogger;
import org.jboss.solder.messages.Message;

/**
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@MessageLogger
public interface BookingLog {
  
    @Log(level = Level.INFO)
    @Message("%s selected the %s in %s.")
    void hotelSelected(String customerName, String hotelName, String city);

    @Log(level = Level.INFO)
    @Message("%s initiated a booking at the %s.")
    void bookingInitiated(String customerName, String hotelName);

    // QUESTION can positional parameters be used in message string?
    @Log(level = Level.INFO)
    @Message("New booking at the %s confirmed for %s.")
    void bookingConfirmed(String hotelName, String customerName);

    @Log(level = Level.INFO)
    @Message("Does the persistence context still contain the hotel instance? %s")
    void hotelEntityInPersistenceContext(boolean state);
    
}

