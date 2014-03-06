/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package pl.jasox.medward.booking;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.seam.faces.validation.InputElement;
import org.jboss.seam.international.status.builder.BundleTemplateMessage;

import pl.jasox.medward.i18n.DefaultBundleKey;

/**
 * A cross-field validator that validates the start date is in the future and before the end date.
 *
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@FacesValidator("reservationDateRange")
public class ReservationDateRangeValidator implements Validator {
  
    @Inject
    private InputElement<Date> startDateElement;

    @Inject
    private InputElement<Date> endDateElement;

    @Inject
    private Instance<BundleTemplateMessage> messageBuilder;

    public void validate(final FacesContext ctx, final UIComponent form, final Object value) 
    throws ValidatorException {
        Date startDate = startDateElement.getValue();
        Date endDate   = endDateElement.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (startDate.before(calendar.getTime())) {
            String message = messageBuilder.get()
                    .key(new DefaultBundleKey("booking_checkInNotFutureDate"))
                    .targets(startDateElement.getClientId()).build().getText();
            throw new ValidatorException(new FacesMessage(message));
        } 
        else if (!startDate.before(endDate)) {
            String message = messageBuilder.get()
                    .key(new DefaultBundleKey("booking_checkOutBeforeCheckIn"))
                    .targets(endDateElement.getClientId()).build().getText();
            throw new ValidatorException(new FacesMessage(message));
        }
    }
}
