package pl.jasox.medward.account;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import pl.jasox.medward.db.ApplicationDatabase;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.IMedwardUserRepository;

/**
 * Produces the new user
 */
@Stateless
public class NewUserProducer {
  
  @Inject
  @ApplicationDatabase
  private IMedwardUserRepository userRepository;
  
  private IMedwardUser newUser;

  @Produces
  @Named("newUser")
  @NotAuthenticated
  public IMedwardUser getNewUser() {
    return newUser;
  }
  
  @PostConstruct
  private void init() {
    // FIXME newUser producer
    // Zakładamy wstępnie, że użytkownicy to lekarze;
    // do logowania, dla ułatwienia testowania wybieramy pierwszego z listy. 
    newUser = userRepository.find("0000001");
    System.out.print(newUser.getEmail());
    System.out.print(newUser.getPassword());
  }
  
}
