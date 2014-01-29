package pl.jasox.medward.test;

import java.io.File;
//import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
//import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public interface Dependencies {
  
  static final File[] SOLDER = Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.jboss.seam.solder:seam-solder")
       .withoutTransitivity().asFile();
      /*    
      DependencyResolvers.use(MavenDependencyResolver.class)
     .loadMetadataFromPom("pom.xml").artifact("org.jboss.seam.solder:seam-solder")
     .exclusion("*").resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
     */
  static final File[] JODA_TIME = Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("joda-time:joda-time")
       .withoutTransitivity().asFile();
      /*    
      DependencyResolvers.use(MavenDependencyResolver.class)
     .loadMetadataFromPom("pom.xml").artifact("joda-time:joda-time").exclusion("*")
     .resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
      */
  static final File[] INTERNATIONAL =  Maven.resolver().loadPomFromFile("pom.xml")
       .resolve("org.jboss.seam.international:seam-international")
       .withoutTransitivity().asFile();
          
      /*
      DependencyResolvers.use(MavenDependencyResolver.class)
     .loadMetadataFromPom("pom.xml").artifact("org.jboss.seam.international:seam-international")
     .exclusion("*").resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
     */
    
}
