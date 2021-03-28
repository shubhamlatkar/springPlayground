package com.spring_mvn.dependency_injection.dependency_check;

import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class Student {
    private int id;
    private List<String> subjects;

    public Student() {
    }

    public Student(int id, List<String> subjects) {
        this.id = id;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    @Required
    public void setId(int id) {
        this.id = id;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", subjects=" + subjects +
                '}';
    }
}
