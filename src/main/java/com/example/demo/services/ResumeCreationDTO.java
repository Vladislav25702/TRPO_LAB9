package com.example.demo.services;

import com.example.demo.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Service
public class ResumeCreationDTO {
    @Getter
    @Setter
    private Long id;
    @NotEmpty(message = "Заголовок не может быть пустым")
    private String information;
    private List<EducationEntity> educationEntities = new ArrayList<>();
    private List<ExpirienceEntity> expirienceEntities = new ArrayList<>();


    @Getter
    @Setter
    private List<String> skillNames;
    @Getter
    @Setter
    private PersonalinformationEntity personalInformation;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String ot;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String dateofbirth;

    public ResumeCreationDTO() {

    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<EducationEntity> getEducationEntities() {
        return educationEntities;
    }

    public void setEducationEntities(List<EducationEntity> educationEntities) {
        this.educationEntities = educationEntities;
    }

    public List<ExpirienceEntity> getExpirienceEntities() {
        return expirienceEntities;
    }

    public void setExpirienceEntities(List<ExpirienceEntity> expirienceEntities) {
        this.expirienceEntities = expirienceEntities;
    }



}