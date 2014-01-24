/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors by the @authors tag. 
 * See the copyright.txt in the distribution for a full listing of individual contributors.
 */
package pl.jasox.medward.inventory;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jboss.seam.international.status.builder.TemplateMessage;
import org.jboss.solder.logging.Logger;

import pl.jasox.medward.model.Hotel;
import pl.jasox.medward.model.Hotel_;

/**
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@Named
@Stateful
@SessionScoped
public class HotelSearch {

    @Inject
    private Logger log;

    @PersistenceContext(unitName="booking")
    private EntityManager em;

    @Inject
    private SearchCriteria criteria;

    @Inject
    private Instance<TemplateMessage> messageBuilder;

    private boolean nextPageAvailable = false;

    private List<Hotel> hotels = new ArrayList<Hotel>();

    public void find() {
        criteria.firstPage();
        queryHotels(criteria);
    }

    public void nextPage() {
        criteria.nextPage();
        queryHotels(criteria);
    }

    public void previousPage() {
        criteria.previousPage();
        queryHotels(criteria);
    }

    @Produces
    @Named
    public List<Hotel> getHotels() {
        return hotels;
    }

    public boolean isNextPageAvailable() {
        return nextPageAvailable;
    }

    public boolean isPreviousPageAvailable() {
        return criteria.getPage() > 0;
    }

    private void queryHotels(final SearchCriteria criteria) {
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<Hotel> cquery = builder.createQuery(Hotel.class);
      Root<Hotel> hotel = cquery.from(Hotel.class);
      // QUESTION can like create the pattern for us?
      cquery.select(hotel).where(
         builder.or(
           builder.like( builder.lower(hotel.get(Hotel_.name)), criteria.getSearchPattern()),
           builder.like( builder.lower(hotel.get(Hotel_.city)), criteria.getSearchPattern()),
           builder.like( builder.lower(hotel.get(Hotel_.zip)),  criteria.getSearchPattern()),
           builder.like( builder.lower(hotel.get(Hotel_.address)), criteria.getSearchPattern())));

      List<Hotel> results = 
     		 em.createQuery(cquery)
           .setMaxResults(criteria.getFetchSize())
           .setFirstResult(criteria.getFetchOffset()).getResultList();

      nextPageAvailable = results.size() > criteria.getPageSize();
      if (nextPageAvailable) {
          // NOTE create new ArrayList since subList creates unserializable list
          hotels = new ArrayList<Hotel>(results.subList(0, criteria.getPageSize()));
      } 
      else {
          hotels = results;
      }
      log.info( messageBuilder.get()
               .text("Found {0} hotel(s) matching search term [ {1} ] (limit {2})")
               .textParams( hotels.size(), criteria.getQuery(), criteria.getPageSize())
               .build().getText() );
    }
}
