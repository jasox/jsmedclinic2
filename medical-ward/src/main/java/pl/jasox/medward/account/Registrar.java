package pl.jasox.medward.account;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.jasox.medward.controllers.AController;

import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.IMedwardUserRepository;

/**
 * The view controller for registering a new user
 */
//@Stateful
@Model
public class Registrar extends AController implements Serializable {

  @Inject 
  @ApplicationDatabase 
  private IMedwardUserRepository userRepository;  

  @Inject  
  private FacesContext facesContext; // = FacesContext.getCurrentInstance();
  
  @Inject
  @NotAuthenticated
  private IMedwardUser newUser; 
  
  // ---------------------------------------------------------------------------

  private UIInput usernameInput;  

  @NotNull
  @Size(min = 5, max = 15)
  private String  confirmPassword;

  private boolean registered;

  private boolean registrationInvalid;
  
  // ---------------------------------------------------------------------------  
  
  public void register() {
    if (verifyUsernameIsAvailable()) {
      registered = true;
      userRepository.store(newUser);
      FacesMessage m = 
          new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
      facesContext.addMessage(null, m);      
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
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, 
              "Invalid registration!", "Please correct the errors and try again.");
      facesContext.addMessage(null, m);     
    }
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
    IMedwardUser existing = userRepository.find(newUser.getUsername());
    if (existing != null) {
      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, 
              "Invalid registration!", 
              "The username is already taken. Please choose another username");      
      return false;
    }
    return true;
  }

}
