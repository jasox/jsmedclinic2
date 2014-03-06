package pl.jasox.medward.services.loginservice;

public interface IAccount {
  
  void    setLoggedIn(boolean value);
  boolean isLoggedIn();
  
  void    setRevoked(boolean value);
  boolean isRevoked();  
  
  String  getId();
  
  boolean passwordMatches(String candidate);  

}
