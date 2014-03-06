/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors. 
 */
package pl.jasox.medward.booking;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.solder.logging.Logger;

import pl.jasox.medward.model.Booking;

//@MessageDriven(activationConfig = {
//      @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/BookingTopic"),
//      @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
//})
public class ReservationNotifier implements MessageListener {
  
    @Inject
    private Logger log;

    public void onMessage(Message message) {
        try {
            Booking booking = (Booking) ((ObjectMessage) message).getObject();
            log.info("In a real-world application, send e-mail containing reservation information to "
                    + booking.getUser().getEmailWithName());
        } 
        catch (JMSException ex) {
            log.error("Error reading booking from topic");
        }
    }

}
