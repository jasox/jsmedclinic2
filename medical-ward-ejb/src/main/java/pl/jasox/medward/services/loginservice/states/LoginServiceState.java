package pl.jasox.medward.services.loginservice.states;

import pl.jasox.medward.services.loginservice.IAccount;
import pl.jasox.medward.services.loginservice.LoginServiceContext;
import pl.jasox.medward.services.loginservice.exceptions.AccountLoginLimitReachedException;
import pl.jasox.medward.services.loginservice.exceptions.AccountRevokedException;

public abstract class LoginServiceState {
	
	protected int    failedAttempts;
	protected String previousAccountId = "";

	public void login(LoginServiceContext context, IAccount account, String password) {
		if (account.passwordMatches(password)) {
           if (account.isLoggedIn()) {
              throw new AccountLoginLimitReachedException();
           }   
           if (account.isRevoked()) {
              throw new AccountRevokedException();
           }   
           account.setLoggedIn(true);
           context.setState(new AwaitingFirstLoginAttempt());
	    } 
		else {
	       handleIncorrectPassword(context, account, password);
		}
	}

	public abstract void handleIncorrectPassword( 
			LoginServiceContext context, IAccount account, String password);

}
