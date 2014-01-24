/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors. 
 */
package pl.jasox.medward.reference;

/**
 * A simple Java bean representing a month. 
 * This bean assumes that the names it is provided have already been localized.
 *
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
public class Month {

    private int    index;
    private String name;
    private String shortName;

    public Month() {
    }

    public Month(int index, String name, String shortName) {
        this.index     = index;
        this.name      = name;
        this.shortName = shortName;
    }

    public int getIndex() {
        return index;
    }

    public int getNumber() {
        return index + 1;
    }

    public String getLongName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }
}
