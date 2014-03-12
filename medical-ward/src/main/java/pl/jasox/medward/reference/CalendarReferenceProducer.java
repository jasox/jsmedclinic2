package pl.jasox.medward.reference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Produces calendar-oriented reference data to be used in user-interface forms. 
 * The user's locale is honored when producing name-based data.
 *
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
public class CalendarReferenceProducer {

    @Produces
    @Named
    @ConversationScoped
    public List<Month> getMonths(Locale locale) {
        List<Month> months = new ArrayList<Month>(12);
        DateFormat longNameFormat  = new SimpleDateFormat("MMMM", locale);
        DateFormat shortNameFormat = new SimpleDateFormat("MMM", locale);
        Calendar cal = Calendar.getInstance();
        for (int m = 0; m < 12; m++) {
            cal.set(Calendar.MONTH, m);
            months.add(
              new Month(m, 
                        longNameFormat.format(cal.getTime()), 
                        shortNameFormat.format(cal.getTime())));
        }

        return months;
    }

}
