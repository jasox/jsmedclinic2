package pl.jasox.medward.test;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import pl.jasox.medward.account.Authenticated;
import pl.jasox.medward.model.User;

public class AuthenticatedUserProducer {
  
    @PersistenceContext(unitName="booking")
    EntityManager em;

    @Produces
    @Authenticated
    public User getRegisteredUser() {
        return em.find(User.class, "ike");
    }
    
}
