package pl.jasox.medward.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;


/**
 * A bean that produces credit card reference data for user-interface forms.
 *
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
public class CreditCardReferenceProducer {

    @Produces
    @Named
    @ConversationScoped
    public List<CreditCardType> getCreditCardTypes() {
        return new ArrayList<CreditCardType>(Arrays.asList(CreditCardType.values()));
    }

    @Produces
    @Named
    @ConversationScoped
    @CreditCardExpiryYears
    public List<Integer> getCreditCardExpiryYears() {
        List<Integer> years = new ArrayList<Integer>(8);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 8; i++) {
            years.add(currentYear + i);
        }
        return years;
    }
}
