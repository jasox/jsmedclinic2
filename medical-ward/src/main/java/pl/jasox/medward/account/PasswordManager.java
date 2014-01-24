/*
 */
package pl.jasox.medward.account;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.international.status.Messages;

import pl.jasox.medward.i18n.DefaultBundleKey;
import pl.jasox.medward.model.User;

/**
 * The view controller for changing the user password
 *
 * @author Janusz Swół
 */
@Stateful
@Model
public class PasswordManager {
	
    @PersistenceContext(unitName="booking")
    private EntityManager em;

    @Inject
    private Messages messages;

    @Inject
    @Authenticated
    private User user;

    @NotNull
    @Size(min = 5, max = 15)
    private String confirmPassword;

    private boolean changed;
    

	public void changePassword() {
        em.merge(user);
        messages.info(
          new DefaultBundleKey("account_passwordChanged")).defaults("Password successfully updated.");
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
