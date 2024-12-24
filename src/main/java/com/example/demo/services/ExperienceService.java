package com.example.demo.services;

import com.example.demo.entity.EducationEntity;
import com.example.demo.entity.ExpirienceEntity;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    private final ExperienceRepository skillsRepository;


    public ExperienceService(ExperienceRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }


    public ExpirienceEntity saveEducation(ExpirienceEntity skillsEntity) {
        return skillsRepository.save(skillsEntity);

    }
    public void deleteExperience(Long id){
        if(!skillsRepository.existsById(id)){
            throw new RuntimeException("Experience with id: " + id + " does not exist");
        }
        skillsRepository.deleteById(id);
    }

}