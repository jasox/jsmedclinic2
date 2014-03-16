package pl.jasox.medward.account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.IMedwardUserRepository;

/**
 * The view controller for changing the user password
 */
//@Stateful
@Model
public class PasswordManager {
  
  //@Inject  
  private FacesContext facesContext = FacesContext.getCurrentInstance();  
      
  @Inject 
  @ApplicationDatabase 
  private IMedwardUserRepository userRepository;

  @Inject
  @Authenticated
  private IMedwardUser user;

  @NotNull
  @Size(min = 5, max = 15)
  private String confirmPassword;

  private boolean changed;

// -----------------------------------------------------------------------------

  public void changePassword() {
    userRepository.store(user);
    FacesMessage m = 
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Changed!", "Password successfully updated.");
    facesContext.addMessage(null, m);    
    this.setChanged( true );
  }
  
  public void setChanged(boolean changed) {
    this.changed = changed;
  }
    
  public boolean isChanged() {
    return changed;
  }

  public void setConfirmPassword(final String password) {
    confirmPassword = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

}
