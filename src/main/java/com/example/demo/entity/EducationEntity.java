package com.example.demo.entity;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "education", schema = "internshipproject")
public class EducationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "speciality")
    private String speciality;
    @Basic
    @Column(name = "enddate")
    private String enddate;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Resume", nullable = false)
    private ResumeEntity resume;

    public Long getId() {
        return id;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public ResumeEntity getResume() {
        return resume;
    }

    public void setResume(ResumeEntity resume) {
        this.resume = resume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationEntity that = (EducationEntity) o;
        return id == that.id && resume == that.resume && Objects.equals(location, that.location) && Objects.equals(speciality, that.speciality) && Objects.equals(enddate, that.enddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, speciality, enddate, resume);
    }
}
