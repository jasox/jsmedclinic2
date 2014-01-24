package pl.jasox.medward.model.dao.ejb;

import java.util.List; 

//import org.apache.log4j.Logger;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import pl.jasox.medward.model.dao.IGenericDao;

/**
 * Implementacja interfejsu definiujacego podstawowe operacje dla EJB DAO 
 */
public abstract class AGenericEjbDao implements IGenericDao {
	
	//static Logger log = Logger.getLogger( AGenericEjbDao.class.getName() );
	
	@SuppressWarnings("rawtypes")
	protected Class   persistedClass;
	
	
	public Object findById(int id) {
		Object object = null; //(Object) session.get(persistedClass, id);
		return object;
	}

	public void save(Object object) {
		//session.save(object);
	}

	public void saveOrUpdate(Object object) {
		//session.saveOrUpdate(object);
	}

	public void delete(Object object) {
		//session.delete(object);
	}
  /*
	@SuppressWarnings("rawtypes")
	public List findAll() {
		session.getTransaction().begin();
		Criteria criteria = session.createCriteria(persistedClass);
		//...
		return criteria.list();
	}
  */
	//public Session getSession() {
		//return session;
	//}

	//public void setSession(Session session) {
		//this.session = session;
	//}

}
