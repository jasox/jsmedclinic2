package pl.jasox.medward.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pl.jasox.medward.model.domainobject.Doctor;

/**
 * @author Janusz Swół
 * @credit Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@XmlRootElement //(name = "doctors") 
@XmlSeeAlso(Doctor.class)
public class Doctors extends ArrayList<Doctor> {

    
    // Constructors ------------------------------------------------------------  

    public Doctors() {
        super();
    }

    public Doctors(Collection<? extends Doctor> c) {
        super(c);
    }
    
    // Getters & Setters -------------------------------------------------------        

    @XmlElement(name = "doctor")
    public List<Doctor> getDoctors() {
        return this;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.addAll(doctors);
    }
}