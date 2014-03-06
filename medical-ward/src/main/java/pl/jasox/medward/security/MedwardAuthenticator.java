/*
 */
package pl.jasox.medward.security;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;

import org.jboss.solder.logging.Logger;

import org.picketlink.idm.impl.api.PasswordCredential;
//import org.picketlink.idm.impl.api.model.SimpleUser;

import pl.jasox.medward.account.Authenticated;
import pl.jasox.medward.i18n.DefaultBundleKey;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.User;
import pl.jasox.medward.model.dao.ejb.UserEjbDao;

/**
 * This implementation of a <strong>Authenticator</strong> that uses Seam security.</br>
 * 
 * Authentication is the act of establishing, or confirming, the identity of a user. 
 * In many applications a user confirms their identity by providing a username and password 
 * (also known as their credentials). Seam Security allows the developer to control how users 
 * are authenticated, by providing a flexible Authentication API that can be easily configured 
 * to allow authentication against any number of sources, including but not limited to databases, 
 * LDAP directory servers or some other external authentication service.
 */
@Stateful
@Named("customAuthenticator")
public class MedwardAuthenticator extends BaseAuthenticator implements Authenticator {

  @Inject
  private Logger log;

  @PersistenceContext(unitName="booking")
  private EntityManager em;

  @Inject
  private Credentials credentials;

  @Inject
  private Messages messages;

  @Inject
  @Authenticated
  private Event<User> loginEvent;  
  
  @Inject
  private UserEjbDao userEjbDao;
  
  
  public void authenticate() {
    log.info("Logging in " + credentials.getUsername());
    if ( (credentials.getUsername()   == null) || 
         (credentials.getCredential() == null) ) {
      messages.error(new DefaultBundleKey("identity_loginFailed"))
              .defaults("Invalid username or password");
      this.setStatus(AuthenticationStatus.FAILURE);
    }
    //User user = em.find( User.class, credentials.getUsername() );
    IMedwardUser user = userEjbDao.find( credentials.getUsername() );  // NOTE  12.11.2013
    if ( ( user != null ) && ( credentials.getCredential() instanceof PasswordCredential ) && 
         user.getPassword().equals(((PasswordCredential)credentials.getCredential()).getValue()))        
     {
        loginEvent.fire((User) user);
        messages.info(new DefaultBundleKey("identity_loggedIn"), user.getName())
                .defaults("You're signed in as {0}")
                .params(user.getName());
        this.setStatus(AuthenticationStatus.SUCCESS);
        // TODO confirm the need for this set method
        // org.picketlink.idm.impl.api.model.SimpleUser
        // this.setUser(new SimpleUser(user.getUsername())); 
        this.setUser(user);  // NOTE  user implementuje org.picketlink.idm.api.User  8.11.2013 
        return;
    }
    messages.error(new DefaultBundleKey("identity_loginFailed")).defaults("Invalid username or password");
    setStatus(AuthenticationStatus.FAILURE);
  }

}
