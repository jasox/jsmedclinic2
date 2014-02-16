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

import static org.junit.Assert.assertEquals;

import pl.jasox.medward.model.domainobject.Doctor;

/**
 * @author Janusz Swół
 * @credit Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
//@Ignore
public class DoctorCRUDRestServiceITest {

    
    // Attributes --------------------------------------------------------------    

    private static HttpServer server;
    private static URI        uri    = UriBuilder.fromUri("http://localhost/").port(8282).build();
    private static Client     client = ClientBuilder.newClient();

    
    // Lifecycle Methods -------------------------------------------------------         
    
    @BeforeClass
    public static void init() throws IOException {
        // create a new server listening at port 8080
        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);

        // create a handler wrapping the JAX-RS application
        HttpHandler handler = 
           RuntimeDelegate.getInstance().createEndpoint(new ApplicationConfig(), HttpHandler.class);

        // map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);

        // start the server
        server.start();
    }

    @AfterClass
    public static void stop() {
        server.stop(0);
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
        System.out.println("Java: " + doctor);
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Doctor.class);
        Marshaller m = context.createMarshaller();
        m.marshal(doctor, writer);  
        System.out.println("XML : " + writer);
    }
   
    @Test
    public void shouldCheckGetDoctorByLoginResponse() {
        Response response = client.target("http://localhost:8282/doctor/1234567").request().get();
        assertEquals(200, response.getStatus());
    }
 
    @Test
    public void shouldCheckGetDoctorByLogin() {
        String login = "1234567";
        Doctor doctor = client.target("http://localhost:8282/doctor").path(login).request().get(Doctor.class);
        assertEquals(login, doctor.getUsername());
    }

    @Test
    public void shouldCheckGetDoctorByIdResponse() {
        Response response = client.target("http://localhost:8282/doctor/1234567").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldCheckGetDoctorById() {
        String id = "1234567";
        Doctor doctor = client.target("http://localhost:8282/doctor").path(id).request().get(Doctor.class);
        assertEquals(id, doctor.getId().toString());
    }

    @Test
    public void shouldCheckGetDoctorByEmailURI() {
        Response response = client.target("http://localhost:8282/doctor?email=j.doe@wp.pl").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldCheckGetDoctorByEmailWithParamURI() {
        Response response = client.target("http://localhost:8282/doctor").queryParam("email", "j.doe@onet.pl").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldCheckGetDoctorByFirstnameNameURI() {
        Response response = client.target("http://localhost:8282/doctor/search;firstname=Joe;surname=Doe").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldCheckGetDoctorByFirstnameNameWithParamURI() {
        Response response = client.target("http://localhost:8282/doctor/search")
                                  .matrixParam("firstname", "Jane").matrixParam("surname", "Doe").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    //@Ignore
    public void shouldCheckGetDoctorWithCookieParamURI() {
        Cookie myCookie = new Cookie("myCookie", "This is my cookie");
        String response = client.target("http://localhost:8282/doctor/cookie").request().cookie(myCookie).get(String.class);
        assertEquals("This is my cookie from the server", response);
    }

    /*
    @Test
    @Ignore
    public void shouldEchoUserAgentValue() {
        String response = client.target("http://localhost:8282/doctor/userAgent").request().get(String.class);
        //System.out.println("    : " + response);
        assertEquals("Jersey/2.3.1 (HttpUrlConnection 1.7.0_05) from the server", response);
    }
    */
}
