package com.spring_mvn.dependency_injection.setter_injection.ref_injection;

public class Student {
    private int id;
    private Scores scores;

    public Student() {
    }

    public Student(int id, Scores scores) {
        this.id = id;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }

    @Override
    public String  toString() {
        return "Student{" +
                "id=" + id +
                ", scores=" + scores +
                '}';
    }
}
