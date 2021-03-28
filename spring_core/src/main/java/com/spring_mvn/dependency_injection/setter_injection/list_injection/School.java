package com.spring_mvn.dependency_injection.setter_injection.list_injection;

import java.util.List;

public class School {
    private int id;
    private List<String> departments;

    public School() {
    }

    public School(int id, List<String> departments) {
        this.id = id;
        this.departments = departments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", departments=" + departments +
                '}';
    }
}
