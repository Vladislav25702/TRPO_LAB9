package com.example.demo.services;

import com.example.demo.entity.EducationEntity;
import com.example.demo.entity.ExpirienceEntity;
import com.example.demo.entity.ResumeEntity;
import com.example.demo.entity.SkillsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final SkillService skillService;



    @Autowired
    public ResumeService(ResumeRepository resumeRepository, EducationService educationService, ExperienceService experienceService, SkillService skillService) {
        this.resumeRepository = resumeRepository;
        this.educationService = educationService;
        this.experienceService = experienceService;
        this.skillService = skillService;
    }


    public void createResume(ResumeEntity resume) {
        resumeRepository.save(resume);
    }


    public ResumeEntity getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElse(null);
    }

    public List<ResumeEntity> getResumesByUserId(Long userId) {
        return resumeRepository.findByStudentId(userId);
    }

    @Transactional
    public void deleteResumeById(Long id) {
        if (!resumeRepository.existsById(id)) {
            throw new RuntimeException("Resume not found with id: " + id);
        }
        resumeRepository.deleteById(id);
    }
    public List<ResumeEntity> getAllResume(){
        return new ArrayList<>(resumeRepository.findAll());
    }


    @Transactional
    public void updateResume(ResumeEntity resume, ResumeCreationDTO resumeCreationDTO) {
        ResumeEntity existingResume = resumeRepository.findById(resumeCreationDTO.getId()).orElseThrow(() -> new RuntimeException("Resume not found"));
        // Check that the user owns this resume
        if (!Objects.equals(existingResume.getStudent().getId(), resume.getStudent().getId())) {
            throw new RuntimeException("User does not own this resume, and cannot update it");
        }

        existingResume.setInformation(resumeCreationDTO.getInformation());
        existingResume.setName(resumeCreationDTO.getName());
        existingResume.setSurname(resumeCreationDTO.getSurname());
        existingResume.setOt(resumeCreationDTO.getOt());
        existingResume.setEmail(resumeCreationDTO.getEmail());
        existingResume.setAddress(resumeCreationDTO.getAddress());
        existingResume.setPhone(resumeCreationDTO.getPhone());


        List<ExpirienceEntity> updatedExperienceEntities = resumeCreationDTO.getExpirienceEntities();
        if (updatedExperienceEntities != null) {
            if(existingResume.getExperiences() != null) {
                // Delete existing experiences
                for (ExpirienceEntity existingExperience : existingResume.getExperiences()) {
                    experienceService.deleteExperience(existingExperience.getId());
                }
                existingResume.getExperiences().clear();
            }
            //Add new experiences
            for(ExpirienceEntity updatedExperience : updatedExperienceEntities){
                updatedExperience.setResume(existingResume);
                existingResume.getExperiences().add(updatedExperience);
            }

        }
        else if (existingResume.getExperiences() != null) {
            // no updated experiences, so we should delete the existing ones
            for (ExpirienceEntity experience: existingResume.getExperiences()) {
                experienceService.deleteExperience(experience.getId());
            }
            existingResume.setExperiences(null);
        }
        //Обновляем education
        List<EducationEntity> updatedEducations = resumeCreationDTO.getEducationEntities();
        if (updatedEducations != null) {
            if (existingResume.getEducations() == null) {
                existingResume.setEducations(new ArrayList<>());
            }
            List<EducationEntity> existingEducationEntities = new ArrayList<>(existingResume.getEducations());
            for (EducationEntity updatedEducation : updatedEducations) {
                if (updatedEducation.getId() != null) {
                    EducationEntity existingEducation = existingEducationEntities.stream()
                            .filter(edu -> updatedEducation.getId().equals(edu.getId()))
                            .findFirst().orElse(null);

                    if(existingEducation != null){
                        existingEducation.setLocation(updatedEducation.getLocation());
                        existingEducation.setSpeciality(updatedEducation.getSpeciality());
                        existingEducation.setEnddate(updatedEducation.getEnddate());
                        existingEducationEntities.remove(existingEducation);
                    }
                    else {
                        updatedEducation.setResume(existingResume);
                        existingResume.getEducations().add(updatedEducation);
                    }
                }else {
                    updatedEducation.setResume(existingResume);
                    existingResume.getEducations().add(updatedEducation);
                }
            }
            for (EducationEntity educationToDelete : existingEducationEntities) {
                educationService.deleteEducation(educationToDelete.getId());
                existingResume.getEducations().remove(educationToDelete);
            }
        }
        else if (existingResume.getEducations() != null) {
            // no updated educations, so we should delete the existing ones
            for (EducationEntity education : existingResume.getEducations()) {
                educationService.deleteEducation(education.getId());
            }
            existingResume.setEducations(null);
        }
        //Обновляем skills
        List<String> updatedSkillsNames = resumeCreationDTO.getSkillNames();
        if(updatedSkillsNames != null) {
            List<SkillsEntity> skills = new ArrayList<>();
            if (existingResume.getSkills() != null) {
                List<SkillsEntity> existingSkills = new ArrayList<>(existingResume.getSkills());
                for (String skillName : updatedSkillsNames) {
                    SkillsEntity existingSkill = existingSkills.stream()
                            .filter(skill -> skill.getName().equals(skillName))
                            .findFirst()
                            .orElse(null);
                    if (existingSkill != null) {
                        skills.add(existingSkill);
                        existingSkills.remove(existingSkill);
                    }
                    else {
                        SkillsEntity skillsEntity = new SkillsEntity();
                        skillsEntity.setName(skillName);
                        SkillsEntity savedSkill = skillService.saveSkill(skillsEntity);
                        skills.add(savedSkill);
                    }
                }
                for (SkillsEntity skillToDelete : existingSkills) {
                    existingResume.getSkills().remove(skillToDelete);
                }
            }
            else {
                for (String skillName : updatedSkillsNames) {
                    SkillsEntity existingSkill = skillService.findSkillsEntityByName(skillName);
                    if (existingSkill != null) {
                        skills.add(existingSkill);
                    } else {
                        SkillsEntity skillsEntity = new SkillsEntity();
                        skillsEntity.setName(skillName);
                        SkillsEntity savedSkill = skillService.saveSkill(skillsEntity);
                        skills.add(savedSkill);
                    }
                }
            }
            existingResume.setSkills(skills);
        }
        else if (existingResume.getSkills() != null) {
            existingResume.getSkills().clear();
        }
        System.out.println(existingResume.getId()+"1");
        resumeRepository.save(existingResume);

    }

    @Transactional
    public void deleteResume(Long resumeId) {
        ResumeEntity resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found with id: " + resumeId));

        if (resume.getEducations() != null){
            for (EducationEntity education : resume.getEducations()) {
                educationService.deleteEducation(education.getId());
            }
        }
        if(resume.getExperiences() != null) {
            for(ExpirienceEntity experience : resume.getExperiences()){
                experienceService.deleteExperience(experience.getId());
            }
        }
        if (resume.getPersonalInformation() != null){
            // We should also delete the personal information associated with this record.
            // personalinformationRepository.deleteById(resume.getPersonalInformation().getId());
        }
        resumeRepository.delete(resume);
    }



}