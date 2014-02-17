package pl.jasox.medward.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.jasox.medward.model.dao.IDoctorDao;
import pl.jasox.medward.model.dao.factory.IDaoFactory;
import pl.jasox.medward.model.dao.hibernate.DoctorHibernateDao;
import pl.jasox.medward.model.dao.hibernate.factory.HibernateDaoFactory;

import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.model.util.hibernate.HibernateUtil;

//import org.jboss.resteasy.annotations.providers.jaxb.Formatted;

/**
 * JAX-RS Service <br/>
 * This class produces a RESTful service to read/write the contents of the doctors table.
 */
//@Singleton
@Path("/doctors")
public class DoctorResourceRESTService {      
    final static Logger log = Logger.getLogger( DoctorResourceRESTService.class.getName() );
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
     
    private static IDaoFactory   daoFactory; // = HibernateDaoFactory.getInstance();   
    private IDoctorDao    doctorRepository;
    private AtomicInteger idCounter = new AtomicInteger();
    
    protected static ValidatorFactory vf;
    protected static Validator validator;   

    //@Inject
    //private Logger log;

    //@Inject
    //private Validator validator;

    //@Inject
    //private DoctorRepository repository;

    //@Inject    
    //DoctorRegistration registration;
    
    @PostConstruct
    public void init() { 
      daoFactory       = HibernateDaoFactory.getInstance();
      System.out.println("############# DAO Factory :" + daoFactory);
      doctorRepository = daoFactory.getDoctorDao();
      //doctorRepository = new DoctorHibernateDao();
      System.out.println("############# Doctor DAO  :" + doctorRepository);
      Doctor doctor = doctorRepository.findById("0000001");
      System.out.println("############# in init, doctor : " + doctor);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML) // "application/xml")
    public Response createDoctorXml(Doctor doctor) {
      doctor.setIdDoctor(idCounter.incrementAndGet());
      //doctorRepository = daoFactory.getDoctorDao();      
      doctorRepository.saveOrUpdate(doctor);
      System.out.println("Created doctor : " + doctor.getId());
      return Response.created(URI.create("/doctors/" + doctor.getIdDoctor())).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML) // "application/xml")
    //@Formatted
    public Doctor getDoctor(@PathParam("id") String id) {
      beginHibernateTransaction();
      System.out.println("@GET @Path(\"{id}\"), doctor id : " + id);
      //doctorRepository = daoFactory.getDoctorDao();
      Doctor doctor = doctorRepository.findById(id);
      System.out.println("in @GET, doctor : " + doctor);
      //Doctor doctor = new Doctor("0000001", "John", "Smith", "j.smith@gmail.com");
      if (doctor == null) {
         throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
      endHibernateTransaction();
      return doctor;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML) // "application/xml")
    //@Formatted
    public void updateCustomer(@PathParam("id") String id, Doctor update)
    {
      Doctor current = doctorRepository.findById(id);
      if (current == null) { 
        throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
      current.setFirstName(update.getFirstName());
      current.setLastName(update.getLastName());
      current.setEmailAddress(update.getEmailAddress());
      current.setSymbolDoctor(update.getSymbolDoctor());
      current.setSymbolSpec(update.getSymbolSpec());      
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Formatted
    public List<Doctor> listAllDoctors() {
        return findAllDoctorsOrderedByName();
    }

    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor lookupDoctorById(@PathParam("id") String id) {
        Doctor doctor = doctorRepository.findById(id);
        if (doctor == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return doctor;
    }
    
    /**
     * Creates a new doctor from the values provided. <br/>
     * Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors. <br/>
     * @param doctor   
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctor) {

        Response.ResponseBuilder builder = null;

        try {
            // Validates doctor using bean validation
            validateDoctor(doctor);

            doctorRepository.saveOrUpdate(doctor);
            //registration.register(doctor);

            // Create an "ok" response
            builder = Response.ok();
        } 
        catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } 
        catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("email", "Email taken");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } 
        catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    /**    
     * Validates the given Doctor variable and throws validation exceptions based on the type of error. <br/>
     * If the error is standard bean validation errors then it will throw a ConstraintValidationException
     * with the set of the constraints violated. <br/>    
     * If the error is caused because an existing doctor with the same email is registered it throws
     * a regular validation exception so that it can be interpreted separately. <br/>
     * 
     * @param doctor Doctor to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     * @throws ValidationException If doctor with the same email already exists
     */
    private void validateDoctor(Doctor doctor) throws ConstraintViolationException, ValidationException {
        // Create a bean validator and check for issues.
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
       
        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        // Check the uniqueness of the email address
        if (emailAlreadyExists(doctor.getEmailAddress())) {
            throw new ValidationException("Unique Email Violation");
        }
       /**/
    }

    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, 
     * and their message. <br/> This can then be used by clients to show violations.
     * 
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.info("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<String, String>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    /**
     * Checks if a doctor with the same email address is already registered. <br/>
     * This is the only way to easily capture the
     * "@UniqueConstraint(columnNames = "email")" constraint from the Doctor class.
     * 
     * @param email The email to check
     * @return True if the email already exists, and false otherwise
     */
    public boolean emailAlreadyExists(String email) {
        Doctor doctor = null;
        
        try {
            doctor = doctorRepository.findByEmail(email);
        } 
        catch (NoResultException e) {
            // ignore
        }
        
        return (doctor != null) && ( email != null );
    }
    
    public List<Doctor> findAllDoctorsOrderedByName() {
        List<Doctor> doctors = null;
        
        try {
            doctors = doctorRepository.getAll();
        } 
        catch (NoResultException e) {
            // ignore
        }
        
        return doctors;
    }
    
   
    protected void beginHibernateTransaction() {	
    
      Session session = sessionFactory.getCurrentSession();     
      try {
        session.beginTransaction();      
      }
      catch ( Exception e ) {
        try {
          if ( session.getTransaction().isActive() ) {
            session.getTransaction().rollback();
          }
        } 
        catch ( HibernateException eh ) {
          log.error("Could not rollback after exception!", eh);
          eh.printStackTrace();
        }
      }    
    }
  
 
    protected void endHibernateTransaction() {

      if ( daoFactory != null ) {
        Session session = sessionFactory.getCurrentSession();   
        try {
          session.getTransaction().commit();
        }
        catch ( Exception e ) {
          try {
            if ( session.getTransaction().isActive() ) {
              session.getTransaction().rollback();
            }
          } 
          catch ( HibernateException eh ) {
            log.error("Could not commit after exception!", eh);
            eh.printStackTrace();
          }
        }      
      }     
    }
}
