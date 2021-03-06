package pl.jasox.medward.rest;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
import org.junit.Ignore;

import pl.jasox.medward.model.domainobject.Doctor;

/**
 *
 */
@Ignore
public class DoctorRestServiceITest {
  
    // Attributes --------------------------------------------------------------    

    private static final String Doctor_XML = 
       "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
          + "<doctor>"
            + "<symbol-doctor>1234565</symbol-doctor>"            
            + "<first-name>John</first-name>"
            + "<last-name>Smith</last-name>"
            + "<email-address>jsmith@gmail.com</email-address>"
          + "</doctor>";
    
    // Tests  ------------------------------------------------------------------
    
    @Test
    public void shouldMarshallADoctor() throws JAXBException {
        // given
        Doctor doctor = new Doctor("1234565", "John", "Smith", "jsmith@gmail.com");
        System.out.println("Java: " + doctor);
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Doctor.class);
        Marshaller m = context.createMarshaller();
        m.marshal(doctor, writer);
        System.out.println("XML : " + writer.toString());
        // then
        assertEquals(Doctor_XML, writer.toString());
    }
    
    @Test
    public void shouldMarshallAListOfDoctors() throws JAXBException {
        Doctors doctors = new Doctors();
        doctors.add(new Doctor("John", "Smith", "jsmith@gmail.com", "1234565"));
        doctors.add(new Doctor("Joe",  "Doe",   "jdoe@gmail.com",   "1234566"));
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Doctors.class);
        Marshaller m = context.createMarshaller();
        m.marshal(doctors, writer);
        System.out.println("XML : " + writer.toString());
    }
   
    @Test //@Ignore
    public void shouldCheckURIs() throws IOException {

        URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();

        // Create an HTTP server listening at port 8282
        HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        // Create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(
                                new TestApplicationConfig01(), HttpHandler.class);
        // Map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);
        // Start the server
        server.start();

        Client client = ClientBuilder.newClient();

        // Valid URIs
        assertEquals(200, client.target("http://localhost:8282/doctor/dummy").request().get().getStatus());
        assertEquals(200, client.target("http://localhost:8282/doctor/1234567").request().get().getStatus());
        assertEquals(200, client.target("http://localhost:8282/doctor?email=j.doe@wp.pl").request().get().getStatus());
        assertEquals(200, client.target("http://localhost:8282/doctor/search;firstname=Jan;surname=Dox").request().get().getStatus());

        // Invalid URIs
        assertEquals(404, client.target("http://localhost:8282/doctor/dummy/22").request().get().getStatus());
        assertEquals(404, client.target("http://localhost:8282/doctor/dummy/1234").request().get().getStatus());

        // Stop HTTP server
        server.stop(0);
    }
      

    @Test //@Ignore
    public void shouldGetDoctors() throws IOException {

        URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();

        // Create an HTTP server listening at port 8282
        HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        // Create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(
                                 new TestApplicationConfig01(), HttpHandler.class);
        // Map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);
        // Start the server
        server.start();

        Client client = ClientBuilder.newClient();

        // Valid URIs
        Response response = client.target("http://localhost:8282/doctor").request().get();
        assertEquals(200, response.getStatus());
        
        System.out.println(response.readEntity(String.class));

        // Stop HTTP server
        server.stop(0);
    }
    
}
