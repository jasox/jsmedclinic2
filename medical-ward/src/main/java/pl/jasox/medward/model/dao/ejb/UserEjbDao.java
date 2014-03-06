package pl.jasox.medward.model.dao.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.solder.logging.Logger;

import pl.jasox.medward.model.dao.IUserDao;
import pl.jasox.medward.model.IMedwardUser;
import pl.jasox.medward.model.User;

@SessionScoped
@Named("userEjbDao")
public class UserEjbDao implements IUserDao, Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private Logger log;

  @PersistenceContext(unitName="booking")
  private EntityManager em;
  
  
  /** */  
  public UserEjbDao() {    
  } 
  
  /*
  @Override
  public void delete(User user) {
    // TODO Auto-generated method stub
    
  }
  */

  /*
  @SuppressWarnings("unchecked")
  @Override
  public List<User> getAll() {    
    List<User> users = null;
      
    return users;
  }
  */
  
  @Override
  public IMedwardUser find(String id) {
    // 
    String username = id;
    User user = em.find( User.class, username );
    return user;
  }
  

}
