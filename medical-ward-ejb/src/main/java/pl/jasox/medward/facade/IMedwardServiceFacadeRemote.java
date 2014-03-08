/*
 The Service Façade methods are designed from the domain perspective and are therefore 
 coarse-grained. A relation to use cases, user stories, or some other kind of specifcation 
 should be identifable at frst glance. Best case scenario, the methods should be understandable 
 for domain experts and even end users.
 The Service Façade is the boundary between the presentation and business layers. 
 The methods of the façade are triggered by the end user. Every interaction between 
 the user and the system is a business transaction. The transaction is either completed
 in its entirety or not at all.
 */

package pl.jasox.medward.facade;

import javax.ejb.Remote;

/**
 *
 * @author Janusz Swół
 */
@Remote
public interface IMedwardServiceFacadeRemote {
  
  public abstract void doSomethingRemotely();
  
}
