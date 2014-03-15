package pl.jasox.medward.account;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.domainobject.Doctor;

/**
 * Produces the new user
 */
@Stateful
@SessionScoped
public class NewUserProducer {
  
  private IMedwardUser newUser;

  @Produces
  @Named("newUser")
  @NotAuthenticated
  public IMedwardUser getNewUser() {
    return newUser;
  }
  
  @PostConstruct
  private void init() {
    newUser = new Doctor(); // FIXME - Zakładamy wstępnie, że użytkownicy to lekarze
  }
  
}
