package pl.jasox.medward.model;

/**
 * is the interface that represents a <i>user repository</i> of med-ward Application.
 * 
 * @author Janusz Swół *
 */
public interface IMedwardUserRepository {
	
	/** Poszukiwanie konta użytkownika według identyfikatora ( loginu )  */
	public abstract IMedwardUser find(String Id);
  
  /** Składowanie konta użytkownika w repozytorium */
  public abstract void         store(IMedwardUser user);
  
  /** Usuwanie konta użytkownika z repozytorium    */
	public abstract void         remove(IMedwardUser user);

}
