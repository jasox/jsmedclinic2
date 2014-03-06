/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * 
 */
package pl.jasox.medward.account;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.international.status.Messages;
//import org.jboss.seam.international.status.builder.BundleKey; 

import pl.jasox.medward.i18n.DefaultBundleKey;
import pl.jasox.medward.model.User;

/**
 * The view controller for registering a new user
 *
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@Stateful
@Model
public class Registrar {
	
    @PersistenceContext(unitName="booking")
    private EntityManager em;

    @Inject
    private Messages messages;

    @Inject
    private FacesContext facesContext;

    private UIInput usernameInput;

    private final User newUser = new User();

    @NotNull
    @Size(min = 5, max = 15)
    private String confirmPassword;

    private boolean registered;

    private boolean registrationInvalid;

    public void register() {
      if (verifyUsernameIsAvailable()) {
          registered = true;
          em.persist(newUser);
          messages.info(new DefaultBundleKey("registration_registered"))
                  .defaults("You have been successfully registered as the user {0}! " +
                  		    "You can now login.")
                  .params(newUser.getUsername());
      } 
      else {
          registrationInvalid = true;
      }
    }

    public boolean isRegistrationInvalid() {
        return registrationInvalid;
    }

    /**
     * This method just shows another approach to adding a status message.
     * <p>
     * Invoked by:
     * </p>
     * <pre>
     * &lt; f:event type="preRenderView"
     *           listener="#{registrar.notifyIfRegistrationIsInvalid}" /&gt;
     * </pre>
     */
    public void notifyIfRegistrationIsInvalid() {
        if (facesContext.isValidationFailed() || registrationInvalid) {
            messages.warn( new DefaultBundleKey("registration_invalid"))
                    .defaults( "Invalid registration. Please correct the errors " +
                   		       "and try again.");
        }
    }

    @Produces
    @Named
    public User getNewUser() {
        return newUser;
    }

    public boolean isRegistered() {
        return registered;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(final String password) {
        confirmPassword = password;
    }

    public UIInput getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(final UIInput usernameInput) {
        this.usernameInput = usernameInput;
    }

    private boolean verifyUsernameIsAvailable() {
        User existing = em.find(User.class, newUser.getUsername());
        if (existing != null) {
            messages.warn(new DefaultBundleKey("account_usernameTaken"))
                    .defaults("The username '{0}' is already taken. " +
                	    	  "Please choose another username.")
                    .targets(usernameInput.getClientId())
                    .params(newUser.getUsername());
            return false;
        }

        return true;
    }

}
