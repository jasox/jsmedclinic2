package pl.jasox.medward.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.jasox.medward.model.domainobject.Doctor;

/**
 */
@Path("/doctor")
@Produces(MediaType.APPLICATION_XML)
public class DoctorCRUDRestService {

    // Public Methods               

    /**
     * http://localhost:8080/rs/doctors/1234567
     */
    @GET
    @Path("{login: [a-zA-Z]*}")
    public Doctor getDoctorByLogin(@PathParam("login") String login) {        
        Doctor doctor = new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com");        
        return doctor;
    }

    /**
     * http://localhost:8080/rs/doctors/1234567
     */
    @GET
    @Path("{doctorId : \\d+}")
    public Doctor getDoctorById(@PathParam("doctorId") Integer id) {
        Doctor doctor = new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com"); 
        doctor.setIdDoctor(id);
        return doctor;
    }

    @GET
    public Doctor getDoctorByZipCode(@QueryParam("email") String email) {
         System.out.println("getDoctorByEmail : " + email);
         Doctor doctor = new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com");
         return doctor;
    }

    @GET
    @Path("search")
    public Doctor getDoctorByFirstnameName(@MatrixParam("firstname") String firstname, 
                                           @MatrixParam("surname")   String surname) {
        System.out.println("getDoctorByFirstnameName : " + firstname + " - " + surname);
        return new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com");
    }

    @GET
    @Path("cookie")
    public String echoCookie(@CookieParam("myCookie") String myCookie) {
        System.out.println("echoCookie : " + myCookie);
        return myCookie + " from the server";
    }

    @GET
    @Path("userAgent")
    @Produces(MediaType.TEXT_PLAIN)
    public String echoUserAgent(@HeaderParam(value = "User-Agent") String userAgent) {
        System.out.println("echoUserAgent : " + userAgent);
        return userAgent + " from the server";
    }

    @GET
    @Path("userAgentRep")
    @Produces(MediaType.TEXT_PLAIN)
    public Response echoUserAgentWithReponse(@HeaderParam(value = "User-Agent") String userAgent) {
        System.out.println("echoUserAgentWithReponse : " + userAgent);
        return Response.ok(userAgent + " from the server", MediaType.TEXT_PLAIN).build();
    }
}