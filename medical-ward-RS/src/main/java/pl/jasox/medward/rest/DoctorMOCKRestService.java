package pl.jasox.medward.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.jasox.medward.model.domainobject.Doctor;
import pl.jasox.medward.rest.Doctors;

/**
 *        
 */
@Path("/doctor")
@Produces(MediaType.APPLICATION_XML)
public class DoctorMOCKRestService {
    
    // Public Methods
  
    @GET
    @Path("{login: [a-z]*}")
    public Response getDoctorByLogin(@PathParam("login") String login) {
        System.out.println("getDoctorByLogin : " + login);
        Doctor doctor = new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com");       
        return Response.ok(doctor).build();
    }

    @GET
    @Path("{doctorId : \\d+}")
    public Response getDoctorById(@PathParam("doctorId") Integer id) {
        System.out.println("getDoctorById : " + id);        
        Doctor doctor = new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com"); 
        doctor.setIdDoctor(id);
        return Response.ok(doctor).build();
    }

    @GET
    public Response getDoctorsByZipCode(@QueryParam("zip") Long zip) {
        System.out.println("getDoctorByZipCode : " + zip);
        Doctors doctors = new Doctors();
        doctors.add(new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com"));
        doctors.add(new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com"));        
        return Response.ok(doctors).build();
    }

    @GET
    @Path("search")
    public Response getDoctorByName(@MatrixParam("firstname") String firstname, 
                                    @MatrixParam("surname")   String surname) {
        System.out.println("getDoctorByName : " + firstname + " - " + surname);
        Doctors doctors = new Doctors();
        doctors.add(new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com"));
        doctors.add(new Doctor( "1234567", "John", "Smith", "jsmith@gmail.com"));
        return Response.ok(doctors).build();
    }
}