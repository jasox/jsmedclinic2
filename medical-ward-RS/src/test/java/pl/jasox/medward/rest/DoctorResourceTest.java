package pl.jasox.medward.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
//import pl.jasox.medward.db.ApplicationDatabase;
//import pl.jasox.medward.db.DatabaseType;
//import pl.jasox.medward.db.TestDatabase;
//import pl.jasox.medward.db.TestEntityManagerProducer;
import pl.jasox.medward.util.ResourcesProducer;

/**
 * @author <a href="mailto:janusz.swol@gmail.com">Janusz Swół</a>
 * @version 1.1.1
 */
@Ignore
@RunWith(Arquillian.class)
public class DoctorResourceTest {  
     
  String localhost    = "127.0.0.1";
  String js_laptop_hp = "10.23.14.95";
   
  String host = localhost;
  // String host = js_laptop_hp;
    
  // ---------------------------------------------------------------------------
  
  @Deployment
  public static Archive<?> createDeployment() {

    File[] medical_ward_simple = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("pl.jasox.medclinic:medical-ward-simple")
       .withoutTransitivity().asFile();
    
    File[] picketlink_idm = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.picketlink.idm:picketlink-idm-core")
       .withTransitivity().asFile();
    
    File[] json = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.glassfish:javax.json")
       .withTransitivity().asFile();
    
    File[] resteasy_jaxrs = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.jboss.resteasy:resteasy-jaxrs")
       .withTransitivity().asFile();
    
    File[] resteasy_jaxb = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.jboss.resteasy:resteasy-jaxb-provider")
       .withTransitivity().asFile();    
    
    File[] medical_ward_ejb = 
        Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("pl.jasox.medclinic:medical-ward-ejb")
       .withoutTransitivity().asFile();
    
    return ShrinkWrap.create(WebArchive.class, "test.war") 
       .addPackages(true,"pl.jasox.medward.model.dao.ejb")
       //.addClass(DoctorEjbDao.class)       
       //.addClass(EjbDaoFactory.class)      
       .addClass(ResourcesProducer.class)
       //.addClass(ApplicationDatabase.class)
       //.addClass(TestDatabase.class)   
       //.addClass(DatabaseType.class)      
       //.addClass(TestEntityManagerProducer.class) 
       .addClass(DoctorHibernateRestService.class)     
       .addAsLibraries(medical_ward_simple)       
       .addAsLibraries(picketlink_idm)  
       .addAsLibraries(medical_ward_ejb)
       .addAsLibraries(json)
       .addAsLibraries(resteasy_jaxrs)
       .addAsLibraries(resteasy_jaxb)
       .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
       .addAsResource("log4j.properties",   "log4j.properties")
       .addAsResource("logging.properties", "logging.properties")
       .addAsWebInfResource("WEB-INF/glassfish-web.xml", "glassfish-web.xml")
       .addAsWebInfResource("WEB-INF/web.xml",           "web.xml")
       .addAsWebInfResource("META-INF/beans.xml",        "beans.xml")
       .addAsWebInfResource("META-INF/seam-beans.xml",   "seam-beans.xml");
  }
  
  // ---------------------------------------------------------------------------
     
  @Test  
   public void testDoctorResource() throws Exception {
      System.out.println("*** Create a new Doctor ***");
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

      URL postUrl = new URL("http://" + host + ":8080/test/doctors");
      HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      connection.setDoOutput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/xml");
      System.out.println("Connection :" + connection);
      OutputStream os = connection.getOutputStream();
      os.write(newDoctor.getBytes());
      os.flush();
      Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());
      System.out.println("Location: " + connection.getHeaderField("Location"));
      connection.disconnect();

      // Get the new doctor
      System.out.println("*** GET Created Doctor **");
      URL getUrl = new URL("http://" + host + ":8080/test/doctors/1");
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");
      System.out.println("Content-Type: " + connection.getContentType());

      BufferedReader reader = new BufferedReader(new
              InputStreamReader(connection.getInputStream()));

      String line = reader.readLine();
      while (line != null) {
         System.out.println(line);
         line = reader.readLine();
      }
      Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
      connection.disconnect();

      // Update the new doctor.  Change Joe name to Jane 
      String updateDoctor = 
              "<doctor>"
              + "<first-name>Jane</first-name>"
              + "<last-name>Doe</last-name>"
              + "<symbol-doctor>0000001</symbol-doctor>"
              + "<symbol-spec>ortopedia</symbol-spec>"
              + "<email-address>jane.doe@mail.com</email-address>"
              + "<remarks>test</remarks>"
              + "</doctor>";
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("PUT");
      connection.setRequestProperty("Content-Type", "application/xml");
      os = connection.getOutputStream();
      os.write(updateDoctor.getBytes());
      os.flush();
      Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
      connection.disconnect();

      // Show the update
      System.out.println("**** After Update ***");
      connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");

      System.out.println("Content-Type: " + connection.getContentType());
      reader = new BufferedReader(
               new InputStreamReader(connection.getInputStream()));

      line = reader.readLine();
      while (line != null) {
         System.out.println(line);
         line = reader.readLine();
      }
      Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
      connection.disconnect();
   }
   
}
