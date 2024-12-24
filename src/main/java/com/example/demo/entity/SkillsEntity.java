package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skills", schema = "internshipproject")
public class SkillsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    public SkillsEntity() {
    }

    public SkillsEntity(String name) {
        this.name = name;
    }
}