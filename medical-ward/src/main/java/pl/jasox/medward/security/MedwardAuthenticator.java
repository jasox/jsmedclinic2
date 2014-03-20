package pl.jasox.medward.security;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.account.Authenticated;
import pl.jasox.medward.account.NotAuthenticated;
import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.IMedwardUserRepository;

/**
 * Authentication is the act of establishing, or confirming, the identity of a user. 
 * In many applications a user confirms their identity by providing a username and password 
 * (also known as their credentials).
 */
@Stateful
@Named("customAuthenticator")
public class MedwardAuthenticator {
  
  //@Inject  
  private FacesContext facesContext = FacesContext.getCurrentInstance();
  
  @Inject
  @NotAuthenticated
  IMedwardUser newUser;

  @Inject
  @Authenticated
  private Event<IMedwardUser> loginEvent;  
  
  @Inject
  @NotAuthenticated
  private Event<IMedwardUser> logoutEvent;
  
  @Inject
  @ApplicationDatabase
  private IMedwardUserRepository userRepository;
  
  // TODO - wprowadzić komunikaty z bundle do FacesMessage!
  @Inject
  private transient ResourceBundle bundle;  // messages from /i18n/messages
  
  
  public String authenticate() {
    String outcome = "failed";     
    // FIXME - dla ułatwienia testowania, logowanie jest na razie bez hasła
    if ( (newUser.getUsername() == null) ) { // || (newUser.getPassword() == null) ) {
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
              "Login failed!", "Invalid username or password.");
      facesContext.addMessage(null, m); 
      return outcome;
    }    
    IMedwardUser user = userRepository.find( newUser.getUsername() );  
    // FIXME - j.w.
    if (( user != null )) { // && (user.getPassword().equals(newUser.getPassword()))) {
      user.setLoggedIn(true);
      loginEvent.fire((IMedwardUser)user);
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, 
              "Login successful.", "You're signed in .");
      facesContext.addMessage(null, m);       
      return "success";
    }
    else {
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
              "Login failed!", "Invalid username or password.");
      facesContext.addMessage(null, m);
      return outcome;
    }    
  }
  
  public String login() {
    // FIXME login == authenticate ?
    return authenticate();
  }
  
  public void logout() throws IOException {
    // FIXME - redirect to home.xhtml in code ?
    logoutEvent.fire((IMedwardUser)newUser);
        
    ExternalContext ec = facesContext.getExternalContext();
    ec.invalidateSession();
    ec.redirect(ec.getRequestContextPath() + "/home.xhtml");    
  }
  
}
