package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "resume", schema = "internshipproject")
public class ResumeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "Information")
    private String information;

    @Getter
    @Setter
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EducationEntity> educations;
    @Getter
    @Setter
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExpirienceEntity> experiences;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Student", nullable = false)
    private Student student;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "skillsresume",
            joinColumns = @JoinColumn(name = "resumeid"),
            inverseJoinColumns = @JoinColumn(name = "skillsid")
    )
    @Getter
    @Setter
    private List<SkillsEntity> skills;


    @Getter
    @Setter
    @OneToOne(mappedBy = "resume", fetch = FetchType.LAZY)
    private PersonalinformationEntity personalInformation;
    @Getter
    @Setter
    @Basic
    @Column(name = "description")
    private String Desctiption;
    @Getter
    @Setter
    @Basic
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    @Basic
    @Column(name = "surname")
    private String surname;
    @Getter
    @Setter
    @Basic
    @Column(name = "ot")
    private String ot;
    @Getter
    @Setter
    @Basic
    @Column(name = "phone")
    private String phone;
    @Getter
    @Setter

    @Basic
    @Column(name = "address")
    private String address;
    @Getter
    @Setter

    @Basic
    @Column(name = "email")
    private String email;
    @Getter
    @Setter
    @Basic
    @Column(name = "dateofbirth")
    private String dateofbirth;




    public ResumeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
        ResumeEntity that = (ResumeEntity) o;
        return id == that.id && student == that.student && Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, information, student);
    }

}
