package pl.jasox.medward.security;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.account.Authenticated;
import pl.jasox.medward.account.NotAuthenticated;
import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.IMedwardUserRepository;

/**
 * This implementation of a <strong>Authenticator</strong> .</br>
 * 
 * Authentication is the act of establishing, or confirming, the identity of a user. 
 * In many applications a user confirms their identity by providing a username and password 
 * (also known as their credentials).
 */
@Stateful
@Named("customAuthenticator")
public class MedwardAuthenticator {
  
  @Inject
  private FacesContext facesContext;
  
  @Inject
  @NotAuthenticated
  IMedwardUser newUser;

  @Inject
  @Authenticated
  private Event<IMedwardUser> loginEvent;  
  
  @Inject
  @ApplicationDatabase
  private IMedwardUserRepository userRepository;
  
  
  public void authenticate() {    
    if ( ( newUser.getUsername() == null) || ( newUser.getPassword() == null) ) {
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, 
              "Login failed!", "Invalid username or password.");
      facesContext.addMessage(null, m); 
      return;
    }    
    IMedwardUser user = userRepository.find( newUser.getUsername() );  // NOTE  12.11.2013
    if ( ( user != null ) && ( user.getPassword().equals( newUser.getPassword() ) ) ) {
      loginEvent.fire((IMedwardUser)user);
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, 
              "Login successful.", "You're signed in .");
      facesContext.addMessage(null, m);       
      return;
    }
    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, 
            "Login failed!", "Invalid username or password.");
    facesContext.addMessage(null, m);
  }
  
}
