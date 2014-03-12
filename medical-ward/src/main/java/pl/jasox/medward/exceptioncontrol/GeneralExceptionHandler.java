package pl.jasox.medward.exceptioncontrol;

import org.jboss.solder.logging.Logger;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

/**
 * Logs all exceptions and allows them to propagate
 *
 * @author <a href="http://community.jboss.org/people/spinner">Jose Freitas</a>
 */
@HandlesExceptions
public class GeneralExceptionHandler {

    public void printExceptionMessage(
      @Handles CaughtException<Throwable> event, Logger log) {
        log.info("Exception logged by seam-catch catcher: " + 
                 event.getException().getMessage());
        event.rethrow();
    }
}
