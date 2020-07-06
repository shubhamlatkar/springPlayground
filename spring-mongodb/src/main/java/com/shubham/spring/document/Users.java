package com.shubham.spring.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Users {

    @Id
    private String id;
    private String name;
    private String teamName;
    private Long salary;

    public Users( String name, String teamName, Long salary) {
        this.name = name;
        this.teamName = teamName;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + "]";
    }
    
}