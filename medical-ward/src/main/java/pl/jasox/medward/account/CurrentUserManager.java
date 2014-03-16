package pl.jasox.medward.account;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import pl.jasox.medward.model.IMedwardUser;

/**
 * Exposes the currently logged in user
 */
@Stateful
@SessionScoped
public class CurrentUserManager {
  
  private IMedwardUser currentUser;

  @Produces
  @Authenticated
  @Named("currentUser")
  public IMedwardUser getCurrentUser() {
    return currentUser;
  }

  public void onLogin(@Observes @Authenticated IMedwardUser user) {
    currentUser = user;
  }
  
}
