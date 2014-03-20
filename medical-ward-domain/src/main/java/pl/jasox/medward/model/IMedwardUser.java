package pl.jasox.medward.model;

/**
 * <p> is the interface that represents a <i>user</i> of med-ward Application.</p> 
 * 
 * @author Janusz Swół
 */
public interface IMedwardUser extends org.picketlink.idm.api.User {

  public abstract String  getName();

  public abstract String  getUsername();

  public abstract void    setUsername(String username);

  public abstract String  getPassword();

  public abstract void    setPassword(String password);
  
  public abstract String  getEmail();

  public abstract void    setEmail(String email);
  
  public abstract boolean getLoggedIn();
  
  public abstract void    setLoggedIn(boolean loggedIn);

}
