package com.example.demo.services;

import com.example.demo.entity.SkillsEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    private final SkillsRepository skillsRepository;


    public SkillService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public SkillsEntity findSkillsEntityByName(String name) {
        return skillsRepository.findByName(name).orElse(null);
    }

    public SkillsEntity saveSkill(SkillsEntity skillsEntity) {
        return skillsRepository.save(skillsEntity);

    }
    @Transactional
    public void deleteSkill(Long skillId) {
        SkillsEntity skill = skillsRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found with id: " + skillId));
        skillsRepository.delete(skill);
    }
}