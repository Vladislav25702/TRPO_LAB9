package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "internship_favorite", schema = "internshipproject")
public class InternshipFavoriteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Internships", nullable = false)
    private Internship internships;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Student", nullable = false)
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Internship getInternships() {
        return internships;
    }

    public void setInternships(Internship internships) {
        this.internships = internships;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternshipFavoriteEntity that = (InternshipFavoriteEntity) o;
        return id == that.id && internships == that.internships && student == that.student;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, internships, student);
    }
}
