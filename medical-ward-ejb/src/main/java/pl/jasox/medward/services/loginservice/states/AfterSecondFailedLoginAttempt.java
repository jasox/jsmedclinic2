package pl.jasox.medward.services.loginservice.states;

import pl.jasox.medward.services.loginservice.IAccount;
import pl.jasox.medward.services.loginservice.LoginServiceContext;

public class AfterSecondFailedLoginAttempt extends LoginServiceState {

  public AfterSecondFailedLoginAttempt(String previousAccountId) {
      this.previousAccountId = previousAccountId;
      this.failedAttempts    = 2;    
  }

  @Override
  public void handleIncorrectPassword(LoginServiceContext context, IAccount account, String password) {
    if (previousAccountId.equals(account.getId())) {
            account.setRevoked(true);
            account.setLoggedIn(false);
            context.setState(new AwaitingFirstLoginAttempt());
         } 
         else {
            context.setState(new AfterFirstFailedLoginAttempt(account.getId()));
         }    
  }

}
