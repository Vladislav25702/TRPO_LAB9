package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "expirience", schema = "internshipproject")
public class ExpirienceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "position")
    private String position;
    @Basic
    @Column(name = "desctiption")
    private String desctiption;
    @Basic
    @Column(name = "Startdate")
    private String startdate;
    @Basic
    @Column(name = "Enddate")
    private String enddate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Resume", nullable = false)
    private ResumeEntity resume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
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
        ExpirienceEntity that = (ExpirienceEntity) o;
        return id == that.id && resume == that.resume && Objects.equals(location, that.location) && Objects.equals(position, that.position) && Objects.equals(desctiption, that.desctiption) && Objects.equals(startdate, that.startdate) && Objects.equals(enddate, that.enddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, position, desctiption, startdate, enddate, resume);
    }
}
