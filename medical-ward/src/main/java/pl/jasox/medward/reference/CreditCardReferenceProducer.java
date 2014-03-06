/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors. 
 */
package pl.jasox.medward.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import pl.jasox.medward.model.CreditCardType;

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
