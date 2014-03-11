/*
 The façade’s remote interface is exposed to external clients, so it has to remain stable 
 during the lifecycle. On the other hand, not all methods need to be exposed remotely; 
 some of them are only dedicated for internal use such as workfow engines, web applications, 
 or message-driven beans.
 The above requirement could be solved easily in traditional object-oriented programming. 
 A class could expose the public methods to external clients as a formalized contract 
 and dedicate the package private or protected methods to its friends. 
 You can approach this requirement similarly with a Service Façade. 
 Its remote business view could be exposed to the external clients with high stability 
 requirements, whereas the local view could be extended on demand in a more pragmatic manner. 
 The local business interface extends the remote interface, which makes maintenance easy.
 It is enough to declare the additional functionality in the local interface—all methods 
 will be inherited from the remote method.
 */

package pl.jasox.medward.facade;

import javax.ejb.Local;

/**
 *
 * @author Janusz Swół
 */
@Local
public interface IMedwardServiceFacadeLocal extends IMedwardServiceFacadeRemote {
  
  public abstract void doSomethingLocally();
  
}
