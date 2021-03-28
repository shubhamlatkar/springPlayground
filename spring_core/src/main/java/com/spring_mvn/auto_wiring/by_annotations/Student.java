package com.spring_mvn.auto_wiring.by_annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Student {
    private String name;
    private Scores scores;

    public Student() {
    }

    public Student(String name, Scores scores) {
        this.name = name;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scores getScores() {
        return scores;
    }

    @Autowired
    @Qualifier("new_scores")
    public void setScores(Scores scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }
}
