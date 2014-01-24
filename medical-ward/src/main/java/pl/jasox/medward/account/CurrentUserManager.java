/*
 */
package pl.jasox.medward.account;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import pl.jasox.medward.model.User;

/**
 * Exposes the currently logged in user
 */
@Stateful
@SessionScoped
public class CurrentUserManager {
	
    private User currentUser;

    @Produces
    @Authenticated
    @Named("currentUser")
    public User getCurrentAccount() {
        return currentUser;
    }

    // Injecting HttpServletRequest instead of HttpSession as the latter conflicts
    // with a Weld bean on GlassFish 3.0.1
    public void onLogin(@Observes @Authenticated User user, HttpServletRequest request) {
        currentUser = user;
        // reward authenticated users with a longer session
        // default is kept short to prevent search engines from driving up # of sessions
        request.getSession().setMaxInactiveInterval(3600);
    }
}
