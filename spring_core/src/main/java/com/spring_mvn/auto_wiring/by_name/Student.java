package com.spring_mvn.auto_wiring.by_name;

public class Student {
    private String name;
    private Scores new_scores;

    public Student() {
    }

    public Student(String name, Scores new_scores) {
        this.name = name;
        this.new_scores = new_scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scores getNew_scores() {
        return new_scores;
    }

    public void setNew_scores(Scores new_scores) {
        this.new_scores = new_scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", new_scores=" + new_scores +
                '}';
    }
}
