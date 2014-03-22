/*
 The simplest way to realize business transactions is to map them to technical transactions. 
 A stateless session bean with container-managed transactions (CMTs) is perfectly suitable 
 for that purpose. 
 A Service Façade is therefore annotated with the TransactionAttributeType.REQUIRES_NEW class level. 
 This setting is inherited by all methods. REQUIRES_NEW always starts a new transaction and suspends 
 the existing one. Although not mandatory, this approach is more explicit. Even though a 
 Service Façade is the boundary between the presentation and business layers, it is impossible 
 to have an existing transaction. The presentation components invoke the Service Façade method, 
 which starts a transaction and propagates it to the services. Each interaction is modeled 
 as a Service Façade method. A Service Façade is defned as a boundary, so invocations between
 Service Façade methods are therefore excluded.
 */

package pl.jasox.medward.facade;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import pl.jasox.medward.log.Loggable;

/**
 *
 * @author Janusz Swół
 */
@Stateless
@Remote(IMedwardServiceFacadeRemote.class)
@Local(IMedwardServiceFacadeLocal.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Loggable
public class MedwardServiceFacade 
  implements IMedwardServiceFacadeRemote, IMedwardServiceFacadeLocal {
  
  @Override
  public void doSomethingRemotely() {      
  }
  
  @Override
  public void doSomethingLocally() {      
  }
  
}
