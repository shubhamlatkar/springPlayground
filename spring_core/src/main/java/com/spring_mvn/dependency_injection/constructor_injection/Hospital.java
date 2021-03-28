package com.spring_mvn.dependency_injection.constructor_injection;

public class Hospital {
    private int id;
    private String name;

    public Hospital() {
    }

    public Hospital(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
