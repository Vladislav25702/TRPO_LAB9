package com.example.demo.services;

import lombok.Data;

@Data
public class StudentRegistrationDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public StudentRegistrationDTO(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}