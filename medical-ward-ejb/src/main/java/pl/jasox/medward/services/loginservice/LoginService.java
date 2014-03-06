package pl.jasox.medward.services.loginservice;

import pl.jasox.medward.services.loginservice.exceptions.AccountNotFoundException;
import pl.jasox.medward.services.loginservice.states.AwaitingFirstLoginAttempt;

/**
 * By Brett L. Schuchert</br>
 * 
 * This example login service demonstrates : </br> 
 * Test Driven Development, Using the Mockito library </br>
   Refactoring to design patterns, Template Method Pattern, State Pattern </br>
   
   podstawowa funkcjonalność logowania do systemu - na razie nie wprowadzona do aplikacji
   aby uniknąć komplikacji przy obsłudze programu który jest tylko przykładem   
*/
public class LoginService extends LoginServiceContext {
  
   private final IAccountRepository accountRepository;
   
   public LoginService(IAccountRepository accountRepository ) {
    super( new AwaitingFirstLoginAttempt() ); 
      this.accountRepository = accountRepository;
   }   
 
   /** logowanie na podstawie identyfikatora i hasła </br>
    *  zwracana jest instancja :IAccount z odpowiednimi wartociami loggedIn i revoked </br>
    *   
    *   logika metody delegowana jest do obiektu stanu, a stany mamy na razie trzy : </br>
    *   AwaitingFirstLoginAttempt, 
    *   AfterFirstFailedLoginAttempt,
    *   AfterSecondFailedLoginAttempt */
   public IAccount login(String accountId, String password) {     
    IAccount account = accountRepository.find(accountId);
    if (account == null) {
     throw new AccountNotFoundException();
    }
      getState().login(this, account, password);      
      return account;
   }
   
   /** logowanie na podstawie istniejcej instancji :IAccount i hasła </br>
    *  zwracana jest wejściowa instancja :IAccount z odpowiednimi wartościami loggedIn lub revoked </br>
    *   logika metody delegowana jest do obiektu stanu */
   public IAccount login(IAccount account, String password) {      
    if (account == null) {
     throw new AccountNotFoundException();
    }
      getState().login(this, account, password);       
      return account;
   }  
}
