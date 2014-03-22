package pl.jasox.medward.account;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import pl.jasox.medward.controllers.AController;
import pl.jasox.medward.model.IMedwardUser;

/**
 * Exposes the currently logged in user
 */
@Stateful
@SessionScoped
public class CurrentUserManager extends AController implements Serializable {
  
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
