package pl.jasox.medward.model.dao;

/**
 * Interfejs definiujacy podstawowe operacje dla DAO
 */

public interface IGenericDao {

  public abstract Object findById(int id);

  public abstract void   save(Object object);

  public abstract void   saveOrUpdate(Object object);

  public abstract void   delete(Object object);

}
