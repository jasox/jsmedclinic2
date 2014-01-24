package pl.jasox.medward.model.dao;

import pl.jasox.medward.model.dao.factory.ADaoFactory;

/**
 * A base class for all DAO for tests.
 */
public abstract class ADaoTest {

  // the daoFactory to be tested.
  protected static ADaoFactory daoFactory;  
  
  /**   */
  public static void setDaoFactory(ADaoFactory _daoFactory) {
    daoFactory = _daoFactory;
  }
    
  /**
   * @return the daoFactory
   */
  public static ADaoFactory getDaoFactory() {
    if ( daoFactory == null ) {
       daoFactory = daoFactory.getInstance();
    }
    return daoFactory;
  }

  /**
   * Setting up the current test
   * 
   * @throws Exception
   */
  protected abstract void onSetUpInTransaction() throws Exception;


  /**
   * Called sometimes just before the assertions in the test.  </br>
   * This callback method can be used for example to flush memory state to the database  </br>
   *  (in Hibernate or JDO for example).
   */
  protected abstract void beforeAssertions();
  
  protected abstract void afterAssertions();

    
  
  
}
