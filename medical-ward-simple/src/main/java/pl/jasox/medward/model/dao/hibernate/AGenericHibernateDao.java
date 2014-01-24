package pl.jasox.medward.model.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.jasox.medward.model.dao.IGenericDao;
import pl.jasox.medward.model.util.hibernate.HibernateUtil;

/**
 * Implementacja interfejsu definiujacego podstawowe operacje dla DAO 
 */
public abstract class AGenericHibernateDao implements IGenericDao {
	
	static Logger log = Logger.getLogger( AGenericHibernateDao.class.getName() );
	
	@SuppressWarnings("rawtypes")
	protected Class   persistedClass;
	
	protected Session session;
	

	@SuppressWarnings("rawtypes")
	public AGenericHibernateDao(Class persistedClass) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		this.session           = factory.getCurrentSession();
		this.persistedClass    = persistedClass;		
	}	
	
	public Object findById(int id) {
		Object object = (Object) session.get(persistedClass, id);
		return object;
	}

	public void save(Object object) {
		session.save(object);
	}

	public void saveOrUpdate(Object object) {
		session.saveOrUpdate(object);
	}

	public void delete(Object object) {
		session.delete(object);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		session.getTransaction().begin();
		Criteria criteria = session.createCriteria(persistedClass);
		//...
		return criteria.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
