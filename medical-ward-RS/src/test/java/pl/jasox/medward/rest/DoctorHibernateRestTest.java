package pl.jasox.medward.rest;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;

import org.apache.log4j.Logger;
import org.junit.After;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;

import pl.jasox.medward.model.domainobject.Doctor;

/**
 * @author <a href="mailto:janusz.swol@gmail.com">Janusz Swół</a>
 * @version 1.1.1
 */
//@Ignore
public class DoctorHibernateRestTest {       
  
    // Attributes --------------------------------------------------------------
	
    final   static Logger     log    = Logger.getLogger( DoctorHibernateRestTest.class.getName() );  
    
    @SuppressWarnings("restriction")
	private static HttpServer server;
    private static URI        uri    = UriBuilder.fromUri("http://localhost/").port(8282).build();
    private static Client     client = ClientBuilder.newClient();

    // Lifecycle Methods -------------------------------------------------------         
    
    @SuppressWarnings("restriction")
	@BeforeClass
    public static void init() throws IOException {
        // create a new server listening at port 8080
        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);

        // create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(
                                 new TestApplicationConfig02(), HttpHandler.class);

        // map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);

        // start the server
        server.start();         
    }

    @SuppressWarnings("restriction")
	@AfterClass
    public static void stop() {
        server.stop(0);        
    }
    
    @Before
	  public void setUp() throws Exception {
		    //beforeAssertions();
	  }

	  @After
	  public void tearDown() throws Exception {
		   //afterAssertions();       
	  }

   
    // Unit tests --------------------------------------------------------------            
    
    /*    
    // Create a new doctor
      String newDoctor = 
              "<doctor>"
              + "<first-name>Joe</first-name>"
              + "<last-name>Doe</last-name>"
              + "<symbol-doctor>0000001</symbol-doctor>"
              + "<symbol-spec>chirurgia</symbol-spec>"
              + "<email-address>joe.doe@mail.com</email-address>"
              + "<remarks>test</remarks>"
              + "</doctor>";
    */    
      
    @Test
    public void shouldMarshallADoctor() throws JAXBException {
        // given
        Doctor doctor = new Doctor( "1234567", "John", "Smith", "j.smith@gmail.com");
        log.info("Java: " + doctor);
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Doctor.class);
        Marshaller m = context.createMarshaller();
        m.marshal(doctor, writer);  
        log.info("XML : " + writer);
    }
    
    @Test
    @Ignore
    public void shouldCheckGetDoctorByLoginResponse() {
        log.info("shouldCheckGetDoctorByLoginResponse");
        Response response = client.target("http://localhost:8282/doctors/0000001").request().get();
        assertEquals(200, response.getStatus());
        log.info(response.toString());
    }
    
    @Test
    @Ignore
    public void shouldCheckGetDoctorByLogin() {
        log.info("shouldCheckGetDoctorByLogin");
        String login = "0000001";
        Doctor doctor = client.target("http://localhost:8282/doctors").path(login).request().get(Doctor.class);
        assertEquals(login, doctor.getUsername());
        log.info(doctor);
    }    
    
    @Test
    @Ignore
    public void shouldCheckGetDoctorByIdResponse() {
        log.info("shouldCheckGetDoctorByIdResponse");
        Response response = client.target("http://localhost:8282/doctors/0000003").request().get();
        assertEquals(200, response.getStatus());
    } 
      
    @Test
    @Ignore
    public void shouldCheckGetDoctorById() {
        log.info("shouldCheckGetDoctorById");
        String id = "0000003";
        Doctor doctor = client.target("http://localhost:8282/doctors").path(id).request().get(Doctor.class);
        assertEquals(id, doctor.getId().toString());
    }    
   
    @Test
    public void shouldCheckGetDoctorByEmailURI() {
        log.info("shouldCheckGetDoctorByEmailURI");
        Response response = client.target("http://localhost:8282/doctors?email=m.zubek@wp.pl").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldCheckGetDoctorByEmailWithParamURI() {
        log.info("shouldCheckGetDoctorByEmailWithParamURI");
        Response response = client.target("http://localhost:8282/doctors")
                                  .queryParam("email", "p.bilski@wp.pl").request().get();
        assertEquals(200, response.getStatus());
    }
    
    /*
    @Test
    public void shouldCheckGetDoctorByFirstnameNameURI() {
        log.info("shouldCheckGetDoctorByFirstnameNameURI");
        Response response = client.target("http://localhost:8282/doctors/search;firstname=Michał;surname=Zubek").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldCheckGetDoctorByFirstnameNameWithParamURI() {
        log.info("shouldCheckGetDoctorByFirstnameNameWithParamURI");
        Response response = client.target("http://localhost:8282/doctors/search")
                                  .matrixParam("firstname", "Janina").matrixParam("surname", "Jagielska").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    //@Ignore
    public void shouldCheckGetDoctorWithCookieParamURI() {
        Cookie myCookie = new Cookie("myCookie", "This is my cookie");
        String response = client.target("http://localhost:8282/doctor/cookie").request().cookie(myCookie).get(String.class);
        assertEquals("This is my cookie from the server", response);
    }
    */
    /*
    @Test
    @Ignore
    public void shouldEchoUserAgentValue() {
        String response = client.target("http://localhost:8282/doctor/userAgent").request().get(String.class);
        //log.info("    : " + response);
        assertEquals("Jersey/2.3.1 (HttpUrlConnection 1.7.0_05) from the server", response);
    }
    */
   
}
