package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "company", schema = "internshipproject")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private Users user;
    private String companyName;
    private String email;
    private String address;
    // Getters, setters, constructors
}
