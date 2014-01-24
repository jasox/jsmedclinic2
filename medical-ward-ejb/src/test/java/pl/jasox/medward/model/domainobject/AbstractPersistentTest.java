package pl.jasox.medward.model.domainobject;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author Janusz Swół 
 * @credit Antonio Goncalves
 *           APress Book - Beginning Java EE 6 with Glassfish 3
 *           http://www.apress.com/
 *           http://www.antoniogoncalves.org
 *           --
 */
public abstract class AbstractPersistentTest {

  // ======================================
  // =             Attributes             =
  // ======================================

  protected static EntityManagerFactory emf = 
                   Persistence.createEntityManagerFactory("medward_SE");
  protected EntityManager     em;
  protected EntityTransaction tx;

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @Before
  public void initEntityManager() throws Exception {
    em = emf.createEntityManager();
    tx = em.getTransaction();
  }

  @After
  public void closeEntityManager() throws SQLException {
    if (em != null) {
      em.close();
    }
  }

  protected Long getRandomId() {
    return Math.abs(new Random().nextLong());
  }
}
