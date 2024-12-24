package com.example.demo.services;

import com.example.demo.entity.EducationEntity;
import com.example.demo.entity.SkillsEntity;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    private final EducationRepository skillsRepository;


    public EducationService(EducationRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }


    public EducationEntity saveEducation(EducationEntity skillsEntity) {
        return skillsRepository.save(skillsEntity);

    }

    public void deleteEducation(Long id){
        if(!skillsRepository.existsById(id)){
            throw new RuntimeException("Education with id: " + id + " does not exist");
        }
        skillsRepository.deleteById(id);
    }
}