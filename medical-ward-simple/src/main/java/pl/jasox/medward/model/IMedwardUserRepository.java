package pl.jasox.medward.model;

/**
 * is the interface that represents a <i>user repository</i> of med-ward Application.
 * 
 * @author Janusz Swół *
 */
public interface IMedwardUserRepository {
	
	/** Poszukiwanie konta użytkownika według identyfikatora ( loginu ) 
	 *  @returns IMedwardUser 
	 */
	public abstract IMedwardUser find(String Id);
  
  public abstract void         save(IMedwardUser user);

	public abstract void         saveOrUpdate(IMedwardUser user);

	public abstract void         delete(IMedwardUser user);

}
