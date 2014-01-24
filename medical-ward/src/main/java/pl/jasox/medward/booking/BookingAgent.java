/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 */
package pl.jasox.medward.booking;

import java.util.Locale;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.EXTENDED;

import com.ocpsoft.pretty.time.PrettyTime;


import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.ConversationBoundaryInterceptor;
import org.jboss.seam.faces.context.conversation.End;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.TemplateMessage;
import org.jboss.solder.logging.TypedCategory;

import pl.jasox.medward.account.Authenticated;
import pl.jasox.medward.i18n.DefaultBundleKey;
import pl.jasox.medward.log.BookingLog;
import pl.jasox.medward.model.Booking;
import pl.jasox.medward.model.Hotel;
import pl.jasox.medward.model.User;


/**
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@Stateful
@ConversationScoped
@Named
@Interceptors(ConversationBoundaryInterceptor.class) // not necessary, this is a temporary 
public class BookingAgent {                          // workaround for GLASSFISH-17184
    
    @Inject
    @TypedCategory(BookingAgent.class)
    private BookingLog log;

    @PersistenceContext(unitName="booking", type = EXTENDED)
    private EntityManager em;

    @Inject
    private Instance<TemplateMessage> messageBuilder;

    @Inject
    private Messages messages;

    @Inject
    @Authenticated
    private User user;

    @Inject
    private Locale locale;

    @Inject
    @Confirmed
    private Event<Booking> bookingConfirmedEvent;

    private Hotel   hotelSelection;  // @Named : "hotel"

    private Booking booking;         // @Named : "booking"

    private boolean bookingValid;

    @Inject
    private Conversation conversation;
    
    // -------------------------------------------------------------------------

    /** Początek konwersacji - przy wejściu na stronę "hotel.xhtml" */
    @Begin  
    public void selectHotel(final Long id) {
        conversation.setTimeout(600000); //10 * 60 * 1000 (10 minutes)

        // NOTE: get a fresh reference that's managed by the extended persistence context
        hotelSelection = em.find(Hotel.class, id);
        if (hotelSelection != null) {
            log.hotelSelected(user != null ? user.getName() : "Anonymous", 
            		          hotelSelection.getName(), 
            		          hotelSelection.getCity());
        }
    }

    /** Utworzenie nowej rezerwacji dla wybranego hotelu i bieżącego użytkownika */
    public void bookHotel() {
        booking        = new Booking(hotelSelection, user, 7, 2);
        hotelSelection = null;

        // for demo convenience
        booking.setCreditCardNumber("1111222233334444");
        
        log.bookingInitiated(user.getName(), booking.getHotel().getName());
        messages.info(new DefaultBundleKey("booking_initiated"))
                .defaults("You've initiated a booking at the {0}.")
                .params(booking.getHotel().getName());
    }

    /** Sprawdzenie bieżącej rezerwacji */
    public void validate() {
        log.hotelEntityInPersistenceContext(em.contains(booking.getHotel()));
        // if we got here, all validations passed
        bookingValid = true;
    }

    /** Potwierdzenie bieżącej rezerwacji */
    @End   
    public void confirm() {
        em.persist(booking);
        bookingConfirmedEvent.fire(booking);
    }

    /** Odwołanie bieżącej rezerwacji */
    @End    
    public void cancel() {
        booking        = null;
        hotelSelection = null;
    }

    /** Po potwierdzeniu bieżącej rezerwacji */
    public void onBookingComplete( @Observes(during = TransactionPhase.AFTER_SUCCESS) 
                                   @Confirmed final Booking booking) {    	
        log.bookingConfirmed(booking.getHotel().getName(), 
        		             booking.getUser().getName());
        messages.info(new DefaultBundleKey("booking_confirmed"))
                .defaults("You're booked to stay at the {0} {1}.")
                .params(booking.getHotel().getName(),
                		new PrettyTime(locale).format(booking.getCheckinDate()));
    }

    /** @Produces "booking" - bieżąca rezerwacja */
    @Produces
    @ConversationScoped
    @Named              // @Named("booking")
    public Booking getBooking() {
        return booking;
    }

    /** @Produces "hotel" - wybrany na stronie 'search.xhtml' lub hotel bieżącej rezerwacji */
    @Produces
    @RequestScoped
    @Named("hotel")
    public Hotel getSelectedHotel() {
        return booking != null ? booking.getHotel() : hotelSelection;
    }

    public boolean isBookingValid() {
        return bookingValid;
    }
}
