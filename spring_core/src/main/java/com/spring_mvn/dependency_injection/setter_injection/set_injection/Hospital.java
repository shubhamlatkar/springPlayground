package com.spring_mvn.dependency_injection.setter_injection.set_injection;

import java.util.Set;

public class Hospital {
    private String name;
    private Set<String> doctors;

    public Hospital() {
    }

    public Hospital(String name, Set<String> doctors) {
        this.name = name;
        this.doctors = doctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<String> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
