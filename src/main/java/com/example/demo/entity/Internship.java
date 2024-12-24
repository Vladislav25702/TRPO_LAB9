package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private String description;

    private String location;
    private String salary;

    public Internship() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }


    public String getLocation() {
        return location;
    }

    public String getSalary() {
        return salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }







    public Internship(String title, String company, String description, String location, String salary) {
        this.title = title;
        this.company = company;
        this.description = description;

        this.location = location;
        this.salary = salary;

    }
}
