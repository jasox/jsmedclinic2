package pl.jasox.medward.services.loginservice;

import pl.jasox.medward.services.loginservice.exceptions.AccountLoginLimitReachedException;
import pl.jasox.medward.services.loginservice.exceptions.AccountNotFoundException;
import pl.jasox.medward.services.loginservice.exceptions.AccountRevokedException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *  By Brett L. Schuchert <br/>
 *  
    All types used or needed by the underling LoginService will be represented as Interfaces <br/>     
    All types used or needed by the underling LoginService will be created via Mockito <br/>
    I'm going to use Loose mocks - you can call anything you want and the underling object will not complain <br/>
    I'm going to minimally verify the expected resulting interactions (one assertion per test) <br/>   
*/ 
public class LoginServiceTest {   	
	
   static Logger log = Logger.getLogger( LoginServiceTest.class.getName() );
   
   // Mock's
   private IAccount           account;
   private IAccountRepository accountRepository;
   // Tested class
   private LoginService       service;  
   
   /** */
   @BeforeClass
   public static void setUp() {
	  log.setLevel(Level.WARN);
	  log.info("Init before class done..." );
   }	      
	  
   /** */
   @Before
   public void init() {	  
      account = mock(IAccount.class);
      when(account.getId()).thenReturn("brett");
      accountRepository = mock(IAccountRepository.class);      
      when(accountRepository.find(anyString())).thenReturn(account);      
      service = new LoginService(accountRepository);
      log.info("Init before test done...");
   }
 
   /** */
   private void willAccountPasswordMatch(boolean value) {
      when(account.passwordMatches(anyString())).thenReturn(value);
   }
   
   /** When a user logs in successfully with a valid account id and password, 
    *  the account's state is set to logged in. */
   @Test
   public void itShouldSetAccountToLoggedInWhenPasswordMatches() {
      willAccountPasswordMatch(true);
      service.login("brett", "password");
      verify(account, times(1)).setLoggedIn(true);
   }
 
   /** */
   @Test
   public void itShouldSetAccountToRevokedAfterThreeFailedLoginAttempts() {
      willAccountPasswordMatch(false); 
      for (int i = 0; i < 3; i++) {
         service.login("brett", "password");
      }
      verify(account, times(1)).setRevoked(true);
      verify(account, times(1)).setLoggedIn(false);
   } 
   
   /** */
   @Test
   public void itShouldNotSetAccountLoggedInIfPasswordDoesNotMatch() {
      willAccountPasswordMatch(false);
      service.login("brett", "password");
      verify(account, never()).setLoggedIn(true);
   }
   
   /** */
   @Test
   public void itShouldNotRevokeSecondAccountAfterTwoFailedAttemptsFirstAccount() {
      willAccountPasswordMatch(false);
 
      IAccount secondAccount = mock(IAccount.class);
      when(secondAccount.passwordMatches(anyString())).thenReturn(false);
      when(accountRepository.find("schuchert")).thenReturn(secondAccount);
 
      service.login("brett",     "password");
      service.login("brett",     "password");
      service.login("schuchert", "password");
 
      verify(secondAccount, never()).setRevoked(true);
   }
   
   /** */
   @Test(expected = AccountLoginLimitReachedException.class)
   public void itShouldNotAllowConcurrentLogins() {
      willAccountPasswordMatch(true);
      when(account.isLoggedIn()).thenReturn(true);
      service.login("brett", "password");
   }
   
   /** */
   @Test(expected = AccountNotFoundException.class)
   public void ItShouldThrowExceptionIfAccountNotFound() {
      when(accountRepository.find("schuchert")).thenReturn(null);
      service.login("schuchert", "password");
   }
   
   /** */
   @Test(expected = AccountRevokedException.class)
   public void ItShouldNotBePossibleToLogIntoRevokedAccount() {
      willAccountPasswordMatch(true);
      when(account.isRevoked()).thenReturn(true);
      service.login("brett", "password");
   }
   
   /** */
   @Test
   public void itShouldResetBackToInitialStateAfterSuccessfulLogin() {
      willAccountPasswordMatch(false);
      service.login("brett", "password");
      service.login("brett", "password");
      willAccountPasswordMatch(true);
      service.login("brett", "password");
      willAccountPasswordMatch(false);
      service.login("brett", "password");
      verify(account, never()).setRevoked(true);
   }
   
}

